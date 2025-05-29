package com.fiap.zalert.api.repository;

import com.fiap.zalert.api.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Integer> {
    List<Alerta> findByDependenteIdDepen(Integer dependenteId);
    List<Alerta> findBySttsAlerta(String sttsAlerta);
    long countBySttsAlerta(String sttsAlerta);
}
