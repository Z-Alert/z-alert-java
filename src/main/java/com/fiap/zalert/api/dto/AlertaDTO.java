package com.fiap.zalert.api.dto;

import jakarta.validation.constraints.*;

public class AlertaDTO {
    @Size(max = 255)
    private String localizacao;

    @Size(max = 50)
    private String sttsAlerta;

    @NotNull
    private Integer dependenteId;

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public String getSttsAlerta() { return sttsAlerta; }
    public void setSttsAlerta(String sttsAlerta) { this.sttsAlerta = sttsAlerta; }

    public Integer getDependenteId() { return dependenteId; }
    public void setDependenteId(Integer dependenteId) { this.dependenteId = dependenteId; }
}
