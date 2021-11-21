package com.graebert.realestatemanager.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Property {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer area;
    @Column(nullable = false)
    private Integer price;

    @OneToMany(targetEntity = Bedroom.class, cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "property_fk", referencedColumnName = "id")
    @NotNull
    private List<Bedroom> bedrooms;

    @OneToMany(targetEntity = Bathroom.class, cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "property_fk", referencedColumnName = "id")
    @NotNull
    private List<Bathroom> bathrooms;
}
