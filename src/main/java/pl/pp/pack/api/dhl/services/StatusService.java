package pl.pp.pack.api.dhl.services;

import com.google.gson.*;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import pl.pp.pack.api.dhl.model.Shipments;

public class StatusService {
    public String getStatusByID(String trackingNumber)  {
       //trackingNumber = "7777777770";
        String sampleUrl = "https://api-eu.dhl.com/track/shipments?trackingNumber=" + trackingNumber;


        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setUri(sampleUrl)
                .setHeader("DHL-API-Key", "8RfuftKoJvIiOykVmnADOPdZrb92fmCv")
                .build();

        HttpResponse response;
        try {
            response = client.execute(httpUriRequest);
        } catch (IOException e) {
            e.printStackTrace();
            return "Problem z wykonaniem rządania";
        }


        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
            return "Nie poprawny klucz";
        } else {
            System.out.println(statusCode);
            String text;
            try {
                text = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
                return "Problem z uzyskaniem odpowiedzi";
            }
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                return LocalDateTime.parse(json.getAsString(), formatter);
            }).create();

            Shipments shipments = gson.fromJson(text, Shipments.class);

            System.out.println(shipments);
            if (shipments.getShipments() == null || shipments.getShipments().size() == 0) {
                return "Nie znaleziono paczki";
            }
            System.out.println(shipments.getShipments().get(0).getStatus());
            System.out.println(shipments.getShipments().get(0).getDestination());
            System.out.println(shipments.getShipments().get(0).getDetails());
            System.out.println(shipments.getShipments().get(0).getEvents());
            System.out.println(shipments.getShipments().get(0).getOrigin());
            System.out.println(shipments.getShipments().get(0).getService());

            return shipments.getShipments().get(0).toInfoString();
        }

    }
}
