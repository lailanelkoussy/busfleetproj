package com.busfleetproj.busfleetproj.controllers;

import java.util.List;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusController {

    @Autowired
    private BusService busService;

    @RequestMapping("/buses")
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    @RequestMapping("buses/{id}")
    public Bus getBus(@PathVariable int id) {
        return busService.getBus(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/buses")
    public void addBus(@RequestBody Bus bus) {
        busService.addBus(bus);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/buses/{id}")
    public void removeBus(@PathVariable int id) {
        busService.deleteBus(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/buses/{id}")
    public void updateBus(@RequestBody Bus bus, @PathVariable int id) {
        busService.updateBus(id, bus);
    }
}
