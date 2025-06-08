package com.fiap.zalert.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepen;

    @Size(max = 100)
    private String nmDepen;

    @Size(max = 30)
    private String tipo;

    private Integer idadeDepen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id_usu")
    private Usuario usuario;

    @OneToMany(mappedBy = "dependente")
    private List<Alerta> alertas;

    @OneToMany(mappedBy = "dependente")
    private List<Localizacao> localizacoes;

    @OneToOne(mappedBy = "dependente")
    private Dispositivo dispositivo;
}
