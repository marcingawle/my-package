package pl.pp.pack.api.dhl.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private LocalDateTime timestamp;
    private Location location;
    private String description;
}