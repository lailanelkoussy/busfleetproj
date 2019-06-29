package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Assistant;
import com.busfleetproj.busfleetproj.entities.Bus;
import com.busfleetproj.busfleetproj.repos.AssistantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = {"assistants"})
public class AssistantService {

    @Autowired
    AssistantRepository assistantRepository;

    @Autowired
    BusService busService;

    @Cacheable
    public List<Assistant> getAllAssistants() {

        log.info("Retrieving assistant from database");
        return assistantRepository.findAll();
    }

    @Cacheable(key = "#id")
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

    @CachePut(key = "#id")
    public Assistant updateAssistant(int id, Assistant assistant) {
        assistantRepository.save(assistant);
        log.info("Updating assistant object id#" + id + " in database");
        return assistant;
    }

    @CacheEvict(key = "#id")
    public void deleteAssistant(int id) {
        assistantRepository.deleteById(id);
        log.info("Deleting assistant object id#" + id + "from database");
    }

    @CachePut(key = "#id")
    public Assistant changeAssistantBus(int id, int bus_id) {

        Assistant assistant = getAssistant(id);             //get assistant corresponding to id


        if (bus_id == -1) {
            assistant.makeBusNull();                     //if we want to remove bus from assistant
            log.info("Removing the bus assigned to assistant object id#" + id);

        } else {

            Bus bus = busService.getBus(bus_id);                //get bus corresponding to id
            assistant.setBus(bus);                              //setting the assistant with the new bus

            bus.setAssistant(assistant);                        //setting the bus with the new assistant
            busService.updateBus(bus_id, bus);

            log.info("Assigning assistant object id#" + id + " to bus id#" + bus_id);
            busService.updateCacheEntry(bus_id, bus);

        }
        assistantRepository.save(assistant);                //saving the updates bus drivers
        log.info("Saving changes to database");
        return assistant;
    }
}
