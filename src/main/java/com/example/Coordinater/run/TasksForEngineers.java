package com.example.Coordinater.run;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record TasksForEngineers(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Location location
) {
    public TasksForEngineers{
        if(!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("completedOn must be after startedOn");
        }
    }
}