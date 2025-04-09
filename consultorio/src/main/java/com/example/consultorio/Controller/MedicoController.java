package com.example.consultorio.Controller;

import com.example.consultorio.DTO.ConsultaDTO;
import com.example.consultorio.DTO.MedicoDTO;
import com.example.consultorio.DTO.MedicoResponseDTO;
import com.example.consultorio.Service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrar(@RequestBody @Valid MedicoDTO dto) {
        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @GetMapping
    public List<MedicoResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/paciente/{id}/historico")
    public ResponseEntity<List<ConsultaDTO>> historico(@PathVariable Long idPaciente) {
        return ResponseEntity.ok(service.historicoPorPaciente(idPaciente));
    }

}
