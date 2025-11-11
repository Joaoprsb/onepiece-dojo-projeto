package com.dojo.OnePieceDojo.repositories;

import com.dojo.OnePieceDojo.entities.Missao;
import com.dojo.OnePieceDojo.enums.ClassificacaoMissao;
import com.dojo.OnePieceDojo.enums.StatusMissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissaoRepository extends JpaRepository<Missao, Long> {

    List<Missao> findByClassificacaoMissao(ClassificacaoMissao classificacaoMissao);

    List<Missao> findByStatusMissao(StatusMissao statusMissao);
}
