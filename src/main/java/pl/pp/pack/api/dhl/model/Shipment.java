package pl.pp.pack.api.dhl.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Shipment {
    private String id;
    private String service;
    private Origin origin;
    private Destination destination;
    private Status status;
    private Details details;
    private ArrayList<Event> events;

    public String toInfoString() {
        StringBuilder info = new StringBuilder("Źródło: " + origin.getAddress().getAddressLocality() + "\n" +
                "Cel: " + destination.getAddress().getAddressLocality() + "\n" +
                "Status: " + status.getTimestamp().toString() + ", " + status.getLocation().getAddress().getAddressLocality() + ", " + status.getDescription() + "\n" +
                "Dostępność podpisanego dowodu dostawy: " + details.isProofOfDeliverySignedAvailable() + "\n" +
                "Ilość kawałków: " + details.getTotalNumberOfPieces() + "\n" +
                "Wydarzenia: \n");

        for (Event event : events) {
            info.append("Czas: ").append(event.getTimestamp()).append(", lokalizacja: ").append(event.getLocation().getAddress().getAddressLocality()).append(", opis: ").append(event.getDescription());
        }

        return info.toString();
    }

}