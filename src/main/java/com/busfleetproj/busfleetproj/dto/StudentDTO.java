package com.busfleetproj.busfleetproj.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class StudentDTO {

    @Pattern(regexp = "^[a-zA-Z]+$", message = "No numbers, special characters or spaces" )
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "No numbers, special characters or spaces" )
    private String lastName;

    private String address;
}
