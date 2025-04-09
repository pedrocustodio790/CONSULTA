package com.example.consultorio.Controller;

import com.example.consultorio.DTO.ConsultaDTO;
import com.example.consultorio.DTO.ConsultaResponseDTO;
import com.example.consultorio.Entity.Consulta;
import com.example.consultorio.Service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService service;

    @PostMapping
    public ConsultaResponseDTO agendarConsulta(@RequestBody ConsultaDTO dto) {
        return service.agendarConsulta(dto);
    }

    @GetMapping
    public List<ConsultaResponseDTO> listarConsultas() {
        return service.listarConsultas();
    }

    // Consulta completa com entidade
    @GetMapping("/paciente/{id}/detalhes")
    public List<Consulta> historicoDetalhadoPorPaciente(@PathVariable Long id) {
        return service.historicoPorPaciente(id);
    }

    // Consulta resumida com DTO
    @GetMapping("/paciente/{id}/resumo")
    public List<ConsultaResponseDTO> resumoHistoricoPorPaciente(@PathVariable Long id) {
        return service.listarConsultasPorPaciente(id);
    }

    @GetMapping("/relatorio")
    public ResponseEntity<Map<String, Object>> relatorioFinanceiro() {
        List<Consulta> consultas = service.getTodasConsultas();
        BigDecimal totalRecebido = consultas.stream()
                .map(Consulta::getValorConsulta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("totalConsultas", consultas.size());
        resultado.put("totalRecebido", totalRecebido);
        resultado.put("consultas", consultas);

        return ResponseEntity.ok(resultado);
    }
}
