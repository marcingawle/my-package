package pl.pp.pack.api.dhl.model;

import lombok.Data;

@Data
public class Details {
    private boolean proofOfDeliverySignedAvailable;
    private int totalNumberOfPieces;
}