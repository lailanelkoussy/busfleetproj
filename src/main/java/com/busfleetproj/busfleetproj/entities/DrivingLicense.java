package com.busfleetproj.busfleetproj.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Table(name = "driving_license")
@Entity
@Data
@Getter @Setter
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_id")
    private int licenseId;

    @Column(name = "start_date")
    private Calendar startDate;

    @Column(name = "expiry_date")
    private Calendar expiryDate;

    @OneToOne(mappedBy = "license")
    private BusDriver driver;
}
