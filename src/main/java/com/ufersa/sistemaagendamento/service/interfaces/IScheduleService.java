package com.ufersa.sistemaagendamento.service.interfaces;

import com.ufersa.sistemaagendamento.model.entities.User;
import com.ufersa.sistemaagendamento.model.requests.ScheduleRequest;

public interface IScheduleService {

    String AddSchedule(ScheduleRequest scheduleRequest);

    String AddIntoWaitingQueue(String email, String senha);

    User GetFirst();

}
