package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.entities.BusDriver;
import com.busfleetproj.busfleetproj.repos.BusDriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = {"busdrivers"})
public class BusDriverService {

    @Autowired
    BusDriverRepository busDriverRepository;

    @Autowired
    BusService busService;

    @Cacheable
    public List<BusDriver> getAllBusDrivers() {
        log.info("Retrieving bus driver objects from database");

        return busDriverRepository.findAll();
    }

    @Cacheable(key = "#id")
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

    @CacheEvict(key = "#id")
    public void deleteBusDriver(int id) {
        busDriverRepository.deleteById(id);
        log.info("Deleting bus driver object id#" + id + "from database");
    }

    @CachePut(key = "#id")
    public void updateBusDriver(int id, BusDriver busDriver) {
        busDriverRepository.save(busDriver);
        log.info("Updating bus driver object id#" + id + " in database");
    }

    @CachePut(key = "#id")
    public BusDriver changeBusDriverBus(int id, int bus_id) {

        BusDriver busDriver = getBusDriver(id);             //get bus driver corresponding to id

        if (bus_id == -1) {
            busDriver.makeBusNull();
            log.info("Removing the bus driver assigned to bus object id#" + id);

        } else {

            log.info("Assigning bus object id#" + bus_id + " to bus driver id#" + id);
            Bus bus = busService.getBus(bus_id);                //get bus corresponding to id

            busDriver.setBus(bus);                              //setting the bus driver with the new bus

            bus.setBusDriver(busDriver);                        //setting the bus with the new bus driver
            busService.updateBus(bus_id, bus);                   //saving the updated bus
            busService.updateCacheEntry(bus_id, bus);

        }
        busDriverRepository.save(busDriver);                //saving the updates bus drivers
        log.info("Saving changes to database");
        return busDriver;
    }

    @CachePut(key = "#id")
    public BusDriver updateCacheEntry(int id, BusDriver busDriver) {
        return busDriver;
    }
}


