package com.ufersa.sistemaagendamento.service.interfaces;

import com.ufersa.sistemaagendamento.model.entities.AvailableTimes;

import java.util.List;

public interface IAvailableTimesService {

    String AddAvailableTimes(String filePath);

    List<AvailableTimes> GetAvailableTimes();

    AvailableTimes GetAvailableTimesById(Integer timeId);

    Boolean RemoveAvailableTime(Integer timeId);

}
