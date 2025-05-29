package com.fiap.zalert.api.dto;

import jakarta.validation.constraints.*;

public class DispositivoDTO {
    @Size(max = 50)
    private String tipoDisposit;

    @Size(max = 20)
    private String statusDisposit;

    @NotNull
    private Integer dependenteId;

    public String getTipoDisposit() { return tipoDisposit; }
    public void setTipoDisposit(String tipoDisposit) { this.tipoDisposit = tipoDisposit; }

    public String getStatusDisposit() { return statusDisposit; }
    public void setStatusDisposit(String statusDisposit) { this.statusDisposit = statusDisposit; }

    public Integer getDependenteId() { return dependenteId; }
    public void setDependenteId(Integer dependenteId) { this.dependenteId = dependenteId; }
}
