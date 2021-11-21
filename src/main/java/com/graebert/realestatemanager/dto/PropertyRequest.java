package com.graebert.realestatemanager.dto;

import com.graebert.realestatemanager.model.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequest {
    private Property property;

}
