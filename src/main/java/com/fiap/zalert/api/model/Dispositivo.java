package com.fiap.zalert.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisposit;

    @Size(max = 50)
    private String tipoDisposit;

    @Size(max = 20)
    private String statusDisposit;

    @OneToOne
    @JoinColumn(name = "dependentes_id_depen")
    private Dependente dependente;
}
