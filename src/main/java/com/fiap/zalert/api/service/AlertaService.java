package com.fiap.zalert.api.service;

import com.fiap.zalert.api.dto.AlertaDTO;
import com.fiap.zalert.api.model.Alerta;
import com.fiap.zalert.api.model.Dependente;
import com.fiap.zalert.api.repository.AlertaRepository;
import com.fiap.zalert.api.repository.DependenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final DependenteRepository dependenteRepository;

    public AlertaService(AlertaRepository alertaRepository, DependenteRepository dependenteRepository) {
        this.alertaRepository = alertaRepository;
        this.dependenteRepository = dependenteRepository;
    }

    public Page<Alerta> listar(Pageable pageable) {
        return alertaRepository.findAll(pageable);
    }

    public Alerta buscarPorId(Integer id) {
        return alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));
    }

    public Alerta criar(AlertaDTO dto) {
        Dependente dependente = dependenteRepository.findById(dto.getDependenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dependente não encontrado"));

        Alerta alerta = Alerta.builder()
                .localizacao(dto.getLocalizacao())
                .sttsAlerta(dto.getSttsAlerta())
                .dependente(dependente)
                .build();

        return alertaRepository.save(alerta);
    }

    public Alerta atualizar(Integer id, AlertaDTO dto) {
        Alerta alerta = buscarPorId(id);
        Dependente dependente = dependenteRepository.findById(dto.getDependenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dependente não encontrado"));

        alerta.setLocalizacao(dto.getLocalizacao());
        alerta.setSttsAlerta(dto.getSttsAlerta());
        alerta.setDependente(dependente);

        return alertaRepository.save(alerta);
    }

    public void deletar(Integer id) {
        if (!alertaRepository.existsById(id)) {
            throw new EntityNotFoundException("Alerta não encontrado");
        }
        alertaRepository.deleteById(id);
    }
}
