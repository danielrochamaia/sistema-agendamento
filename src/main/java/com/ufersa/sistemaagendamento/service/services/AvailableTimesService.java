package com.ufersa.sistemaagendamento.service.services;

import com.ufersa.sistemaagendamento.infrastructure.interfaces.IAvailableTimesRepository;
import com.ufersa.sistemaagendamento.model.entities.AvailableTimes;
import com.ufersa.sistemaagendamento.service.interfaces.IAvailableTimesService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class AvailableTimesService implements IAvailableTimesService {

    private IAvailableTimesRepository availableTimesRepository;

    public AvailableTimesService(IAvailableTimesRepository availableTimesRepository) {
        this.availableTimesRepository = availableTimesRepository;
    }

    @Override
    public String AddAvailableTimes(String filePath) {

        var file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine();
                var data = line.split(",");
                availableTimesRepository.save(new AvailableTimes(data[0], data[1]));
            }
        }
        catch (IOException e) {

        }
        finally {
            if(scanner != null) {
                scanner.close();
            }
        }

        return "Horários adicionados!";
    }

    @Override
    public List<AvailableTimes> GetAvailableTimes() {
        return availableTimesRepository.findAll();
    }

    @Override
    public Boolean RemoveAvailableTime(Integer timeId) {
        var time = GetAvailableTimesById(timeId);
        availableTimesRepository.delete(time);
        return true;
    }

    @Override
    public AvailableTimes GetAvailableTimesById(Integer timeId) {
        return availableTimesRepository.getById(timeId);
    }
}
