package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Assistant;
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


}
