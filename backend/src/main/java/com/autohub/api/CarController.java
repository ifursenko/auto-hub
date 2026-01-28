package com.autohub.api;

import com.autohub.api.dto.CarRequest;
import com.autohub.api.dto.CarResponse;
import com.autohub.service.CarService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public CarResponse addCar(@Valid @RequestBody CarRequest request) {
        return carService.addCar(request);
    }

    @GetMapping
    public List<CarResponse> listCars() {
        return carService.listMyCars();
    }
}
