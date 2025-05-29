package com.fiap.zalert.api.service;

import com.fiap.zalert.api.dto.UsuarioDTO;
import com.fiap.zalert.api.model.Usuario;
import com.fiap.zalert.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Page<Usuario> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Usuario buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    public Usuario criar(UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .nmUsu(dto.getNmUsu())
                .emailUsu(dto.getEmailUsu())
                .senhaUsu(dto.getSenhaUsu())
                .build();
        return repository.save(usuario);
    }

    public Usuario atualizar(Integer id, UsuarioDTO dto) {
        Usuario usuario = buscarPorId(id);
        usuario.setNmUsu(dto.getNmUsu());
        usuario.setEmailUsu(dto.getEmailUsu());
        usuario.setSenhaUsu(dto.getSenhaUsu());
        return repository.save(usuario);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}
