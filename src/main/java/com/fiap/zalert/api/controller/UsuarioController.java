package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.UsuarioDTO;
import com.fiap.zalert.api.model.Usuario;
import com.fiap.zalert.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Usuários", description = "Gerenciamento de usuários")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Lista usuários paginados")
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Usuario>>> listar(Pageable pageable) {
        Page<Usuario> page = usuarioService.listar(pageable);
        PagedModel<EntityModel<Usuario>> pagedModel = toPagedModel(page);
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Busca usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> buscarPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        EntityModel<Usuario> resource = EntityModel.of(usuario);
        resource.add(linkTo(methodOn(UsuarioController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(UsuarioController.class).listar(Pageable.unpaged())).withRel("usuarios"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Cria um novo usuário")
    @PostMapping
    public ResponseEntity<EntityModel<Usuario>> criar(@Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.criar(dto);
        EntityModel<Usuario> resource = EntityModel.of(usuario);
        resource.add(linkTo(methodOn(UsuarioController.class).buscarPorId(usuario.getIdUsu())).withSelfRel());
        resource.add(linkTo(methodOn(UsuarioController.class).listar(Pageable.unpaged())).withRel("usuarios"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Atualiza um usuário existente")
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> atualizar(@PathVariable Integer id, @Valid @RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.atualizar(id, dto);
        EntityModel<Usuario> resource = EntityModel.of(usuario);
        resource.add(linkTo(methodOn(UsuarioController.class).buscarPorId(id)).withSelfRel());
        resource.add(linkTo(methodOn(UsuarioController.class).listar(Pageable.unpaged())).withRel("usuarios"));
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Remove um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private PagedModel<EntityModel<Usuario>> toPagedModel(Page<Usuario> page) {
        Link link = linkTo(methodOn(UsuarioController.class).listar(page.getPageable())).withSelfRel();
        return PagedModel.of(
                page.map(EntityModel::of).toList(),
                new PagedModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()),
                link);
    }
}
