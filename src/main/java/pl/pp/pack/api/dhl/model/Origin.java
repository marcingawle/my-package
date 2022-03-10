package pl.pp.pack.api.dhl.model;

import lombok.Data;

@Data
public class Origin {
    private Address address;
    private ServicePoint servicePoint;
}