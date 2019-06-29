package com.busfleetproj.busfleetproj.dto;

import com.busfleetproj.busfleetproj.entities.Route;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class BusDTO {


    private Route route;

    @Pattern(regexp = "^[a-zA-Z0-9_.-]*$", message = "Only letters and numbers")
    private String licensePlate;

    private int numberOfSeats;

    private String model;

    private int year;
}
