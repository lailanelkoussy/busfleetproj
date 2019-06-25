package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Route;
import com.busfleetproj.busfleetproj.repos.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        routeRepository.findAll()
                .forEach(routes::add);
        return routes;
    }

    public Route getRoute(int id) {

        Optional<Route> routeOptional = routeRepository.findById(id);
        if (routeOptional.isPresent())
            return routeOptional.get();
        else
            return new Route();

    }

    public void addRoute(Route route) {
        routeRepository.save(route);
    }

    public void deleteRoute(int id) {
        routeRepository.deleteById(id);
    }

    public void updateRoute(int id, Route route){
        routeRepository.save(route);
    }

    public void updateRoutes(List<Route> routes){
        routeRepository.saveAll(routes);
    }
}
