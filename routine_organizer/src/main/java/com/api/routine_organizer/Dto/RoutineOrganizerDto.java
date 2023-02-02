package com.api.routine_organizer.Dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class RoutineOrganizerDto {

    private String name;
    private String day;
    private String time;
    private boolean important;

}
