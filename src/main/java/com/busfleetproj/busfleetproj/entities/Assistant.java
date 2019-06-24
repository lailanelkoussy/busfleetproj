package com.busfleetproj.busfleetproj.entities;

import lombok.Data;

import javax.persistence.*;

@Table(name = "assistant")
@Entity
@Data
public class Assistant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assistant_id")
    private int assistantId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "route_id")
    private int routeId;

    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

}