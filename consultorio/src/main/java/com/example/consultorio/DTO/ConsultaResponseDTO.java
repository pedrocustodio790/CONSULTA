package com.example.consultorio.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ConsultaResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String motivo;
    private String nomeMedico;
    private String nomePaciente;
    private Boolean retorno;
    private String atestado;

    public ConsultaResponseDTO(Long id, LocalDateTime dataHora, String motivo,
                               String nomeMedico, String nomePaciente,
                               Boolean retorno, String atestado, BigDecimal valorConsulta) {
        this.id = id;
        this.dataHora = dataHora;
        this.motivo = motivo;
        this.nomeMedico = nomeMedico;
        this.nomePaciente = nomePaciente;
        this.retorno = retorno;
        this.atestado = atestado;
    }
}
