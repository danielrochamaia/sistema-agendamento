package com.ufersa.sistemaagendamento.service.services;

import com.ufersa.sistemaagendamento.infrastructure.interfaces.IScheduleRepository;
import com.ufersa.sistemaagendamento.model.entities.Schedule;
import com.ufersa.sistemaagendamento.model.requests.ScheduleRequest;
import com.ufersa.sistemaagendamento.service.interfaces.IAvailableTimesService;
import com.ufersa.sistemaagendamento.service.interfaces.IScheduleService;
import com.ufersa.sistemaagendamento.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements IScheduleService {

    private IScheduleRepository scheduleRepository;
    private IUserService userService;

    private IAvailableTimesService availableTimesService;

    public ScheduleService(IScheduleRepository scheduleRepository,
        IUserService userService, IAvailableTimesService availableTimesService) {
        this.scheduleRepository = scheduleRepository;
        this.userService = userService;
        this.availableTimesService = availableTimesService;
    }

    @Override
    public String AddSchedule(ScheduleRequest scheduleRequest) {

        var registeredUser = userService.GetUserByLoginData(scheduleRequest.email, scheduleRequest.senha);
        if(registeredUser.isEmpty()){
            return "Usuário não cadastrado";
        }

        scheduleRepository.save(new Schedule(registeredUser.get().getId(), scheduleRequest.timeId));

        availableTimesService.RemoveAvailableTime(scheduleRequest.timeId);

        return "Agendamento bem sucedido!";
    }
}
