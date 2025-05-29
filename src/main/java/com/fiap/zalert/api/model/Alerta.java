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
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlerta;

    private LocalDateTime dataHora;

    @Size(max = 255)
    private String localizacao;

    @Size(max = 50)
    private String sttsAlerta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dependentes_id_depen")
    private Dependente dependente;
}
