package com.busfleetproj.busfleetproj.controllers;

import java.util.List;

import com.busfleetproj.busfleetproj.entities.Route;
import com.busfleetproj.busfleetproj.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping("/routes")
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @RequestMapping("/routes/{id}")
    public Route getRoute(@PathVariable int id) {
        return routeService.getRoute(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/routes")
    public void addRoute(@RequestBody Route route) {
        routeService.addRoute(route);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/routes/{id}")
    public void deleteRoute(@PathVariable int id) {
        routeService.deleteRoute(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/routes/{id}")
    public void updateRoute(@PathVariable int id, @RequestBody Route route) {
        routeService.updateRoute(id, route);
    }


}
