package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.entities.Route;
import com.busfleetproj.busfleetproj.entities.Student;
import com.busfleetproj.busfleetproj.repos.BusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteService routeService;

    @Autowired
    private StudentService studentService;

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        log.info("Retrieving bus objects from database");
        busRepository.findAll()
                .forEach(buses::add);

        return buses;
    }

    public Bus getBus(int id) {

        Optional<Bus> busOptional = busRepository.findById(id);
        log.info("Retrieving bus object id#" + id + " from database");
        if (busOptional.isPresent()) {
            log.info("Bus object id#" + id + "found in database");
            return busOptional.get();
        } else {
            log.error("Bus object id#" + id + " not found in database");
            return new Bus();
        }

    }

    public void addBus(Bus bus) {
        busRepository.save(bus);
        log.info("Saving bus object to database");

    }

    public void deleteBus(int id) {
        busRepository.deleteById(id);
        log.info("Deleting bus object id#" + id + "from database");

    }

    public void updateBus(int id, Bus bus) {
        busRepository.save(bus);
        log.info("Updating bus object id#" + id + " in database");

    }

    // we want to change  the bus to route_id's route
    public void changeBusRoute(int id, int route_id) {

        List<Route> routeList = new ArrayList<>();
        List<Bus> busList = new ArrayList<>();

        Bus bus = getBus(id);                       //getting the concerned bus


        if (route_id == -1) {
            bus.makeRouteNull();                                                     //if we want to remove route from bus
            log.info("Removing route assigned to bus object id#" + id);
        } else {
            Route route = routeService.getRoute(route_id);                          //route to update bus to

            route.setBus(bus);                                                      //setting route's bus to our bus
            routeList.add(route);                                                   //adding route to update list

            bus.setRoute(route);                                                    //setting bus's route to new route
            log.info("Assigning route object id#" + route_id + " to bus object id#" + id);
        }

        busList.add(bus);
        routeService.updateRoutes(routeList);
        busRepository.saveAll(busList);
        log.info("Saving changes to database");
    }

    public boolean addStudents(int id, List<Integer> studentIds) {

        Bus bus = getBus(id);

        int seatsLeft = bus.getNumberOfSeats() - bus.getStudents().size() - 1;
        if (seatsLeft < studentIds.size()) {
            log.error("Number of students exceeds current bus capacity");
            return false;
        } else {
            log.info("Adding students to bus");
            List<Student> students = studentService.getStudents(studentIds);

            for (Student student : students) {
                student.setBus(bus);                                         //setting the bus of that student to the new bus
            }
            studentService.updateStudents(students);                             //updating student in its repository
            students.addAll(bus.getStudents());
            bus.setStudents(students);                                           //updating student list in bus

            return true;
        }
    }

    public void updateBuses(List<Bus> buses) {
        busRepository.saveAll(buses);
        log.info("Updating bus objects information");
    }

    public void removeStudents(int id, List<Integer> studentIds) {
        Bus bus = getBus(id);
        List<Student> studentsToUpdate = new ArrayList<>();
        List<Student> studentsToRemove = studentService.getStudents(studentIds);
        List<Student> busStudents = bus.getStudents();

        log.info("Removing students from bus object id#" + id);
        for (Student student : studentsToRemove) {
            busStudents.remove(student);
            student.makeBusNull();
            studentsToUpdate.add(student);
        }

        bus.setStudents(busStudents);
        updateBus(id, bus);

        studentService.updateStudents(studentsToUpdate);
    }
}
