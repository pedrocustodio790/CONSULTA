package com.example.consultorio.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MedicoDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String crm;

    private String especialidade;

    private String telefone;

    private String email;

    private String nomeConsultorio;

    private Integer numeroConsultorio;
}
