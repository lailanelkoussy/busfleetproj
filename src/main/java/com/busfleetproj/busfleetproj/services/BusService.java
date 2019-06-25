package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.entities.Route;
import com.busfleetproj.busfleetproj.entities.Student;
import com.busfleetproj.busfleetproj.repos.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addBus(Bus bus) {
        busRepository.save(bus);
    }

    public void deleteBus(int id) {
        busRepository.deleteById(id);
    }

    public void updateBus(int id, Bus bus) {
        busRepository.save(bus);
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

    public void addStudents(int id, List<Integer> studentIds) {

        Bus bus = getBus(id);
        Student tempStudent;
        int seatsLeft = bus.getNumberOfSeats() - bus.getStudents().size() - 1;
        if (seatsLeft < studentIds.size()) {

        } else {
            List<Student> students = new ArrayList<>();
            for (int studentId : studentIds) {
                tempStudent = studentService.getStudent(studentId);              //get student using its id
                tempStudent.setBus(bus);                                         //setting the bus of that student to the new bus
                students.add(tempStudent);                                       //adding to list of students to be updated
            }

            studentService.updateStudents(students);                             //updating student in its repository
            students.addAll(bus.getStudents());
            bus.setStudents(students);                                           //updating student list in bus
        }
    }

    public void updateBuses(List<Bus> buses) {
        busRepository.saveAll(buses);
    }
}
