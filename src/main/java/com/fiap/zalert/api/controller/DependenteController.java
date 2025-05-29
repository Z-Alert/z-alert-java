package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.DependenteDTO;
import com.fiap.zalert.api.model.Dependente;
import com.fiap.zalert.api.service.DependenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Dependentes", description = "Gerenciamento dos dependentes (pessoas e pets)")
@RestController
@RequestMapping("/api/dependentes")
public class DependenteController {

    private final DependenteService dependenteService;

    public DependenteController(DependenteService dependenteService) {
        this.dependenteService = dependenteService;
    }

    @Operation(summary = "Lista dependentes paginados")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Dependente>>> listar(Pageable pageable) {
        Page<Dependente> page = dependenteService.listar(pageable);
        PagedModel<EntityModel<Dependente>> pagedModel = toPagedModel(page);
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca dependente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Dependente>> buscarPorId(@PathVariable Integer id) {
        Dependente dependente = dependenteService.buscarPorId(id);
        EntityModel<Dependente> resource = EntityModel.of(dependente);
        resource.add(linkTo(methodOn(DependenteController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(DependenteController.class).listar(Pageable.unpaged())).withRel("dependentes"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo dependente")
    @PostMapping
    public ResponseEntity<EntityModel<Dependente>> criar(@Valid @RequestBody DependenteDTO dto) {
        Dependente dependente = dependenteService.criar(dto);
        EntityModel<Dependente> resource = EntityModel.of(dependente);
        resource.add(linkTo(methodOn(DependenteController.class).buscarPorId(dependente.getIdDepen())).withSelfRel());
        resource.add(linkTo(methodOn(DependenteController.class).listar(Pageable.unpaged())).withRel("dependentes"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Atualiza um dependente existente")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Dependente>> atualizar(@PathVariable Integer id, @Valid @RequestBody DependenteDTO dto) {
        Dependente dependente = dependenteService.atualizar(id, dto);
        EntityModel<Dependente> resource = EntityModel.of(dependente);
        resource.add(linkTo(methodOn(DependenteController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(DependenteController.class).listar(Pageable.unpaged())).withRel("dependentes"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Remove um dependente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        dependenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private PagedModel<EntityModel<Dependente>> toPagedModel(Page<Dependente> page) {
        Link link = linkTo(methodOn(DependenteController.class).listar(page.getPageable())).withSelfRel();
        return PagedModel.of(
                page.map(EntityModel::of).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()),
                link);
    }
}
