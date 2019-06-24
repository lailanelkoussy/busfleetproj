package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.DrivingLicense;
import com.busfleetproj.busfleetproj.repos.DrivingLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrivingLicenseService {

    @Autowired
    DrivingLicenseRepository drivingLicenseRepository;

    public List<DrivingLicense> getAllDrivingLicenses() {
        List<DrivingLicense> drivingLicenses = new ArrayList<>();
        drivingLicenseRepository.findAll()
                .forEach(drivingLicenses::add);

        return drivingLicenses;
    }

    public DrivingLicense getDrivingLicense(int id) {
        Optional<DrivingLicense> drivingLicenseOptional = drivingLicenseRepository.findById(id);
        if (drivingLicenseOptional.isPresent())
            return drivingLicenseOptional.get();
        else
            return new DrivingLicense();
    }

    public void addDrivingLicense(DrivingLicense drivingLicense) {
        drivingLicenseRepository.save(drivingLicense);
    }

    public void deleteDrivingLicense(int id) {
        drivingLicenseRepository.deleteById(id);
    }

    public void updateDrivingLicense(int id, DrivingLicense drivingLicense) {
        drivingLicenseRepository.save(drivingLicense);
    }

}
