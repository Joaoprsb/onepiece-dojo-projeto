package com.dojo.OnePieceDojo.entities;

import com.dojo.OnePieceDojo.enums.Racas;
import com.dojo.OnePieceDojo.enums.Status;
import com.dojo.OnePieceDojo.enums.Tripulacoes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Pirata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_pirata", nullable = false)
    private String nome;

    @Column(name = "raça_pirata", nullable = false)
    @Enumerated(EnumType.STRING)
    private Racas raca;

    @Column(name = "tripulação_pirata", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tripulacoes tripulacao;

    @Column(name = "status_pirata", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    // Um pirata pode ter várias missões
    // cascade = CascadeType.ALL - faz com que quando salve/delete o pirata, a missão também seja afetada
    // orphanRemoval = true - remove as missões "órfãs" (as que não tem um pirata)
    @OneToMany(mappedBy = "pirata", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Missao> missoes = new ArrayList<>();
}
