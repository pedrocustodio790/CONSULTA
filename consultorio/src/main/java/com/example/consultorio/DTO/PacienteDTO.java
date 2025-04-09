package com.example.consultorio.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    private String telefone;

    private LocalDate dataNascimento;

    private String endereco;

    private String planoSaude;
}
