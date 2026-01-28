package com.autohub.service;

import com.autohub.api.dto.OrderCreateRequest;
import com.autohub.api.dto.OrderResponse;
import com.autohub.api.dto.ScheduleSlotResponse;
import com.autohub.domain.Car;
import com.autohub.domain.Master;
import com.autohub.domain.Order;
import com.autohub.domain.OrderStatus;
import com.autohub.domain.ServiceEntity;
import com.autohub.domain.User;
import com.autohub.repo.CarRepository;
import com.autohub.repo.MasterRepository;
import com.autohub.repo.OrderRepository;
import com.autohub.repo.ServiceRepository;
import com.autohub.repo.UserRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MasterRepository masterRepository;
    private final CarRepository carRepository;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    private final MasterService masterService;
    private final CurrentUserService currentUserService;

    public OrderService(OrderRepository orderRepository,
                        MasterRepository masterRepository,
                        CarRepository carRepository,
                        ServiceRepository serviceRepository,
                        UserRepository userRepository,
                        MasterService masterService,
                        CurrentUserService currentUserService) {
        this.orderRepository = orderRepository;
        this.masterRepository = masterRepository;
        this.carRepository = carRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.masterService = masterService;
        this.currentUserService = currentUserService;
    }

    public OrderResponse createOrder(OrderCreateRequest request) {
        User client = getCurrentUser();
        Master master = masterRepository.findById(request.getMasterId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Master not found"));
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        if (!car.getUser().getId().equals(client.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Car does not belong to user");
        }
        ServiceEntity service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found"));

        LocalDateTime start = request.getStartTime();
        LocalDateTime end = start.plusMinutes(service.getDurationMinutes());

        if (!isSlotAvailable(master.getId(), start, end)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Slot is not available");
        }

        Order order = new Order();
        order.setClient(client);
        order.setMaster(master);
        order.setCar(car);
        order.setService(service);
        order.setStatus(OrderStatus.NEW);
        order.setStartTime(start);
        order.setEndTime(end);
        order.setTotalPrice(calculatePrice(service.getDurationMinutes(), service.getBasePrice()));
        Order saved = orderRepository.save(order);
        return toResponse(saved);
    }

    public List<OrderResponse> listOrdersForCurrentUser() {
        User user = getCurrentUser();
        List<Order> orders = new java.util.ArrayList<>(orderRepository.findByClientId(user.getId()));
        masterRepository.findByUserId(user.getId())
                .ifPresent(master -> orders.addAll(orderRepository.findByMasterId(master.getId())));
        return orders.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<OrderResponse> listAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        order.setStatus(status);
        return toResponse(orderRepository.save(order));
    }

    public OrderResponse pay(Long id) {
        return updateStatus(id, OrderStatus.PAID);
    }

    public OrderResponse rate(Long id, Integer rating) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        order.setClientRating(rating);
        Order saved = orderRepository.save(order);
        updateMasterRating(order.getMaster().getId());
        return toResponse(saved);
    }

    public List<ScheduleSlotResponse> getSchedule(Long masterId, LocalDate date) {
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = date.atTime(LocalTime.MAX);
        List<Order> orders = orderRepository.findByMasterIdAndStartTimeBetween(masterId, from, to);

        LocalDateTime start = date.atTime(9, 0);
        LocalDateTime end = date.atTime(18, 0);
        return startDates(start, end, 30)
                .stream()
                .filter(slotStart -> isSlotAvailable(orders, slotStart, slotStart.plusMinutes(30)))
                .map(slotStart -> new ScheduleSlotResponse(slotStart, slotStart.plusMinutes(30)))
                .collect(Collectors.toList());
    }

    private boolean isSlotAvailable(Long masterId, LocalDateTime start, LocalDateTime end) {
        LocalDateTime from = start.toLocalDate().atStartOfDay();
        LocalDateTime to = start.toLocalDate().atTime(LocalTime.MAX);
        List<Order> orders = orderRepository.findByMasterIdAndStartTimeBetween(masterId, from, to);
        return isSlotAvailable(orders, start, end);
    }

    private boolean isSlotAvailable(List<Order> orders, LocalDateTime start, LocalDateTime end) {
        Instant cutoff = Instant.now().minus(15, ChronoUnit.MINUTES);
        for (Order order : orders) {
            if (order.getStatus() == OrderStatus.CANCELLED) {
                continue;
            }
            if (order.getStatus() == OrderStatus.NEW && order.getCreatedAt().isBefore(cutoff)) {
                continue;
            }
            boolean overlaps = order.getStartTime().isBefore(end) && order.getEndTime().isAfter(start);
            if (overlaps) {
                return false;
            }
        }
        return true;
    }

    private List<LocalDateTime> startDates(LocalDateTime start, LocalDateTime end, int stepMinutes) {
        LocalDateTime cursor = start;
        java.util.ArrayList<LocalDateTime> list = new java.util.ArrayList<>();
        while (!cursor.isAfter(end)) {
            list.add(cursor);
            cursor = cursor.plusMinutes(stepMinutes);
        }
        return list;
    }

    private int calculatePrice(int durationMinutes, int basePrice) {
        int extra = Math.max(durationMinutes - 15, 0);
        return basePrice + extra * 3;
    }

    private void updateMasterRating(Long masterId) {
        List<Order> orders = orderRepository.findByMasterId(masterId);
        double avg = orders.stream()
                .filter(order -> order.getClientRating() != null)
                .mapToInt(Order::getClientRating)
                .average()
                .orElse(5.0);
        masterService.updateRating(masterId, avg);
    }

    private User getCurrentUser() {
        Long userId = currentUserService.getUserId();
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getClient().getId(),
                order.getMaster().getId(),
                order.getCar().getId(),
                order.getService().getId(),
                order.getStatus().name(),
                order.getStartTime(),
                order.getEndTime(),
                order.getTotalPrice(),
                order.getClientRating()
        );
    }
}
