package com.graebert.realestatemanager.service.impl;

import com.graebert.realestatemanager.dto.PropertyRequest;
import com.graebert.realestatemanager.dto.PropertyResponse;
import com.graebert.realestatemanager.dto.CustomerRequest;
import com.graebert.realestatemanager.model.Bathroom;
import com.graebert.realestatemanager.model.Bedroom;
import com.graebert.realestatemanager.model.Property;
import com.graebert.realestatemanager.repository.PropertyRepository;
import com.graebert.realestatemanager.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RealEstateServiceImpl implements RealEstateService {

    private final PropertyRepository propertyRepository;

    @Override
    public boolean insertApartment(PropertyRequest request) {
        if (request.getProperty() != null &&
                (request.getProperty().getBedrooms().size() == 0 ||
                        request.getProperty().getBathrooms().size() == 0)) {
            return false;
        }
        try {
            propertyRepository.save(request.getProperty());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PropertyResponse findApartment(CustomerRequest request) {
        List<Property> properties = propertyRepository.findAll();
        if (request.getPrice() == 0 || properties == null || properties.size() == 0) {
            return null;
        }

        Property bestProperty = null;
        for (Property p : properties)  {
            if (request.getPrice() >= p.getPrice()) {
                if (bestProperty != null && p.getPrice() <= bestProperty.getPrice()) {
                    continue;
                }
                if (request.getTotalArea() <= p.getArea() &&
                        request.getMinNumberOfBedrooms() <= p.getBedrooms().size() &&
                        request.getMinNumberOfBathrooms() <= p.getBathrooms().size() &&
                        checkLists(request.getBedroomAreas(), p.getBedrooms()) &&
                        checkLists(request.getBathroomAreas(), p.getBathrooms())) {

                    bestProperty = p;
                }
            }
        }

        if (bestProperty == null) {
            return null;
        }
        return prepareApartmentResponse(bestProperty);
    }

    // check bedrooms or bathrooms satisfies minimum requirements
    private boolean checkLists(List<Integer> required, List existing) {
        List<Integer> rooms = new ArrayList<>();
        if (existing.get(0) instanceof Bedroom) {
            for (int i = 0; i < existing.size(); i++) {
                rooms.add(((Bedroom) existing.get(i)).getArea());
            }
        } else {
            for (int i = 0; i < existing.size(); i++) {
                rooms.add(((Bathroom) existing.get(i)).getArea());
            }
        }

        Collections.sort(required, Collections.reverseOrder());
        Collections.sort(rooms, Collections.reverseOrder());
        for (int i = 0; i < required.size(); i++) {
            if (required.get(i) > rooms.get(i)) {
                return false;
            }
        }

        return true;
    }

    // prepare the response object
    private PropertyResponse prepareApartmentResponse(Property property) {
        PropertyResponse response = new PropertyResponse();

        response.setPrice(property.getPrice());
        response.setTotalArea(property.getArea());

        List<Integer> bedroomList = new ArrayList<>();
        response.setNumberOfBedrooms(property.getBedrooms().size());
        for (Bedroom b : property.getBedrooms()) {
            bedroomList.add(b.getArea());
        }
        response.setBedroomAreas(bedroomList);

        List<Integer> bathroomList = new ArrayList<>();
        response.setNumberOfBathrooms(property.getBathrooms().size());
        for (Bathroom b : property.getBathrooms()) {
            bathroomList.add(b.getArea());
        }
        response.setBathroomAreas(bathroomList);

        return response;
    }
}
