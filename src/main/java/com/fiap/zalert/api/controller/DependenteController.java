package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.DependenteDTO;
import com.fiap.zalert.api.model.Dependente;
import com.fiap.zalert.api.service.DependenteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dependentes")
public class DependenteController {

    private final DependenteService dependenteService;

    public DependenteController(DependenteService dependenteService) {
        this.dependenteService = dependenteService;
    }

    @GetMapping
    public ResponseEntity<Page<Dependente>> listar(Pageable pageable) {
        return ResponseEntity.ok(dependenteService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dependente> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(dependenteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Dependente> criar(@Valid @RequestBody DependenteDTO dto) {
        return ResponseEntity.ok(dependenteService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dependente> atualizar(@PathVariable Integer id, @Valid @RequestBody DependenteDTO dto) {
        return ResponseEntity.ok(dependenteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        dependenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
