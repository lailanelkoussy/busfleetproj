package com.busfleetproj.busfleetproj.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Table(name = "route")
@Entity
@Data
@NoArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "route")
    private List<Student> students;

    @JsonIgnore
    @OneToOne(mappedBy = "route")
    private Bus bus;


}
