package com.graebert.realestatemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PropertyResponse {
    private int price;
    private int totalArea;

    private int numberOfBedrooms;
    private List<Integer> bedroomAreas;

    private int numberOfBathrooms;
    private List<Integer> bathroomAreas;
}
