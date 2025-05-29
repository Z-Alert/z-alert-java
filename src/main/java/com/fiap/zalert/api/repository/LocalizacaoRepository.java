package com.fiap.zalert.api.repository;

import com.fiap.zalert.api.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {
    List<Localizacao> findByDependenteIdDepen(Integer dependenteId);
    List<Localizacao> findTop5ByDependenteIdDepenOrderByDataHoraDesc(Integer dependenteId);
}
