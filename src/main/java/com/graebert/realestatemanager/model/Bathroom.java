package com.graebert.realestatemanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bathroom {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer area;
}
