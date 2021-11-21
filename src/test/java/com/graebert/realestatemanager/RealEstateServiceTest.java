package com.graebert.realestatemanager;

import com.graebert.realestatemanager.dto.CustomerRequest;
import com.graebert.realestatemanager.dto.PropertyRequest;
import com.graebert.realestatemanager.dto.PropertyResponse;
import com.graebert.realestatemanager.model.Bathroom;
import com.graebert.realestatemanager.model.Bedroom;
import com.graebert.realestatemanager.model.Property;
import com.graebert.realestatemanager.service.RealEstateService;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class RealEstateServiceTest {

    @Autowired
    private RealEstateService realEstateService;

    @Test
    @Order(1)
    public void successTestAddPropertyOne() {
        assertEquals(true, realEstateService.insertApartment(getPropert1()));
    }

    @Test
    @Order(2)
    public void successTestAddPropertyTwo() {
        assertEquals(true, realEstateService.insertApartment(getPropert2()));
    }

    @Test
    @Order(3)
    public void successTestAddPropertyThree() {
        assertEquals(true, realEstateService.insertApartment(getPropert3()));
    }

    @Test
    @Order(4)
    public void successTestAddPropertyFour() {
        assertEquals(true, realEstateService.insertApartment(getPropert4()));
    }

    @Test
    @Order(5)
    public void failTestAddPropertyMissingField() {
        assertEquals(false, realEstateService.insertApartment(getPropert5()));

    }

    @Test
    @Order(6)
    public void successTestFindProperty() {
        realEstateService.insertApartment(getPropert1());
        realEstateService.insertApartment(getPropert2());
        realEstateService.insertApartment(getPropert3());
        realEstateService.insertApartment(getPropert4());

        CustomerRequest request = new CustomerRequest();
        request.setPrice(255000);
        request.setTotalArea(90);
        request.setMinNumberOfBedrooms(2);
        request.setBedroomAreas(Arrays.asList(19, 12));
        request.setMinNumberOfBathrooms(1);
        request.setBathroomAreas(Arrays.asList(10));

        PropertyResponse response = new PropertyResponse();
        response.setPrice(250000);
        response.setTotalArea(90);
        response.setNumberOfBedrooms(3);
        response.setBedroomAreas(Arrays.asList(12, 15, 20));
        response.setNumberOfBathrooms(1);
        response.setBathroomAreas(Arrays.asList(10));

        assertEquals(response, realEstateService.findApartment(request));
    }

    @Test
    @Order(7)
    public void failTestFindPropertyNoProperty() {
        CustomerRequest request = new CustomerRequest();
        request.setPrice(255000);
        request.setTotalArea(90);
        request.setMinNumberOfBedrooms(2);
        request.setBedroomAreas(Arrays.asList(19, 12));
        request.setMinNumberOfBathrooms(1);
        request.setBathroomAreas(Arrays.asList(10));

        assertEquals(null, realEstateService.findApartment(request));
    }

    @Test
    @Order(8)
    public void failTestFindPropertyNoSuitableProperty() {
        realEstateService.insertApartment(getPropert3());

        CustomerRequest request = new CustomerRequest();
        request.setPrice(255000);
        request.setTotalArea(90);
        request.setMinNumberOfBedrooms(2);
        request.setBedroomAreas(Arrays.asList(19, 12));
        request.setMinNumberOfBathrooms(1);
        request.setBathroomAreas(Arrays.asList(10));

        assertEquals(null, realEstateService.findApartment(request));
    }

    private PropertyRequest getPropert1() {
        Property property = new Property();
        property.setArea(90);
        property.setPrice(250000);

        List<Bedroom> bedrooms = new ArrayList<Bedroom>();
        Bedroom bedroom1 = new Bedroom();
        bedroom1.setArea(12);
        Bedroom bedroom2 = new Bedroom();
        bedroom2.setArea(15);
        Bedroom bedroom3 = new Bedroom();
        bedroom3.setArea(20);
        bedrooms.add(bedroom1);
        bedrooms.add(bedroom2);
        bedrooms.add(bedroom3);
        property.setBedrooms(bedrooms);

        List<Bathroom> bathrooms = new ArrayList<Bathroom>();
        Bathroom b = new Bathroom();
        b.setArea(10);
        bathrooms.add(b);
        property.setBathrooms(bathrooms);

        PropertyRequest request = new PropertyRequest();
        request.setProperty(property);
        return request;
    }

    private PropertyRequest getPropert2() {
        Property property = new Property();
        property.setArea(90);
        property.setPrice(245000);

        List<Bedroom> bedrooms = new ArrayList<Bedroom>();
        Bedroom bedroom1 = new Bedroom();
        bedroom1.setArea(12);
        Bedroom bedroom2 = new Bedroom();
        bedroom2.setArea(15);
        Bedroom bedroom3 = new Bedroom();
        bedroom3.setArea(20);
        bedrooms.add(bedroom1);
        bedrooms.add(bedroom2);
        bedrooms.add(bedroom3);
        property.setBedrooms(bedrooms);

        List<Bathroom> bathrooms = new ArrayList<Bathroom>();
        Bathroom b = new Bathroom();
        b.setArea(8);
        bathrooms.add(b);
        property.setBathrooms(bathrooms);

        PropertyRequest request = new PropertyRequest();
        request.setProperty(property);
        return request;
    }

    private PropertyRequest getPropert3() {
        Property property = new Property();
        property.setArea(90);
        property.setPrice(260000);

        List<Bedroom> bedrooms = new ArrayList<Bedroom>();
        Bedroom bedroom1 = new Bedroom();
        bedroom1.setArea(12);
        Bedroom bedroom2 = new Bedroom();
        bedroom2.setArea(15);
        Bedroom bedroom3 = new Bedroom();
        bedroom3.setArea(20);
        bedrooms.add(bedroom1);
        bedrooms.add(bedroom2);
        bedrooms.add(bedroom3);
        property.setBedrooms(bedrooms);

        List<Bathroom> bathrooms = new ArrayList<Bathroom>();
        Bathroom b = new Bathroom();
        b.setArea(10);
        bathrooms.add(b);
        property.setBathrooms(bathrooms);

        PropertyRequest request = new PropertyRequest();
        request.setProperty(property);
        return request;
    }

    private PropertyRequest getPropert4() {
        Property property = new Property();
        property.setArea(120);
        property.setPrice(375000);

        List<Bedroom> bedrooms = new ArrayList<Bedroom>();
        Bedroom bedroom1 = new Bedroom();
        bedroom1.setArea(18);
        Bedroom bedroom2 = new Bedroom();
        bedroom2.setArea(30);
        bedrooms.add(bedroom1);
        bedrooms.add(bedroom2);
        property.setBedrooms(bedrooms);

        List<Bathroom> bathrooms = new ArrayList<Bathroom>();
        Bathroom b1 = new Bathroom();
        b1.setArea(16);
        Bathroom b2 = new Bathroom();
        b2.setArea(11);
        bathrooms.add(b1);
        bathrooms.add(b2);
        property.setBathrooms(bathrooms);

        PropertyRequest request = new PropertyRequest();
        request.setProperty(property);
        return request;
    }

    private PropertyRequest getPropert5() {
        Property property = new Property();
        //property.setArea(90);
        property.setPrice(250000);

        List<Bedroom> bedrooms = new ArrayList<Bedroom>();
        Bedroom bedroom1 = new Bedroom();
        bedroom1.setArea(12);
        Bedroom bedroom2 = new Bedroom();
        bedroom2.setArea(15);
        Bedroom bedroom3 = new Bedroom();
        bedroom3.setArea(20);
        bedrooms.add(bedroom1);
        bedrooms.add(bedroom2);
        bedrooms.add(bedroom3);
        property.setBedrooms(bedrooms);

        List<Bathroom> bathrooms = new ArrayList<Bathroom>();
        Bathroom b = new Bathroom();
        b.setArea(10);
        bathrooms.add(b);
        property.setBathrooms(bathrooms);

        PropertyRequest request = new PropertyRequest();
        request.setProperty(property);
        return request;
    }
}
