package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.DrivingLicense;
import com.busfleetproj.busfleetproj.repos.DrivingLicenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DrivingLicenseService {

    @Autowired
    DrivingLicenseRepository drivingLicenseRepository;

    public List<DrivingLicense> getAllDrivingLicenses() {
        List<DrivingLicense> drivingLicenses = new ArrayList<>();
        log.info("Retrieving driving license objects from database");
        drivingLicenseRepository.findAll()
                .forEach(drivingLicenses::add);

        return drivingLicenses;
    }

    public DrivingLicense getDrivingLicense(int id) {
        Optional<DrivingLicense> drivingLicenseOptional = drivingLicenseRepository.findById(id);
        log.info("Retrieving driving license object id#" + id + " from database");
        if (drivingLicenseOptional.isPresent()) {
            log.info("Driving license object id#" + id + "found in database");
            return drivingLicenseOptional.get();
        } else {
            log.error("Driving license object id#" + id + " not found in database");
            return new DrivingLicense();
        }
    }

    public void addDrivingLicense(DrivingLicense drivingLicense) {
        drivingLicenseRepository.save(drivingLicense);
        log.info("Saving driving license object to database");
    }

    public void deleteDrivingLicense(int id) {
        drivingLicenseRepository.deleteById(id);
        log.info("Deleting driving license object id#" + id + "from database");
    }

    public void updateDrivingLicense(int id, DrivingLicense drivingLicense) {
        drivingLicenseRepository.save(drivingLicense);
        log.info("Updating driving license object id#" + id + " in database");
    }

}
