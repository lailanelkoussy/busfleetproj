package com.busfleetproj.busfleetproj.controllers;

import com.busfleetproj.busfleetproj.entities.Assistant;
import com.busfleetproj.busfleetproj.services.AssistantService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/assistants")
@Api(value = "Assistant Management System")
public class AssistantController {

    @Autowired
    AssistantService assistantService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View all assistant objects ", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public List<Assistant> getAllAssistants() {
        return assistantService.getAllAssistants();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View assistant object by id", response = Assistant.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public Assistant getAssistant(
            @ApiParam(value = "Id of assistant object to retrieve", required = true)
            @PathVariable int id) {
        return assistantService.getAssistant(id);
    }

    @PostMapping
    @ApiOperation(value = "Add assistant object to database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void addAssistant(
            @ApiParam(value = "Assistant object to store in database", required = true)
            @RequestBody Assistant assistant) {
        assistantService.addAssistant(assistant);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Remove assistant object from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void deleteAssistant(
            @ApiParam(value = "Id of assistant object to delete from database", required = true)
            @PathVariable int id) {
        assistantService.deleteAssistant(id);
    }
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update assistant object data in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void updateAssistant(
            @ApiParam(value = "Id of assistant object to update in database", required = true)
            @PathVariable int id,
            @ApiParam(value = "Updated assistant object", required = true)
            @RequestBody Assistant assistant) {
        assistantService.updateAssistant(id, assistant);
    }

    @PatchMapping(value = "/{id}/bus/id")
    @ApiOperation(value = "Assign assistant object to bus ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully assigned object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void changeBusRoute(
            @ApiParam(value = "Id of assistant object that requires assigning to bus", required = true)
            @PathVariable int id,
            @ApiParam(value = "Id of bus to be assigned to (-1 if we wish to just remove assistant from bus)", required = true)
            @RequestBody int bus_id) {
        assistantService.changeAssistantBus(id, bus_id);
    }
}
