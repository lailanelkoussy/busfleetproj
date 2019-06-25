package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Assistant;
import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.entities.BusDriver;
import com.busfleetproj.busfleetproj.repos.AssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssistantService {

    @Autowired
    AssistantRepository assistantRepository;

    @Autowired
    BusService busService;

    public List<Assistant> getAllAssistants() {
        List<Assistant> assistants = new ArrayList<>();
        assistantRepository.findAll()
                .forEach(assistants::add);
        return assistants;

    }

    public Assistant getAssistant(int id) {
        Optional<Assistant> assistantOptional = assistantRepository.findById(id);
        if (assistantOptional.isPresent())
            return assistantOptional.get();
        else
            return new Assistant();
    }

    public void addAssistant(Assistant assistant) {
        assistantRepository.save(assistant);
    }

    public void updateAssistant(int id, Assistant assistant) {
        assistantRepository.save(assistant);
    }

    public void deleteAssistant(int id) {
        assistantRepository.deleteById(id);
    }

    public void changeAssistantBus(int id, int bus_id) {

        List<Bus> busList = new ArrayList<>();
        List<Assistant> assistants = new ArrayList<>();

        Assistant assistant = getAssistant(id);             //get assistant corresponding to id


        if (bus_id == -1) {
            assistant.makeBusNull();                     //if we want to remove bus from assistant

        } else {

            Bus bus = busService.getBus(bus_id);                //get bus corresponding to id
            assistant.setBus(bus);                              //setting the assistant with the new bus

            bus.setAssistant(assistant);                        //setting the bus with the new assistant
            busList.add(bus);                                   //adding new bus to update list

        }
        assistants.add(assistant);
        busService.updateBuses(busList);                        //saving the updated buses
        assistantRepository.saveAll(assistants);                //saving the updates bus drivers


    }
}
