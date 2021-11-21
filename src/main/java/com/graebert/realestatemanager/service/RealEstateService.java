package com.graebert.realestatemanager.service;

import com.graebert.realestatemanager.dto.PropertyRequest;
import com.graebert.realestatemanager.dto.PropertyResponse;
import com.graebert.realestatemanager.dto.CustomerRequest;
import com.graebert.realestatemanager.model.Property;

import java.util.List;

public interface RealEstateService {

    // insert an apartment to the database
    boolean insertApartment(PropertyRequest request);

    // find a suitable apartment by given filters
    PropertyResponse findApartment(CustomerRequest request);
}
