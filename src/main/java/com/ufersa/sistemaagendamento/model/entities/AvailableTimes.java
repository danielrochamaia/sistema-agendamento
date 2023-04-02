package com.ufersa.sistemaagendamento.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "horarios_disponiveis")
public class AvailableTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String hora;

    public AvailableTimes(){};

    public AvailableTimes(String data, String hora){
        this.data = data;
        this.hora = hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}