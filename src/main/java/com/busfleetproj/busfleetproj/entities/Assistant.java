package com.busfleetproj.busfleetproj.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "assistant")
@Entity
@Data
@NoArgsConstructor
public class Assistant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assistant_id")
    private int assistantId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    public void makeBusNull(){
        bus = null;
    }

}