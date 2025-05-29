package com.fiap.zalert.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocali;

    @Digits(integer = 8, fraction = 6)
    private Double latLocali;

    @Digits(integer = 8, fraction = 6)
    private Double lngLocali;

    private LocalDateTime dataHora;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dependentes_id_depen")
    private Dependente dependente;
}
