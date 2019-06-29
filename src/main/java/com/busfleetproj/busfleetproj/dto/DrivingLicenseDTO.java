package com.busfleetproj.busfleetproj.dto;

import com.busfleetproj.busfleetproj.entities.BusDriver;
import lombok.Data;

import java.util.Calendar;

@Data
public class DrivingLicenseDTO {

    private Calendar startDate;

    private Calendar expiryDate;

    private BusDriver driver;
}
