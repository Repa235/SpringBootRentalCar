package com.example.springbootrentalcar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
public class Vehicle implements Serializable {
    private static final long serialVersionUID = -4545225556642569508L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "car_brand")
    private String carBrand;

    @Column(name = "model")
    private String model;

    @Column(name = "registration_year")
    private int registrationYear;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rent> Rents = new HashSet<>();


}