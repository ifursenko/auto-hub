package com.autohub.api.dto;

import java.time.LocalDateTime;

public class ScheduleSlotResponse {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ScheduleSlotResponse(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
