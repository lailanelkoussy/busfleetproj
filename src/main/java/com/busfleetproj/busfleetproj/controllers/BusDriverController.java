package com.busfleetproj.busfleetproj.controllers;

import com.busfleetproj.busfleetproj.dto.BusDriverDTO;
import com.busfleetproj.busfleetproj.entities.BusDriver;
import com.busfleetproj.busfleetproj.services.BusDriverService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/busdrivers")
@Api(value = "Bus Driver Management System")
public class BusDriverController {

    @Autowired
    BusDriverService busDriverService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View all bus driver objects", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public List<BusDriver> getAllBusDrivers() {
        return busDriverService.getAllBusDrivers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View bus driver object by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public BusDriver getBusDriver(
            @ApiParam(value = "Id of bus driver to retrieve", required = true)
            @PathVariable int id) {
        return busDriverService.getBusDriver(id);
    }

    @PostMapping
    @ApiOperation(value = "Add bus driver object to database", response = BusDriver.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void addBusDriver(
            @ApiParam(value = "Bus driver object to add to database", required = true)
            @Valid @RequestBody BusDriverDTO busDriverDTO) {
        BusDriver busDriver = new BusDriver();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(busDriverDTO, busDriver);
        busDriverService.addBusDriver(busDriver);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete bus driver object from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void deleteBusDriver(
            @ApiParam(value = "Id of bus driver to delete", required = true)
            @PathVariable int id) {
        busDriverService.deleteBusDriver(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update bus driver object in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void updateBusDriver(
            @ApiParam(value = "Id of bus driver to update", required = true)
            @PathVariable int id,
            @ApiParam(value = "Updated bus driver object", required = true)
            @Valid @RequestBody BusDriverDTO busDriverDTO) {
        BusDriver busDriver = new BusDriver();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(busDriverDTO, busDriver);
        busDriverService.updateBusDriver(id, busDriver);
    }

    @PatchMapping(value = "/{id}/bus/id")
    @ApiOperation(value = "Change bus assigned to bus driver")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully assigned object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void changeBusRoute(
            @ApiParam(value = "Id of bus driver to assign to route", required = true)
            @PathVariable int id,
            @ApiParam(value = "Id of bus to assign bus driver to, -1 if wish to remove bus driver's bus only")
            @RequestBody int bus_id) {
        busDriverService.changeBusDriverBus(id, bus_id);
    }
}
