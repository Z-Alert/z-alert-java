package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.LocalizacaoDTO;
import com.fiap.zalert.api.model.Localizacao;
import com.fiap.zalert.api.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @GetMapping
    public ResponseEntity<Page<Localizacao>> listar(Pageable pageable) {
        return ResponseEntity.ok(localizacaoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localizacao> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(localizacaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Localizacao> criar(@Valid @RequestBody LocalizacaoDTO dto) {
        return ResponseEntity.ok(localizacaoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Localizacao> atualizar(@PathVariable Integer id, @Valid @RequestBody LocalizacaoDTO dto) {
        return ResponseEntity.ok(localizacaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        localizacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
