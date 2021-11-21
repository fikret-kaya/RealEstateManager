package com.graebert.realestatemanager.repository;

import com.graebert.realestatemanager.dto.PropertyRequest;
import com.graebert.realestatemanager.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query("SELECT p FROM Property p")
    public List<PropertyRequest> getJoinInformation();
}
