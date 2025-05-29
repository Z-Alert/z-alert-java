package com.fiap.zalert.api.repository;

import com.fiap.zalert.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailUsu(String emailUsu);
    boolean existsByEmailUsu(String emailUsu);
}
