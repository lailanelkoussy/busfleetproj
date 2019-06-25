package com.busfleetproj.busfleetproj.controllers;

import java.util.List;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //routeId == -1 if you want to remove route
    @RequestMapping(method = RequestMethod.PATCH, value = "/buses/{id}/route/id")
    public void changeBusRoute(@PathVariable int id, @RequestBody int routeId) {
        busService.changeBusRoute(id, routeId);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/buses/{id}/students/id")
    public ResponseEntity<Object> addStudents(@PathVariable int id, @RequestBody List<Integer> studentIds) {
        return new ResponseEntity<>(busService.addStudents(id, studentIds) ? HttpStatus.OK:HttpStatus.NOT_ACCEPTABLE );

    }

    @RequestMapping(method = RequestMethod.PATCH, value = "buses/{id}/students/id/remove")
    public void removeStudents(@PathVariable int id, @RequestBody List<Integer> studentIds){
        busService.removeStudents(id,studentIds);
    }



}
