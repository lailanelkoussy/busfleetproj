package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.entities.Route;
import com.busfleetproj.busfleetproj.entities.Student;
import com.busfleetproj.busfleetproj.repos.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteService routeService;

    @Autowired
    private StudentService studentService;

    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        busRepository.findAll()
                .forEach(buses::add);

        return buses;
    }

    public Bus getBus(int id) {

        Optional<Bus> busOptional = busRepository.findById(id);
        if (busOptional.isPresent())
            return busOptional.get();
        else
            return new Bus();

    }

    public ResponseEntity addBus(Bus bus) {
        busRepository.save(bus);
        return new ResponseEntity<>("Bus successfully added.", HttpStatus.CREATED);
    }

    public ResponseEntity deleteBus(int id) {
        busRepository.deleteById(id);
        return new ResponseEntity<>("Bus successfully added.", HttpStatus.OK);
    }

    public ResponseEntity updateBus(int id, Bus bus) {

        busRepository.save(bus);
        return new ResponseEntity<>("Bus successfully updated.", HttpStatus.CREATED);
    }

    // we want to change  the bus to route_id's route
    public void changeBusRoute(int id, int route_id) {

        List<Route> routeList = new ArrayList<>();
        List<Bus> busList = new ArrayList<>();

        Bus bus = getBus(id);                       //getting the concerned bus


        if (route_id == -1) {
            bus.makeRouteNull();                                                     //if we want to remove route from bus
        } else {
            Route route = routeService.getRoute(route_id);                          //route to update bus to

            route.setBus(bus);                                                      //setting route's bus to our bus
            routeList.add(route);                                                   //adding route to update list

            bus.setRoute(route);                                                    //setting bus's route to new route
        }

        busList.add(bus);
        routeService.updateRoutes(routeList);
        busRepository.saveAll(busList);
    }

    public boolean addStudents(int id, List<Integer> studentIds) {

        Bus bus = getBus(id);

        int seatsLeft = bus.getNumberOfSeats() - bus.getStudents().size() - 1;
        if (seatsLeft < studentIds.size()) {
            return false;
        } else {
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
    }

    public void removeStudents(int id, List<Integer> studentIds) {
        Bus bus = getBus(id);
        List<Student> studentsToUpdate = new ArrayList<>();
        List<Student> studentsToRemove = studentService.getStudents(studentIds);
        List<Student> busStudents = bus.getStudents();

        for (Student student: studentsToRemove){
            busStudents.remove(student);
            student.makeBusNull();
            studentsToUpdate.add(student);
        }

        bus.setStudents(busStudents);
        updateBus(id, bus);

        studentService.updateStudents(studentsToUpdate);
    }
}
