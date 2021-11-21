package com.graebert.realestatemanager.controller;

import com.google.gson.Gson;
import com.graebert.realestatemanager.dto.PropertyRequest;
import com.graebert.realestatemanager.dto.PropertyResponse;
import com.graebert.realestatemanager.dto.CustomerRequest;
import com.graebert.realestatemanager.model.Property;
import com.graebert.realestatemanager.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final RealEstateService realEstateService;

    @PostMapping("/insert")
    public ResponseEntity<String> placeOrder(@RequestBody PropertyRequest request) {
        if (realEstateService.insertApartment(request)) {
            return ResponseEntity.ok(new Gson().toJson("Property added to the database"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Gson().toJson("Property not added to the database"));
        }
    }

    @GetMapping("/find")
    public ResponseEntity<String> getOrders(@RequestBody CustomerRequest request) {
        PropertyResponse response = realEstateService.findApartment(request);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Gson().toJson("No property found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(response));
    }
}
