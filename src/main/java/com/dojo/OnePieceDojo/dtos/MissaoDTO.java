package com.dojo.OnePieceDojo.dtos;


import com.dojo.OnePieceDojo.entities.Missao;
import com.dojo.OnePieceDojo.enums.ClassificacaoMissao;
import com.dojo.OnePieceDojo.enums.StatusMissao;
import com.dojo.OnePieceDojo.enums.TipoMissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MissaoDTO {

    private Long id;
    private ClassificacaoMissao classificacaoMissao;
    private TipoMissao tipoMissao;
    private StatusMissao statusMissao;
    private Long pirataId;

    public MissaoDTO(Missao missao) {
        this.id = missao.getId();
        this.classificacaoMissao = missao.getClassificacaoMissao();
        this.tipoMissao = missao.getTipoMissao();
        this.statusMissao = missao.getStatusMissao();
        this.pirataId = missao.getPirata() != null ? missao.getPirata().getId() : null;
    }
}
