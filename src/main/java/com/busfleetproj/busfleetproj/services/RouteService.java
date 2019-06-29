package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Route;
import com.busfleetproj.busfleetproj.repos.RouteRepository;
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
@CacheConfig(cacheNames={"routes"})
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Cacheable
    public List<Route> getAllRoutes() {
        log.info("Retrieving route objects from database");
        return routeRepository.findAll();
    }

    @Cacheable(key = "#id")
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

    @CacheEvict(key = "#id")
    public void deleteRoute(int id) {
        routeRepository.deleteById(id);
        log.info("Deleting route object id#" + id + "from database");
    }

    @CachePut(key = "#id")
    public Route updateRoute(int id, Route route) {
        routeRepository.save(route);
        log.info("Updating route object id#" + id + " in database");
        return route;

    }

    @CacheEvict(allEntries = true)
    public void updateRoutes(List<Route> routes) {
        routeRepository.saveAll(routes);
        log.info("Updating route objects in database");
    }

    @CachePut(key = "#id")
    public Route updateCacheEntry(int id, Route route){
        return route;
    }
}
