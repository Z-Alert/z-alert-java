package com.fiap.zalert.api.service;

import com.fiap.zalert.api.dto.UsuarioDTO;
import com.fiap.zalert.api.model.Usuario;
import com.fiap.zalert.api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "usuarios", key = "'todos'")
    public Page<Usuario> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Cacheable(value = "usuarios", key = "#id")
    public Usuario buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    @Transactional
    @CachePut(value = "usuarios", key = "#result.idUsu")
    public Usuario criar(UsuarioDTO dto) {
        Usuario usuario = Usuario.builder()
                .nmUsu(dto.getNmUsu())
                .emailUsu(dto.getEmailUsu())
                .senhaUsu(dto.getSenhaUsu())
                .build();
        return repository.save(usuario);
    }

    @Transactional
    @CachePut(value = "usuarios", key = "#result.idUsu")
    public Usuario atualizar(Integer id, UsuarioDTO dto) {
        Usuario usuario = buscarPorId(id);
        usuario.setNmUsu(dto.getNmUsu());
        usuario.setEmailUsu(dto.getEmailUsu());
        usuario.setSenhaUsu(dto.getSenhaUsu());
        return repository.save(usuario);
    }

    @Transactional
    @CacheEvict(value = "usuarios", key = "#id")
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        repository.deleteById(id);
        limparCacheTodosUsuarios();
    }

    @CacheEvict(value = "usuarios", key = "'todos'")
    public void limparCacheTodosUsuarios() {}

    @CacheEvict(value = "usuarios", allEntries = true)
    public void limparTodoCacheUsuarios() {}
}
