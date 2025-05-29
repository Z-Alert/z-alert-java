package com.fiap.zalert.api.repository;

import com.fiap.zalert.api.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
    Optional<Dispositivo> findByDependenteIdDepen(Integer dependenteId);
    Optional<Dispositivo> findByTipoDisposit(String tipoDisposit);
}
