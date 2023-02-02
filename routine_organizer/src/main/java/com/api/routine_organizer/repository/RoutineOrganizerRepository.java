package com.api.routine_organizer.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.routine_organizer.model.RoutineOrganizerModel;


public interface RoutineOrganizerRepository extends JpaRepository<RoutineOrganizerModel, UUID>{

    public List<RoutineOrganizerModel> findAllByDay(String day);
    public List<RoutineOrganizerModel> findAllByTime(String time);
    
    public boolean existsByDay(String day);
    public boolean existsByTime(String time);

    
    
}
