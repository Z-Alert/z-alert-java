package com.fiap.zalert.api.controller;

import com.fiap.zalert.api.dto.AuthDTO;
import com.fiap.zalert.api.dto.RegisterDTO;
import com.fiap.zalert.api.dto.LoginResponseDTO;
import com.fiap.zalert.api.model.Usuario;
import com.fiap.zalert.api.repository.UsuarioRepository;
import com.fiap.zalert.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {
        var userPassword = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.senha());
        var auth = authenticationManager.authenticate(userPassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO dto) {
        if (usuarioRepository.findByEmailUsu(dto.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        Usuario novoUsuario = Usuario.builder()
                .emailUsu(dto.email())
                .senhaUsu(senhaCriptografada)
                .nmUsu(dto.nome())
                .role(dto.role())
                .build();
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
