package com.busfleetproj.busfleetproj.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Table(name = "student")
@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "first_name")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "No numbers, special characters or spaces" )
    private String firstName;

    @Column(name = "last_name")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "No numbers, special characters or spaces" )
    private String lastName;

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    public void makeBusNull() {
        bus = null;
    }

}

