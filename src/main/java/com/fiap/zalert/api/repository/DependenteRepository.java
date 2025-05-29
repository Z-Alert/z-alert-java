package com.fiap.zalert.api.repository;

import com.fiap.zalert.api.model.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DependenteRepository extends JpaRepository<Dependente, Integer> {
    List<Dependente> findByUsuarioIdUsu(Integer usuarioId);
    List<Dependente> findByTipo(String tipo);
}
