package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.entities.BusDriver;
import com.busfleetproj.busfleetproj.repos.BusDriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BusDriverService {

    @Autowired
    BusDriverRepository busDriverRepository;

    @Autowired
    BusService busService;

    public List<BusDriver> getAllBusDrivers() {
        List<BusDriver> busDrivers = new ArrayList<>();
        log.info("Retrieving bus driver objects from database");
        busDriverRepository.findAll()
                .forEach(busDrivers::add);

        return busDrivers;
    }

    public BusDriver getBusDriver(int id) {
        Optional<BusDriver> busDriverOptional = busDriverRepository.findById(id);
        log.info("Retrieving bus driver object id#" + id + " from database");
        if (busDriverOptional.isPresent()) {
            log.info("Bus driver object id#" + id + "found in database");
            return busDriverOptional.get();
        } else {
            log.error("Bus driver object id#" + id + " not found in database");
            return new BusDriver();
        }
    }

    public void addBusDriver(BusDriver busDriver) {
        busDriverRepository.save(busDriver);
        log.info("Saving bus driver object to database");
    }

    public void deleteBusDriver(int id) {
        busDriverRepository.deleteById(id);
        log.info("Deleting bus driver object id#" + id + "from database");
    }

    public void updateBusDriver(int id, BusDriver busDriver) {
        busDriverRepository.save(busDriver);
        log.info("Updating bus driver object id#" + id + " in database");
    }

    public void changeBusDriverBus(int id, int bus_id) {

        List<Bus> busList = new ArrayList<>();
        List<BusDriver> busDrivers = new ArrayList<>();

        BusDriver busDriver = getBusDriver(id);             //get bus driver corresponding to id


        if (bus_id == -1) {
            busDriver.makeBusNull();
            log.info("Removing the bus driver assigned to bus object id#" + id);

        } else {

            log.info("Assigning bus object id#" + bus_id + " to bus driver id#" + id);
            Bus bus = busService.getBus(bus_id);                //get bus corresponding to id

            busDriver.setBus(bus);                              //setting the bus driver with the new bus

            bus.setBusDriver(busDriver);                        //setting the bus with the new bus driver
            busList.add(bus);                                   //adding new bus to update list


        }
        busDrivers.add(busDriver);
        busService.updateBuses(busList);                        //saving the updated buses
        busDriverRepository.saveAll(busDrivers);                //saving the updates bus drivers
        log.info("Saving changes to database");

    }
}


