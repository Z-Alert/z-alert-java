package com.fiap.zalert.api.dto;

import jakarta.validation.constraints.*;

public class UsuarioDTO {
    @Size(max = 60)
    private String nmUsu;

    @Email
    @Size(max = 100)
    private String emailUsu;

    @Size(max = 100)
    private String senhaUsu;

    public String getNmUsu() { return nmUsu; }
    public void setNmUsu(String nmUsu) { this.nmUsu = nmUsu; }

    public String getEmailUsu() { return emailUsu; }
    public void setEmailUsu(String emailUsu) { this.emailUsu = emailUsu; }

    public String getSenhaUsu() { return senhaUsu; }
    public void setSenhaUsu(String senhaUsu) { this.senhaUsu = senhaUsu; }
}
