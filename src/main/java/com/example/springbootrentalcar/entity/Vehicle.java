package com.example.springbootrentalcar.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "VEHICLE")
@Data
public class Vehicle implements Serializable {
    private static final long serialVersionUID = -4545225556642569508L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "carBrand")
    private String carBrand;

    @Column(name = "model")
    private String model;

    @Column(name = "registrationYear")
    private int registrationYear;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Rent> Rents = new HashSet<>();


}