package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.LocalizacaoDTO;
import com.fiap.zalert.api.model.Localizacao;
import com.fiap.zalert.api.service.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Localizações", description = "Gerenciamento das localizações dos dependentes")
@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @Operation(summary = "Lista localizações paginadas")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Localizacao>>> listar(Pageable pageable) {
        Page<Localizacao> page = localizacaoService.listar(pageable);
        PagedModel<EntityModel<Localizacao>> pagedModel = toPagedModel(page);
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca localização por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Localizacao>> buscarPorId(@PathVariable Integer id) {
        Localizacao localizacao = localizacaoService.buscarPorId(id);
        EntityModel<Localizacao> resource = EntityModel.of(localizacao);
        resource.add(linkTo(methodOn(LocalizacaoController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(LocalizacaoController.class).listar(Pageable.unpaged())).withRel("localizacoes"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria uma nova localização")
    @PostMapping
    public ResponseEntity<EntityModel<Localizacao>> criar(@Valid @RequestBody LocalizacaoDTO dto) {
        Localizacao localizacao = localizacaoService.criar(dto);
        EntityModel<Localizacao> resource = EntityModel.of(localizacao);
        resource.add(linkTo(methodOn(LocalizacaoController.class).buscarPorId(localizacao.getIdLocali())).withSelfRel());
        resource.add(linkTo(methodOn(LocalizacaoController.class).listar(Pageable.unpaged())).withRel("localizacoes"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Atualiza uma localização existente")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Localizacao>> atualizar(@PathVariable Integer id, @Valid @RequestBody LocalizacaoDTO dto) {
        Localizacao localizacao = localizacaoService.atualizar(id, dto);
        EntityModel<Localizacao> resource = EntityModel.of(localizacao);
        resource.add(linkTo(methodOn(LocalizacaoController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(LocalizacaoController.class).listar(Pageable.unpaged())).withRel("localizacoes"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Remove uma localização")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        localizacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private PagedModel<EntityModel<Localizacao>> toPagedModel(Page<Localizacao> page) {
        Link link = linkTo(methodOn(LocalizacaoController.class).listar(page.getPageable())).withSelfRel();
        return PagedModel.of(
                page.map(EntityModel::of).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()),
                link);
    }
}
