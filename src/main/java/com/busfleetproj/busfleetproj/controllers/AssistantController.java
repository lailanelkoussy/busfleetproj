package com.busfleetproj.busfleetproj.controllers;

import com.busfleetproj.busfleetproj.entities.Assistant;
import com.busfleetproj.busfleetproj.services.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssistantController {

    @Autowired
    AssistantService assistantService;

    @RequestMapping("/assistants")
    public List<Assistant> getAllAssistants() {
        return assistantService.getAllAssistants();
    }

    @RequestMapping("/assistants/{id}")
    public Assistant getAssistant(@PathVariable int id) {
        return assistantService.getAssistant(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/assistants")
    public void addAssistant(@RequestBody Assistant assistant) {
        assistantService.addAssistant(assistant);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/assistants/{id}")
    public void deleteAssistant(@PathVariable int id) {
        assistantService.deleteAssistant(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/assistants/{id}")
    public void updateAssistant(@PathVariable int id, @RequestBody Assistant assistant) {
        assistantService.updateAssistant(id, assistant);
    }
}
