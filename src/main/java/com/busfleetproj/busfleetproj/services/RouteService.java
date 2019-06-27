package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Route;
import com.busfleetproj.busfleetproj.repos.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        log.info("Retrieving route objects from database");
        routeRepository.findAll()
                .forEach(routes::add);
        return routes;
    }

    public Route getRoute(int id) {

        Optional<Route> routeOptional = routeRepository.findById(id);
        log.info("Retrieving route object id#" + id + " from database");
        if (routeOptional.isPresent()) {
            log.info("Retrieving route object id#" + id + " from database");
            return routeOptional.get();
        } else {
            log.error("Route object id#" + id + " not found in database");
            return new Route();
        }

    }

    public void addRoute(Route route) {
        routeRepository.save(route);
        log.info("Saving route object to database");
    }

    public void deleteRoute(int id) {
        routeRepository.deleteById(id);
        log.info("Deleting route object id#" + id + "from database");
    }

    public void updateRoute(int id, Route route) {
        routeRepository.save(route);
        log.info("Updating route object id#" + id + " in database");

    }

    public void updateRoutes(List<Route> routes) {
        routeRepository.saveAll(routes);
        log.info("Updating route objects in database");
    }
}
