package com.autohub.api;

import com.autohub.api.dto.ScheduleSlotResponse;
import com.autohub.service.OrderService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/masters")
public class MasterController {
    private final OrderService orderService;

    public MasterController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}/schedule")
    public List<ScheduleSlotResponse> schedule(@PathVariable Long id,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                               LocalDate date) {
        return orderService.getSchedule(id, date);
    }
}
