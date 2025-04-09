package com.example.consultorio.Service;

import com.example.consultorio.DTO.ConsultaDTO;
import com.example.consultorio.DTO.MedicoDTO;
import com.example.consultorio.DTO.MedicoResponseDTO;
import com.example.consultorio.Entity.Medico;
import com.example.consultorio.Repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public MedicoResponseDTO cadastrar(MedicoDTO dto) {
        Medico medico = new Medico();
        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());
        medico.setEspecialidade(dto.getEspecialidade());
        medico.setTelefone(dto.getTelefone());
        medico.setEmail(dto.getEmail());
        medico.setNomeConsultorio(dto.getNomeConsultorio());
        medico.setNumeroConsultorio(dto.getNumeroConsultorio());
        medico.setPacientes(List.of()); // vazio por padrão

        Medico salvo = repository.save(medico);
        return toDTO(salvo);
    }

    public List<MedicoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MedicoResponseDTO buscarPorId(Long id) {
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));
        return toDTO(medico);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    private MedicoResponseDTO toDTO(Medico medico) {
        MedicoResponseDTO dto = new MedicoResponseDTO();
        dto.setId(medico.getId());
        dto.setNome(medico.getNome());
        dto.setCrm(medico.getCrm());
        dto.setEspecialidade(medico.getEspecialidade());
        dto.setTelefone(medico.getTelefone());
        dto.setEmail(medico.getEmail());
        dto.setNomeConsultorio(medico.getNomeConsultorio());
        dto.setNumeroConsultorio(medico.getNumeroConsultorio());

        if (medico.getPacientes() != null) {
            dto.setNomesPacientes(
                    medico.getPacientes().stream()
                            .map(p -> p.getNome())
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public List<ConsultaDTO> historicoPorPaciente(Long idPaciente) {
        return null;
    }
}
