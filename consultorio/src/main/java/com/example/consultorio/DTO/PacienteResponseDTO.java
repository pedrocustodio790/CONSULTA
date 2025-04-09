package com.example.consultorio.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco;
    private String planoSaude;
}
