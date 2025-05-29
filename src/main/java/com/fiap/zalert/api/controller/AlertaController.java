package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.AlertaDTO;
import com.fiap.zalert.api.model.Alerta;
import com.fiap.zalert.api.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Alertas", description = "Gerenciamento dos alertas de desaparecidos")
@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @Operation(summary = "Lista alertas paginados")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Alerta>>> listar(Pageable pageable) {
        Page<Alerta> page = alertaService.listar(pageable);
        PagedModel<EntityModel<Alerta>> pagedModel = toPagedModel(page);
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca alerta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Alerta>> buscarPorId(@PathVariable Integer id) {
        Alerta alerta = alertaService.buscarPorId(id);
        EntityModel<Alerta> resource = EntityModel.of(alerta);
        resource.add(linkTo(methodOn(AlertaController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(AlertaController.class).listar(Pageable.unpaged())).withRel("alertas"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo alerta")
    @PostMapping
    public ResponseEntity<EntityModel<Alerta>> criar(@Valid @RequestBody AlertaDTO dto) {
        Alerta alerta = alertaService.criar(dto);
        EntityModel<Alerta> resource = EntityModel.of(alerta);
        resource.add(linkTo(methodOn(AlertaController.class).buscarPorId(alerta.getIdAlerta())).withSelfRel());
        resource.add(linkTo(methodOn(AlertaController.class).listar(Pageable.unpaged())).withRel("alertas"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Atualiza um alerta existente")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Alerta>> atualizar(@PathVariable Integer id, @Valid @RequestBody AlertaDTO dto) {
        Alerta alerta = alertaService.atualizar(id, dto);
        EntityModel<Alerta> resource = EntityModel.of(alerta);
        resource.add(linkTo(methodOn(AlertaController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(AlertaController.class).listar(Pageable.unpaged())).withRel("alertas"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Remove um alerta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        alertaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private PagedModel<EntityModel<Alerta>> toPagedModel(Page<Alerta> page) {
        Link link = linkTo(methodOn(AlertaController.class).listar(page.getPageable())).withSelfRel();
        return PagedModel.of(
                page.map(EntityModel::of).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()),
                link);
    }
}
