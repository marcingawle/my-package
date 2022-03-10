package pl.pp.pack.api.dhl.model;

import lombok.Data;

@Data
public class Destination {
    private Address address;
    private ServicePoint servicePoint;
}