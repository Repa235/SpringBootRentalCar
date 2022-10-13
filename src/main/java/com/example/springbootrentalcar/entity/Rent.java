package com.example.springbootrentalcar.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "RENT")
@Data
public class Rent implements Serializable {
    private static final long serialVersionUID = 3381529742555952208L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate")
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "isApproved")
    private boolean isApproved;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "vehicleId", referencedColumnName = "id")
    @JsonBackReference
    private Vehicle vehicle;

}
