package com.autohub.service;

import com.autohub.api.dto.CarRequest;
import com.autohub.api.dto.CarResponse;
import com.autohub.domain.Car;
import com.autohub.domain.User;
import com.autohub.repo.CarRepository;
import com.autohub.repo.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    public CarService(CarRepository carRepository, UserRepository userRepository, CurrentUserService currentUserService) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    public CarResponse addCar(CarRequest request) {
        User user = getCurrentUser();
        Car car = new Car();
        car.setUser(user);
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setVin(request.getVin());
        Car saved = carRepository.save(car);
        return toResponse(saved);
    }

    public List<CarResponse> listMyCars() {
        User user = getCurrentUser();
        return carRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private User getCurrentUser() {
        Long userId = currentUserService.getUserId();
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private CarResponse toResponse(Car car) {
        return new CarResponse(car.getId(), car.getBrand(), car.getModel(), car.getYear(), car.getVin());
    }
}
