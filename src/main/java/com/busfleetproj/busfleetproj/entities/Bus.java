package com.busfleetproj.busfleetproj.entities;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Table(name = "bus")
@Entity
@Data

public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private int busId;

    @OneToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    private String model;

    private int year;

    @OneToMany(mappedBy = "bus")
    private List<Student> students;

    @OneToOne(mappedBy = "bus")
    private BusDriver busDriver;

    @OneToOne(mappedBy = "bus")
    private Assistant assistant;
}
