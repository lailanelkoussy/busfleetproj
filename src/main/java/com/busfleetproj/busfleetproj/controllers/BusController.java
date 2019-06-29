package com.busfleetproj.busfleetproj.controllers;

import java.util.List;

import com.busfleetproj.busfleetproj.dto.BusDTO;
import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.services.BusService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/buses")
@Api(value = "Bus Management System")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View all buses", response = List.class)

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View bus by id", response = Bus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public Bus getBus(
            @ApiParam(value = "Id of bus to retrieve", required = true)
            @PathVariable int id) {
        return busService.getBus(id);
    }

    @PostMapping
    @ApiOperation(value = "Add bus to database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void addBus(
            @ApiParam(value = "Bus object to add to database", required = true)
            @Valid @RequestBody BusDTO busDTO) {
        Bus bus = new Bus();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(busDTO, bus);
        busService.addBus(bus);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove bus from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void removeBus(
            @ApiParam(value = "Id of bus to delete from database", required = true)
            @PathVariable int id) {
        busService.deleteBus(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update bus data in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void updateBus(
            @ApiParam(value = "Id of bus to update in database", required = true)
            @PathVariable int id,
            @ApiParam(value = "Updated bus object")
            @Valid @RequestBody BusDTO busDTO) {
        Bus bus = new Bus();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(busDTO, bus);
        busService.updateBus(id, bus);
    }

    @PatchMapping(value = "/{id}/route/id")
    @ApiOperation("Assign route to bus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully assigned object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void changeBusRoute(
            @ApiParam(value = "Id of bus to assign to a new route", required = true)
            @PathVariable int id,
            @ApiParam(value = "Id of new route to assign to bus, -1 if wish to have no route assigned to bus")
            @RequestBody int routeId) {
        busService.changeBusRoute(id, routeId);
    }

    @PatchMapping(value = "/{id}/students/id")
    @ApiOperation("Assign students to bus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully assigned list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 406, message = "The number of students you wish to add exceeds the bus capacity")})
    public ResponseEntity<Object> addStudents(
            @ApiParam(value = "Id of bus to add students to", required = true)
            @PathVariable int id,
            @ApiParam(value = "List of student ids to add to bus")
            @RequestBody List<Integer> studentIds) {
        return new ResponseEntity<>(busService.addStudents(id, studentIds) ? HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE);
    }

    @PatchMapping("/{id}/students/id/remove")
    @ApiOperation("Remove students from bus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully removed list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void removeStudents(
            @ApiParam(value = "Id of bus to remove students from", required = true)
            @PathVariable int id,
            @ApiParam(value = "List of student ids to remove from bus", required = true)
            @RequestBody List<Integer> studentIds) {
        busService.removeStudents(id, studentIds);
    }


}
