package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.DispositivoDTO;
import com.fiap.zalert.api.model.Dispositivo;
import com.fiap.zalert.api.service.DispositivoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    private final DispositivoService dispositivoService;

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @GetMapping
    public ResponseEntity<Page<Dispositivo>> listar(Pageable pageable) {
        return ResponseEntity.ok(dispositivoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(dispositivoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Dispositivo> criar(@Valid @RequestBody DispositivoDTO dto) {
        return ResponseEntity.ok(dispositivoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> atualizar(@PathVariable Integer id, @Valid @RequestBody DispositivoDTO dto) {
        return ResponseEntity.ok(dispositivoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        dispositivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
