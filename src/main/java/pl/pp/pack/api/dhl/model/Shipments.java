package pl.pp.pack.api.dhl.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Shipments {
    private ArrayList<Shipment> shipments;
    private ArrayList<String> possibleAdditionalShipmentsUrl;
}