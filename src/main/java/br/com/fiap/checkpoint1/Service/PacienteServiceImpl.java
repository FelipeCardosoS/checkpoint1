package br.com.fiap.checkpoint1.Service;


import br.com.fiap.checkpoint1.dto.PacienteDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImpl implements PacienteService{
    private final List<PacienteDTO> pacientes = new ArrayList<>();

    @Override
    public PacienteDTO criarPaciente(PacienteDTO pacienteDTO) {
        pacienteDTO.setId((long) (pacientes.size() + 1));
        pacientes.add(pacienteDTO);
        return pacienteDTO;
    }

    @Override
    public List<PacienteDTO> listarPacientes() {
        return pacientes;
    }

    @Override
    public PacienteDTO buscarPaciente(Long id) {
        Optional<PacienteDTO> paciente = pacientes.stream().filter(p -> p.getId().equals(id)).findFirst();
        return paciente.orElse(null);
    }

    @Override
    public PacienteDTO atualizarPaciente(Long id, PacienteDTO pacienteAtualizado) {
        for(PacienteDTO paciente : pacientes) {
            if(paciente.getId().equals(id)){
                paciente.setNome(pacienteAtualizado.getNome());
                paciente.setEndereco(pacienteAtualizado.getEndereco());
                paciente.setBairro(pacienteAtualizado.getBairro());
                paciente.setEmail(pacienteAtualizado.getEmail());
                paciente.setTelefone(pacienteAtualizado.getTelefone());
                return paciente;
            }
        }
        return null;
    }

    @Override
    public void excluirPaciente(Long id) {
        pacientes.removeIf(p -> p.getId().equals(id));
    }

}
