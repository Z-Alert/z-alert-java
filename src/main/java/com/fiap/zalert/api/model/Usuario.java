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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsu;

    @Size(max = 60)
    private String nmUsu;

    @Email
    @Size(max = 100)
    private String emailUsu;

    @Size(max = 100)
    private String senhaUsu;

    @OneToMany(mappedBy = "usuario")
    private List<Dependente> dependentes;
}
