package com.ufersa.sistemaagendamento.service.interfaces;

import com.ufersa.sistemaagendamento.model.entities.Schedule;
import com.ufersa.sistemaagendamento.model.requests.ScheduleRequest;

public interface IScheduleService {

    String AddSchedule(ScheduleRequest scheduleRequest);

}
