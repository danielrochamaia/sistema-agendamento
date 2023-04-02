package com.ufersa.sistemaagendamento.controller;

import com.ufersa.sistemaagendamento.model.entities.AvailableTimes;
import com.ufersa.sistemaagendamento.model.entities.Schedule;
import com.ufersa.sistemaagendamento.model.entities.User;
import com.ufersa.sistemaagendamento.model.requests.ScheduleRequest;
import com.ufersa.sistemaagendamento.model.requests.UserRequest;
import com.ufersa.sistemaagendamento.service.interfaces.IAvailableTimesService;
import com.ufersa.sistemaagendamento.service.interfaces.IScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private IAvailableTimesService availableTimesService;
    private IScheduleService scheduleService;

    public ScheduleController(IAvailableTimesService availableTimesService, IScheduleService scheduleService){
        this.availableTimesService = availableTimesService;
        this.scheduleService = scheduleService;
    }

    @PostMapping("/horarios/gerar")
    public ResponseEntity<String> AddAvailableTimes(@RequestHeader String filePath) {
        var response = availableTimesService.AddAvailableTimes(filePath);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/horarios/disponiveis")
    public ResponseEntity<List<AvailableTimes>> GetAvailableTimes() {
        var response = availableTimesService.GetAvailableTimes();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/agendar")
    public ResponseEntity<String> AddSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        var response = scheduleService.AddSchedule(scheduleRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
