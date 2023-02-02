package com.api.routine_organizer.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.routine_organizer.Dto.RoutineOrganizerDto;
import com.api.routine_organizer.model.RoutineOrganizerMessage;
import com.api.routine_organizer.model.RoutineOrganizerModel;
import com.api.routine_organizer.services.RoutineOrganizerService;


@RestController
@CrossOrigin(origins = "*")
public class RoutineOrganizerController {
    
    @Autowired
    RoutineOrganizerService routineOrganizerService;

    @Autowired
    RoutineOrganizerMessage routineOrganizerMessage;

    @PostMapping("/register")
    public ResponseEntity<?> registerTask(@RequestBody RoutineOrganizerDto routineOrganizerDto) {
        return routineOrganizerService.register(routineOrganizerDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editTask(@PathVariable(value="id") UUID id, @RequestBody RoutineOrganizerDto routineOrganizerDto) {
        return routineOrganizerService.edit(routineOrganizerDto, id);
    }

    @GetMapping("/list")
    public Iterable<RoutineOrganizerModel> listAllTasks() {
        return routineOrganizerService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RoutineOrganizerMessage> deleteTask(@PathVariable UUID id) {
        return routineOrganizerService.delete(id);
    }

}
