package com.busfleetproj.busfleetproj.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "bus_driver")
@Entity
@Data
@Getter
@Setter
public class BusDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int driverId;

    @OneToOne
    @JoinColumn(name = "license_id")
    private DrivingLicense license;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;







}
