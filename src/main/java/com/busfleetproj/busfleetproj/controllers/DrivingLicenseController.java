package com.busfleetproj.busfleetproj.controllers;

import com.busfleetproj.busfleetproj.dto.DrivingLicenseDTO;
import com.busfleetproj.busfleetproj.entities.DrivingLicense;
import com.busfleetproj.busfleetproj.services.DrivingLicenseService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drivinglicenses")
@Api(value = "Driving License Management System")
public class DrivingLicenseController {

    @Autowired
    DrivingLicenseService drivingLicenseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View all driving license objects", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public List<DrivingLicense> getAllDrivingLicenses() {
        return drivingLicenseService.getAllDrivingLicenses();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View driving license object by id", response = DrivingLicense.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public DrivingLicense getDrivingLicense(
            @ApiParam(value = "Id of driving license object to retrieve", required = true)
            @PathVariable int id) {
        return drivingLicenseService.getDrivingLicense(id);
    }

    @PostMapping
    @ApiOperation(value = "Add driving license object to database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void addDrivingLicense(
            @ApiParam(value = "Driving license object to add")
            @Valid @RequestBody DrivingLicenseDTO drivingLicenseDTO) {
        DrivingLicense drivingLicense = new DrivingLicense();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(drivingLicenseDTO, drivingLicense);
        drivingLicenseService.addDrivingLicense(drivingLicense);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete driving license object from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void deleteDrivingLicense(
            @ApiParam(value = "Id of driving license object to delete", required = true)
            @PathVariable int id) {
        drivingLicenseService.deleteDrivingLicense(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update driving license object in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void updateDrivingLicense(
            @ApiParam(value = "Id of driving license object to update", required = true)
            @PathVariable int id,
            @ApiParam(value = "Updated riving license object", required = true)
            @Valid @RequestBody DrivingLicenseDTO drivingLicenseDTO) {
        DrivingLicense drivingLicense = new DrivingLicense();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(drivingLicenseDTO, drivingLicense);
        drivingLicenseService.updateDrivingLicense(id, drivingLicense);
    }
}
