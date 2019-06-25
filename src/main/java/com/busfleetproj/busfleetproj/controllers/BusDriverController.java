package com.busfleetproj.busfleetproj.controllers;

import com.busfleetproj.busfleetproj.entities.BusDriver;
import com.busfleetproj.busfleetproj.services.BusDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusDriverController {

    @Autowired
    BusDriverService busDriverService;

    @RequestMapping("/busdrivers")
    public List<BusDriver> getAllBusDrivers() {
        return busDriverService.getAllBusDrivers();
    }

    @RequestMapping("/busdrivers/{id}")
    public BusDriver getBusDriver(@PathVariable int id) {
        return busDriverService.getBusDriver(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/busdrivers")
    public void addBusDriver(@RequestBody BusDriver busDriver) {
        busDriverService.addBusDriver(busDriver);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/busdrivers/{id}")
    public void deleteBusDriver(@PathVariable int id) {
        busDriverService.deleteBusDriver(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/busdrivers/{id}")
    public void updateBusDriver(@PathVariable int id, @RequestBody BusDriver busDriver){
        busDriverService.updateBusDriver(id, busDriver);
    }

    //bus_id == -1 if you want to remove bus
    @RequestMapping(method = RequestMethod.PATCH, value = "/busdrivers/{id}/bus/id")
    public void changeBusRoute(@PathVariable int id, @RequestBody int bus_id) {
        busDriverService.changeBusDriverBus(id, bus_id);
    }
}
