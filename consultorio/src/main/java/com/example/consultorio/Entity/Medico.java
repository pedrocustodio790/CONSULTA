package com.example.consultorio.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Data

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String crm;

    private String especialidade;

    private String telefone;

    private String email;

    private String nomeConsultorio;

    private Integer numeroConsultorio;

    @ManyToMany
    @JoinTable(
            name = "medico_paciente",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "paciente_id")
    )
    private List<Paciente> pacientes;
}
