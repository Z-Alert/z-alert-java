package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.DispositivoDTO;
import com.fiap.zalert.api.model.Dispositivo;
import com.fiap.zalert.api.service.DispositivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Dispositivos", description = "Gerenciamento dos dispositivos IoT")
@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    private final DispositivoService dispositivoService;

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @Operation(summary = "Lista dispositivos paginados")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Dispositivo>>> listar(Pageable pageable) {
        Page<Dispositivo> page = dispositivoService.listar(pageable);
        PagedModel<EntityModel<Dispositivo>> pagedModel = toPagedModel(page);
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca dispositivo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Dispositivo>> buscarPorId(@PathVariable Integer id) {
        Dispositivo dispositivo = dispositivoService.buscarPorId(id);
        EntityModel<Dispositivo> resource = EntityModel.of(dispositivo);
        resource.add(linkTo(methodOn(DispositivoController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(DispositivoController.class).listar(Pageable.unpaged())).withRel("dispositivos"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo dispositivo")
    @PostMapping
    public ResponseEntity<EntityModel<Dispositivo>> criar(@Valid @RequestBody DispositivoDTO dto) {
        Dispositivo dispositivo = dispositivoService.criar(dto);
        EntityModel<Dispositivo> resource = EntityModel.of(dispositivo);
        resource.add(linkTo(methodOn(DispositivoController.class).buscarPorId(dispositivo.getIdDisposit())).withSelfRel());
        resource.add(linkTo(methodOn(DispositivoController.class).listar(Pageable.unpaged())).withRel("dispositivos"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Atualiza um dispositivo existente")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Dispositivo>> atualizar(@PathVariable Integer id, @Valid @RequestBody DispositivoDTO dto) {
        Dispositivo dispositivo = dispositivoService.atualizar(id, dto);
        EntityModel<Dispositivo> resource = EntityModel.of(dispositivo);
        resource.add(linkTo(methodOn(DispositivoController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(DispositivoController.class).listar(Pageable.unpaged())).withRel("dispositivos"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Remove um dispositivo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        dispositivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private PagedModel<EntityModel<Dispositivo>> toPagedModel(Page<Dispositivo> page) {
        Link link = linkTo(methodOn(DispositivoController.class).listar(page.getPageable())).withSelfRel();
        return PagedModel.of(
                page.map(EntityModel::of).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()),
                link);
    }
}
