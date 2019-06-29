package com.busfleetproj.busfleetproj.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "bus_driver")
@Entity
@Data
@NoArgsConstructor
public class BusDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int driverId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @OneToOne
    @JoinColumn(name = "license_id")
    private DrivingLicense license;

    public void makeBusNull(){
        bus = null;
    }
}
