package com.dojo.OnePieceDojo.entities;

import com.dojo.OnePieceDojo.enums.ClassificacaoMissao;
import com.dojo.OnePieceDojo.enums.StatusMissao;
import com.dojo.OnePieceDojo.enums.TipoMissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Diz pro Spring que essa classe representa uma tabela no banco de dados
public class Missao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Faz o banco gerar autoamticamente o valor do id (auto incrementa)
    private Long id; // Define o campo 'id' como primary key

    @Column(name = "classificação_missão", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassificacaoMissao classificacaoMissao; // D, C, B, A, S

    @Column(name = "tipo_missão", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMissao tipoMissao; // EXPLORACAO, BATALHA_NAVAL, SAQUE

    @Column(name = "status_missão", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusMissao statusMissao; // CONCLUIDA, EM_ANDAMENTO

    // Cada missão pertence a um pirata só
    @ManyToOne// Indica que várias missões podem estar ligadas a um único pirata
    @JoinColumn(name = "pirata_id", nullable = false) // Cria a chave estrangeira na tabela 'missao', apontando para pirata
    private Pirata pirata;
}
