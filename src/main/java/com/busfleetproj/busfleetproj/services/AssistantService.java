package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Assistant;
import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.repos.AssistantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AssistantService {

    @Autowired
    AssistantRepository assistantRepository;

    @Autowired
    BusService busService;

    public List<Assistant> getAllAssistants() {
        List<Assistant> assistants = new ArrayList<>();
        log.info("Retrieving assistant from database");
        assistantRepository.findAll()
                .forEach(assistants::add);
        return assistants;

    }

    public Assistant getAssistant(int id) {
        Optional<Assistant> assistantOptional = assistantRepository.findById(id);
        log.info("Retrieving assistant object id#" + id + " from database");
        if (assistantOptional.isPresent()) {
            log.info("Assistant object id#" + id + "found in database");
            return assistantOptional.get();
        } else {
            log.error("Assistant object id#" + id + " not found in database");
            return new Assistant();
        }
    }

    public void addAssistant(Assistant assistant) {
        assistantRepository.save(assistant);
        log.info("Saving assistant object to database");
    }

    public void updateAssistant(int id, Assistant assistant) {
        assistantRepository.save(assistant);
        log.info("Updating assistant object id#" + id + " in database");
    }

    public void deleteAssistant(int id) {
        assistantRepository.deleteById(id);
        log.info("Deleting assistant object id#" + id + "from database");
    }

    public void changeAssistantBus(int id, int bus_id) {

        List<Bus> busList = new ArrayList<>();
        List<Assistant> assistants = new ArrayList<>();

        Assistant assistant = getAssistant(id);             //get assistant corresponding to id


        if (bus_id == -1) {
            assistant.makeBusNull();                     //if we want to remove bus from assistant
            log.info("Removing the bus assigned to assistant object id#" + id);

        } else {

            Bus bus = busService.getBus(bus_id);                //get bus corresponding to id
            assistant.setBus(bus);                              //setting the assistant with the new bus

            bus.setAssistant(assistant);                        //setting the bus with the new assistant
            busList.add(bus);                                   //adding new bus to update list

            log.info("Assigning assistant object id#" + id + " to bus id#" + bus_id);

        }
        assistants.add(assistant);
        busService.updateBuses(busList);                        //saving the updated buses
        assistantRepository.saveAll(assistants);                //saving the updates bus drivers
        log.info("Saving changes to database");


    }
}
