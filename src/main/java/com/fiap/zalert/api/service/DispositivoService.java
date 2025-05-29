package com.fiap.zalert.api.service;

import com.fiap.zalert.api.dto.DispositivoDTO;
import com.fiap.zalert.api.model.Dependente;
import com.fiap.zalert.api.model.Dispositivo;
import com.fiap.zalert.api.repository.DependenteRepository;
import com.fiap.zalert.api.repository.DispositivoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;
    private final DependenteRepository dependenteRepository;

    public DispositivoService(DispositivoRepository dispositivoRepository, DependenteRepository dependenteRepository) {
        this.dispositivoRepository = dispositivoRepository;
        this.dependenteRepository = dependenteRepository;
    }

    public Page<Dispositivo> listar(Pageable pageable) {
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo buscarPorId(Integer id) {
        return dispositivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));
    }

    public Dispositivo criar(DispositivoDTO dto) {
        Dependente dependente = dependenteRepository.findById(dto.getDependenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dependente não encontrado"));

        Dispositivo dispositivo = Dispositivo.builder()
                .tipoDisposit(dto.getTipoDisposit())
                .statusDisposit(dto.getStatusDisposit())
                .dependente(dependente)
                .build();

        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo atualizar(Integer id, DispositivoDTO dto) {
        Dispositivo dispositivo = buscarPorId(id);
        Dependente dependente = dependenteRepository.findById(dto.getDependenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dependente não encontrado"));

        dispositivo.setTipoDisposit(dto.getTipoDisposit());
        dispositivo.setStatusDisposit(dto.getStatusDisposit());
        dispositivo.setDependente(dependente);

        return dispositivoRepository.save(dispositivo);
    }

    public void deletar(Integer id) {
        if (!dispositivoRepository.existsById(id)) {
            throw new EntityNotFoundException("Dispositivo não encontrado");
        }
        dispositivoRepository.deleteById(id);
    }
}
