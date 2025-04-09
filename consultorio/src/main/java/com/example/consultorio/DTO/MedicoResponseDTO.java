package com.example.consultorio.DTO;

import lombok.Data;
import java.util.List;

@Data
public class MedicoResponseDTO {
    private Long id;
    private String nome;
    private String crm;
    private String especialidade;
    private String telefone;
    private String email;
    private String nomeConsultorio;
    private Integer numeroConsultorio;
    private List<String> nomesPacientes; // lista de nomes dos pacientes
}
