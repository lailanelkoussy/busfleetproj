package com.busfleetproj.busfleetproj.dto;

import com.busfleetproj.busfleetproj.entities.Bus;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class AssistantDTO {

    @Pattern(regexp = "^[a-zA-Z]+$", message = "No numbers, special characters or spaces")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "No numbers, special characters or spaces" )
    private String lastName;

    private Bus bus;
}
