package com.fiap.zalert.api.service;

import com.fiap.zalert.api.dto.LocalizacaoDTO;
import com.fiap.zalert.api.model.Dependente;
import com.fiap.zalert.api.model.Localizacao;
import com.fiap.zalert.api.repository.DependenteRepository;
import com.fiap.zalert.api.repository.LocalizacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;
    private final DependenteRepository dependenteRepository;

    public LocalizacaoService(LocalizacaoRepository localizacaoRepository, DependenteRepository dependenteRepository) {
        this.localizacaoRepository = localizacaoRepository;
        this.dependenteRepository = dependenteRepository;
    }

    public Page<Localizacao> listar(Pageable pageable) {
        return localizacaoRepository.findAll(pageable);
    }

    public Localizacao buscarPorId(Integer id) {
        return localizacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Localização não encontrada"));
    }

    public Localizacao criar(LocalizacaoDTO dto) {
        Dependente dependente = dependenteRepository.findById(dto.getDependenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dependente não encontrado"));

        Localizacao localizacao = Localizacao.builder()
                .latLocali(dto.getLatLocali())
                .lngLocali(dto.getLngLocali())
                .dependente(dependente)
                .build();

        return localizacaoRepository.save(localizacao);
    }

    public Localizacao atualizar(Integer id, LocalizacaoDTO dto) {
        Localizacao localizacao = buscarPorId(id);
        Dependente dependente = dependenteRepository.findById(dto.getDependenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dependente não encontrado"));

        localizacao.setLatLocali(dto.getLatLocali());
        localizacao.setLngLocali(dto.getLngLocali());
        localizacao.setDependente(dependente);

        return localizacaoRepository.save(localizacao);
    }

    public void deletar(Integer id) {
        if (!localizacaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Localização não encontrada");
        }
        localizacaoRepository.deleteById(id);
    }
}
