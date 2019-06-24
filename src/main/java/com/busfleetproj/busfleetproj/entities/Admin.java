package com.busfleetproj.busfleetproj.entities;


import lombok.Data;


import javax.persistence.*;

@Table(name = "admin")
@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;

    private String name;
}
