package com.example.springbootrentalcar.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -9032770611804973466L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "isAdmin")
    private boolean isAdmin;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Rent> Rents = new HashSet<>();

}

