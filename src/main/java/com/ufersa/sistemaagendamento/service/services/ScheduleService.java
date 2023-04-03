package com.ufersa.sistemaagendamento.service.services;

import com.ufersa.sistemaagendamento.infrastructure.interfaces.IScheduleRepository;
import com.ufersa.sistemaagendamento.model.dataStructures.fila.IFila;
import com.ufersa.sistemaagendamento.model.dataStructures.fila.MinhaFilaVetor;
import com.ufersa.sistemaagendamento.model.entities.Schedule;
import com.ufersa.sistemaagendamento.model.entities.User;
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
    private IFila queue;

    public ScheduleService(IScheduleRepository scheduleRepository,
        IUserService userService, IAvailableTimesService availableTimesService) {
        this.scheduleRepository = scheduleRepository;
        this.userService = userService;
        this.availableTimesService = availableTimesService;
        this.queue = new MinhaFilaVetor<User>(5);
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

    @Override
    public String AddIntoWaitingQueue(String email, String senha) {

        var registeredUser = userService.GetUserByLoginData(email, senha);
        if(registeredUser.isEmpty()){
            return "Usuário não cadastrado";
        }

        if(queue.isFull()){
            return "Desculpe, lista de espera cheia :(";
        }

        queue.add(registeredUser.get());

        return "Adicionado!";
    }

    @Override
    public User GetFirst() {
        return (User) queue.consultaInicio();
    }
}
