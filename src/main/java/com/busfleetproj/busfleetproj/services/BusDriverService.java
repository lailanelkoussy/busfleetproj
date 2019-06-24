package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.BusDriver;
import com.busfleetproj.busfleetproj.repos.BusDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusDriverService {

    @Autowired
    BusDriverRepository busDriverRepository;

    public List<BusDriver> getAllBusDrivers() {
        List<BusDriver> busDrivers = new ArrayList<>();
        busDriverRepository.findAll()
                .forEach(busDrivers::add);

        return busDrivers;
    }

    public BusDriver getBusDriver(int id) {
        Optional<BusDriver> busDriverOptional = busDriverRepository.findById(id);
        if (busDriverOptional.isPresent())
            return busDriverOptional.get();
        else
            return new BusDriver();
    }

    public void addBusDriver(BusDriver busDriver) {
        busDriverRepository.save(busDriver);
    }

    public void deleteBusDriver(int id) {
        busDriverRepository.deleteById(id);
    }

    public void updateBusDriver(int id, BusDriver busDriver) {
        busDriverRepository.save(busDriver);
    }

}
