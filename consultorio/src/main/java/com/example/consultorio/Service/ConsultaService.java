package com.example.consultorio.Service;

import com.example.consultorio.DTO.ConsultaDTO;
import com.example.consultorio.DTO.ConsultaResponseDTO;
import com.example.consultorio.Entity.Consulta;
import com.example.consultorio.Entity.Medico;
import com.example.consultorio.Entity.Paciente;
import com.example.consultorio.Repository.ConsultaRepository;
import com.example.consultorio.Repository.MedicoRepository;
import com.example.consultorio.Repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaResponseDTO agendarConsulta(ConsultaDTO dto) {
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setDataHora(dto.getDataHora());
        consulta.setMotivo(dto.getMotivo());
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setNomeMedico(medico.getNome());
        consulta.setNomePaciente(paciente.getNome());
        consulta.setRetorno(dto.getRetorno());
        consulta.setAtestado(dto.getAtestado());
        consulta.setValorConsulta(dto.getValorConsulta());

        Consulta salva = consultaRepository.save(consulta);

        return toDTO(salva);
    }

    public List<ConsultaResponseDTO> listarConsultas() {
        return consultaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public List<ConsultaResponseDTO> listarConsultasPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Consulta> historicoPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    public List<Consulta> getTodasConsultas() {
        return consultaRepository.findAll();
    }

    private ConsultaResponseDTO toDTO(Consulta c) {
        return new ConsultaResponseDTO(
                c.getId(),
                c.getDataHora(),
                c.getMotivo(),
                c.getNomeMedico(),
                c.getNomePaciente(),
                c.getRetorno(),
                c.getAtestado(),
                c.getValorConsulta()
        );
    }
}
