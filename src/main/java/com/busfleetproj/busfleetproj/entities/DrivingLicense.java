package com.busfleetproj.busfleetproj.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Table(name = "driving_license")
@Entity
@Data
@NoArgsConstructor
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_id")
    private int licenseId;

    @Column(name = "start_date")
    private Calendar startDate;

    @Column(name = "expiry_date")
    private Calendar expiryDate;

    @JsonIgnore
    @OneToOne(mappedBy = "license")
    private BusDriver driver;
}
