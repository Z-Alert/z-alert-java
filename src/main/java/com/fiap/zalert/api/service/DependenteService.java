package com.fiap.zalert.api.service;

import com.fiap.zalert.api.dto.DependenteDTO;
import com.fiap.zalert.api.model.Dependente;
import com.fiap.zalert.api.model.Usuario;
import com.fiap.zalert.api.repository.DependenteRepository;
import com.fiap.zalert.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DependenteService {

    private final DependenteRepository dependenteRepository;
    private final UsuarioRepository usuarioRepository;

    public DependenteService(DependenteRepository dependenteRepository, UsuarioRepository usuarioRepository) {
        this.dependenteRepository = dependenteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Dependente> listar(Pageable pageable) {
        return dependenteRepository.findAll(pageable);
    }

    public Dependente buscarPorId(Integer id) {
        return dependenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dependente não encontrado"));
    }

    public Dependente criar(DependenteDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Dependente dependente = Dependente.builder()
                .nmDepen(dto.getNmDepen())
                .tipo(dto.getTipo())
                .idadeDepen(dto.getIdadeDepen())
                .usuario(usuario)
                .build();

        return dependenteRepository.save(dependente);
    }

    public Dependente atualizar(Integer id, DependenteDTO dto) {
        Dependente dependente = buscarPorId(id);
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        dependente.setNmDepen(dto.getNmDepen());
        dependente.setTipo(dto.getTipo());
        dependente.setIdadeDepen(dto.getIdadeDepen());
        dependente.setUsuario(usuario);

        return dependenteRepository.save(dependente);
    }

    public void deletar(Integer id) {
        if (!dependenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Dependente não encontrado");
        }
        dependenteRepository.deleteById(id);
    }
}
