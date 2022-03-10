package pl.pp.pack.api.dhl.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Status {
    private LocalDateTime timestamp;
    private Location location;
    private String description;
}