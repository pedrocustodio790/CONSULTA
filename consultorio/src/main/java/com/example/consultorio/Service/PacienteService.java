package com.example.consultorio.Service;

import com.example.consultorio.DTO.PacienteDTO;
import com.example.consultorio.DTO.PacienteResponseDTO;
import com.example.consultorio.Entity.Paciente;
import com.example.consultorio.Repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public PacienteResponseDTO cadastrar(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());
        paciente.setEndereco(dto.getEndereco());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setPlanoSaude(dto.getPlanoSaude());

        return toDTO(repository.save(paciente));
    }

    public List<PacienteResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PacienteResponseDTO buscarPorId(Long id) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
        return toDTO(paciente);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    private PacienteResponseDTO toDTO(Paciente paciente) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setCpf(paciente.getCpf());
        dto.setTelefone(paciente.getTelefone());
        dto.setEndereco(paciente.getEndereco());
        dto.setDataNascimento(paciente.getDataNascimento());
        dto.setPlanoSaude(paciente.getPlanoSaude());
        return dto;
    }
    public Paciente getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
    }

}
