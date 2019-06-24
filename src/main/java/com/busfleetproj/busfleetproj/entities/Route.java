package com.busfleetproj.busfleetproj.entities;

import lombok.Data;

import javax.persistence.*;

import java.util.List;


@Table(name = "route")
@Entity
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private int routeId;

    private String area;

    @Column(name = "start_point")
    private String startPoint;

    @Column(name = "end_point")
    private String endPoint;

    @OneToMany(mappedBy = "route")
    private List<Student> students;

    @OneToOne(mappedBy = "route")
    private Bus bus;


}
