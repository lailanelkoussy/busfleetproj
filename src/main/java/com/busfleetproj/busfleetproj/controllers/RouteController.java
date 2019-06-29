package com.busfleetproj.busfleetproj.controllers;

import java.util.List;

import com.busfleetproj.busfleetproj.dto.RouteDTO;
import com.busfleetproj.busfleetproj.entities.Route;
import com.busfleetproj.busfleetproj.services.RouteService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/routes")
@Api(value = "Route Management System")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View all route objects", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View route object by id", response = Route.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public Route getRoute(
            @ApiParam(value = "Id of route object to view", required = true)
            @PathVariable int id) {
        return routeService.getRoute(id);
    }

    @PostMapping
    @ApiOperation(value = "Add route object to database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void addRoute(
            @ApiParam(value = "Route object to add to database", required = true)
            @Valid @RequestBody RouteDTO routeDTO) {
        Route route = new Route();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(routeDTO, route);
        routeService.addRoute(route);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete route object from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void deleteRoute(
            @ApiParam(value = "Id of route object to delete", required = true)
            @PathVariable int id) {
        routeService.deleteRoute(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update route object in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void updateRoute(
            @ApiParam(value = "Id of route object to update", required = true)
            @PathVariable int id,
            @ApiParam(value = "Updated route object", required = true)
            @Valid @RequestBody RouteDTO routeDTO) {
        Route route = new Route();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(routeDTO, route);
        routeService.updateRoute(id, route);
    }


}
