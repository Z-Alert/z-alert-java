package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.AlertaDTO;
import com.fiap.zalert.api.model.Alerta;
import com.fiap.zalert.api.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @GetMapping
    public ResponseEntity<Page<Alerta>> listar(Pageable pageable) {
        return ResponseEntity.ok(alertaService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alerta> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(alertaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Alerta> criar(@Valid @RequestBody AlertaDTO dto) {
        return ResponseEntity.ok(alertaService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alerta> atualizar(@PathVariable Integer id, @Valid @RequestBody AlertaDTO dto) {
        return ResponseEntity.ok(alertaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        alertaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
