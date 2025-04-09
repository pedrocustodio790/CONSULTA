package com.example.consultorio.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ConsultaDTO {
    private LocalDateTime dataHora;
    private String motivo;
    private Long medicoId;
    private Long pacienteId;
    private Boolean retorno;
    private String atestado;
    private BigDecimal valorConsulta;

    public ConsultaDTO() {
    }

    public ConsultaDTO(LocalDateTime dataHora, String motivo, Long medicoId, Long pacienteId, Boolean retorno, String atestado) {
        this.dataHora = dataHora;
        this.motivo = motivo;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
        this.retorno = retorno;
        this.atestado = atestado;
    }
}
