package com.api.routine_organizer.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.routine_organizer.Dto.RoutineOrganizerDto;
import com.api.routine_organizer.model.RoutineOrganizerMessage;
import com.api.routine_organizer.model.RoutineOrganizerModel;
import com.api.routine_organizer.repository.RoutineOrganizerRepository;

import jakarta.transaction.Transactional;

@Service
public class RoutineOrganizerService {
    
    @Autowired
    RoutineOrganizerRepository routineOrganizerRepository;

    @Autowired
    RoutineOrganizerMessage routineOrganizerMessage;

    @Transactional
    public ResponseEntity<?> register(RoutineOrganizerDto routineOrganizerDto) {
        if (taskAnalysis(routineOrganizerDto) != "") {
            routineOrganizerMessage.setMessage(taskAnalysis(routineOrganizerDto));
            return new ResponseEntity<RoutineOrganizerMessage>(routineOrganizerMessage,HttpStatus.BAD_REQUEST);
        } else {
            RoutineOrganizerModel routineOrganizerModel = new RoutineOrganizerModel();
            BeanUtils.copyProperties(routineOrganizerDto, routineOrganizerModel);
            return new ResponseEntity<RoutineOrganizerModel>(routineOrganizerRepository.save(routineOrganizerModel), HttpStatus.CREATED);
        }
    }

    @Transactional
    public ResponseEntity<?> edit(RoutineOrganizerDto routineOrganizerDto, UUID id) {
        if (taskAnalysis(routineOrganizerDto) != "") {
            routineOrganizerMessage.setMessage(taskAnalysis(routineOrganizerDto));
            return new ResponseEntity<RoutineOrganizerMessage>(routineOrganizerMessage,HttpStatus.BAD_REQUEST);
        } else {
            Optional<RoutineOrganizerModel> routineOrganizerModelOptional = routineOrganizerRepository.findById(id);
            var routineOrganizerModel = new RoutineOrganizerModel();
            BeanUtils.copyProperties(routineOrganizerDto, routineOrganizerModel);
            routineOrganizerModel.setId(routineOrganizerModelOptional.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(routineOrganizerRepository.save(routineOrganizerModel));
        }
    }

    public Iterable<RoutineOrganizerModel> findAll() {
        return routineOrganizerRepository.findAll();
    }

    public Optional<RoutineOrganizerModel> findById(UUID id) {
        return routineOrganizerRepository.findById(id);
    }
        
    public ResponseEntity<RoutineOrganizerMessage> delete(UUID id) {
        routineOrganizerRepository.deleteById(id);
        routineOrganizerMessage.setMessage("Tarefa deletada com sucesso");
        return new ResponseEntity<RoutineOrganizerMessage>(routineOrganizerMessage,HttpStatus.OK);
    }

    private String taskAnalysis(RoutineOrganizerDto dto) {
        String message = "";
        if(dto.getName()=="") {
            message = "O nome da tarefa é obrigatório";
            
        }
        else if (dto.getTime()=="") {
            message = "O horário da tarefa é obrigatório";
        }
        else if (dto.getDay()=="") {
            message = "O dia da tarefa é obrigatório";
        }
        return message;
    }
}
