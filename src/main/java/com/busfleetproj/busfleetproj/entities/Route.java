package com.busfleetproj.busfleetproj.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(mappedBy = "route")
    private List<Student> students;

    @OneToOne(mappedBy = "route")
    private Bus bus;


}
