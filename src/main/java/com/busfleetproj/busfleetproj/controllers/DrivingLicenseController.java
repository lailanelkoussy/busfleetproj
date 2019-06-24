package com.busfleetproj.busfleetproj.controllers;

import com.busfleetproj.busfleetproj.entities.DrivingLicense;
import com.busfleetproj.busfleetproj.services.DrivingLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DrivingLicenseController {

    @Autowired
    DrivingLicenseService drivingLicenseService;

    @RequestMapping("/drivinglicenses")
    public List<DrivingLicense> getAllDrivingLicenses(){
        return drivingLicenseService.getAllDrivingLicenses();
    }

    @RequestMapping("/drivinglicenses/{id}")
    public DrivingLicense getDrivingLicense(@PathVariable int id){
        return drivingLicenseService.getDrivingLicense(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/drivinglicenses")
    public void addDrivingLicense(@RequestBody DrivingLicense drivingLicense){
        drivingLicenseService.addDrivingLicense(drivingLicense);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/drivinglicenses/{id}")
    public void deleteDrivingLicense(@PathVariable int id){
        drivingLicenseService.deleteDrivingLicense(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/drivinglicenses/{id}")
    public void updateDrivingLicense(@PathVariable int id, @RequestBody DrivingLicense drivingLicense){
        drivingLicenseService.updateDrivingLicense(id, drivingLicense);
    }
}
