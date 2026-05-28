package com.dojo.OnePieceDojo.dtos;

import com.dojo.OnePieceDojo.entities.Pirata;
import com.dojo.OnePieceDojo.enums.Racas;
import com.dojo.OnePieceDojo.enums.Status;
import com.dojo.OnePieceDojo.enums.Tripulacoes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PirataDTO {

    private Long id;

    @NotBlank(message = "O nome do pirata é obrigatório.")
    private String nome;

    @NotNull(message = "A raça é obrigatória.")
    private Racas raca;

    private Tripulacoes tripulacao;
    private Status status;

    public PirataDTO(Pirata pirata) {
        this.id = pirata.getId();
        this.nome = pirata.getNome();
        this.raca = pirata.getRaca();
        this.tripulacao = pirata.getTripulacao();
        this.status = pirata.getStatus();
    }
}
