package com.example.consultorio.Controller;

import com.example.consultorio.DTO.ConsultaResponseDTO;
import com.example.consultorio.DTO.PacienteDTO;
import com.example.consultorio.DTO.PacienteResponseDTO;
import com.example.consultorio.Entity.Paciente;
import com.example.consultorio.Service.ConsultaService;
import com.example.consultorio.Service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrar(@RequestBody @Valid PacienteDTO dto) {
        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @GetMapping
    public List<PacienteResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/ficha")
    public ResponseEntity<Map<String, Object>> fichaPaciente(@PathVariable Long id) {
        Paciente paciente = service.getById(id);
        List<ConsultaResponseDTO> consultas = consultaService.listarConsultasPorPaciente(id);

        Map<String, Object> ficha = new HashMap<>();
        ficha.put("paciente", paciente);
        ficha.put("consultas", consultas);

        return ResponseEntity.ok(ficha);
    }

}
