package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.repos.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        busRepository.findAll()
                .forEach(buses::add);

        return buses;
    }

    public Bus getBus(int id) {

        Optional<Bus> busOptional = busRepository.findById(id);
        if (busOptional.isPresent())
            return busOptional.get();
        else
            return new Bus();

    }

    public void addBus(Bus bus) {
        busRepository.save(bus);
    }

    public void deleteBus(int id) {
        busRepository.deleteById(id);
    }

    public void updateBus(int id, Bus bus){
        busRepository.save(bus);
    }

}
