package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.entities.BusDriver;
import com.busfleetproj.busfleetproj.entities.Route;
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

    @Autowired
    BusService busService;

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

    public void changeBusDriverBus(int id, int bus_id) {

        List<Bus> busList = new ArrayList<>();
        List<BusDriver> busDrivers = new ArrayList<>();

        BusDriver busDriver = getBusDriver(id);             //get bus driver corresponding to id


        if (bus_id == -1) {
            busDriver.makeBusNull();

        } else {

            Bus bus = busService.getBus(bus_id);                //get bus corresponding to id

            busDriver.setBus(bus);                              //setting the bus driver with the new bus

            bus.setBusDriver(busDriver);                        //setting the bus with the new bus driver
            busList.add(bus);                                   //adding new bus to update list


        }
        busDrivers.add(busDriver);
        busService.updateBuses(busList);                        //saving the updated buses
        busDriverRepository.saveAll(busDrivers);                //saving the updates bus drivers


    }
}


