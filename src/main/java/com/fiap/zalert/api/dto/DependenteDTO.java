package com.fiap.zalert.api.dto;

import jakarta.validation.constraints.*;

public class DependenteDTO {
    @Size(max = 100)
    private String nmDepen;

    @Size(max = 30)
    private String tipo;

    private Integer idadeDepen;

    @NotNull
    private Integer usuarioId;

    public String getNmDepen() { return nmDepen; }
    public void setNmDepen(String nmDepen) { this.nmDepen = nmDepen; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getIdadeDepen() { return idadeDepen; }
    public void setIdadeDepen(Integer idadeDepen) { this.idadeDepen = idadeDepen; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
}
