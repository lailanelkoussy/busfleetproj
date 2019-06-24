package com.busfleetproj.busfleetproj.repos;

import com.busfleetproj.busfleetproj.entities.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,Integer> {

}