package com.busfleetproj.busfleetproj.dto;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.entities.DrivingLicense;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class BusDriverDTO {


    @Pattern(regexp = "^[a-zA-Z]+$", message = "No numbers, special characters or spaces" )
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "No numbers, special characters or spaces" )
    private String lastName;

    private Bus bus;

    @NotNull
    private DrivingLicense license;
}
