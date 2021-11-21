package com.graebert.realestatemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private int price;
    private int totalArea;

    private int minNumberOfBedrooms;
    private List<Integer> bedroomAreas;

    private int minNumberOfBathrooms;
    private List<Integer> bathroomAreas;
}
