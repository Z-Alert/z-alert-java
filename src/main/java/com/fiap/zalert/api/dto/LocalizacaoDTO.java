package com.fiap.zalert.api.dto;

import jakarta.validation.constraints.*;

public class LocalizacaoDTO {
    @NotNull
    @Digits(integer = 8, fraction = 6)
    private Double latLocali;

    @NotNull
    @Digits(integer = 8, fraction = 6)
    private Double lngLocali;

    @NotNull
    private Integer dependenteId;

    public Double getLatLocali() { return latLocali; }
    public void setLatLocali(Double latLocali) { this.latLocali = latLocali; }

    public Double getLngLocali() { return lngLocali; }
    public void setLngLocali(Double lngLocali) { this.lngLocali = lngLocali; }

    public Integer getDependenteId() { return dependenteId; }
    public void setDependenteId(Integer dependenteId) { this.dependenteId = dependenteId; }
}
