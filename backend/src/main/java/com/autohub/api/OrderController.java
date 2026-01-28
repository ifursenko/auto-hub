package com.autohub.api;

import com.autohub.api.dto.OrderCreateRequest;
import com.autohub.api.dto.OrderResponse;
import com.autohub.api.dto.OrderStatusUpdateRequest;
import com.autohub.api.dto.RateRequest;
import com.autohub.domain.OrderStatus;
import com.autohub.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Locale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse create(@Valid @RequestBody OrderCreateRequest request) {
        return orderService.createOrder(request);
    }

    @PutMapping("/{id}/status")
    public OrderResponse updateStatus(@PathVariable Long id, @Valid @RequestBody OrderStatusUpdateRequest request) {
        OrderStatus status = OrderStatus.valueOf(request.getStatus().toUpperCase(Locale.ROOT));
        return orderService.updateStatus(id, status);
    }

    @GetMapping
    public List<OrderResponse> list() {
        return orderService.listOrdersForCurrentUser();
    }

    @GetMapping("/admin")
    public List<OrderResponse> listAll() {
        return orderService.listAllOrders();
    }

    @PostMapping("/{id}/pay")
    public OrderResponse pay(@PathVariable Long id) {
        return orderService.pay(id);
    }

    @PostMapping("/{id}/rate")
    public OrderResponse rate(@PathVariable Long id, @Valid @RequestBody RateRequest request) {
        return orderService.rate(id, request.getRating());
    }
}
