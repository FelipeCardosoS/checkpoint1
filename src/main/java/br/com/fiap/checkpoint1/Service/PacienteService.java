package br.com.fiap.checkpoint1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.checkpoint1.dto.PacienteDTO;

@Service
public interface PacienteService {
    PacienteDTO criarPaciente(PacienteDTO pacienteDTO);

    List<PacienteDTO> listarPacientes();

    PacienteDTO buscarPaciente(Long id);

    PacienteDTO atualizarPaciente(Long id, PacienteDTO pacienteDTO);

    void excluirPaciente(Long id);
}
