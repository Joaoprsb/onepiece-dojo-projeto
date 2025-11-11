package com.dojo.OnePieceDojo.services;

import com.dojo.OnePieceDojo.dtos.MissaoDTO;
import com.dojo.OnePieceDojo.entities.Missao;
import com.dojo.OnePieceDojo.entities.Pirata;
import com.dojo.OnePieceDojo.enums.ClassificacaoMissao;
import com.dojo.OnePieceDojo.enums.StatusMissao;
import com.dojo.OnePieceDojo.repositories.MissaoRepository;
import com.dojo.OnePieceDojo.repositories.PirataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;
    private final PirataRepository pirataRepository;

    public MissaoService(MissaoRepository missaoRepository, PirataRepository pirataRepository) {
        this.missaoRepository = missaoRepository;
        this.pirataRepository = pirataRepository;
    }

    public MissaoDTO criarMissao(MissaoDTO missaoDTO){
        Pirata pirata = pirataRepository.findById(missaoDTO.getPirataId())
                .orElseThrow(() -> new RuntimeException("Pirata não encontrado"));

        Missao missao = new Missao();
        missao.setClassificacaoMissao(missaoDTO.getClassificacaoMissao());
        missao.setTipoMissao(missaoDTO.getTipoMissao());
        missao.setStatusMissao(missaoDTO.getStatusMissao());
        missao.setPirata(pirata);

        Missao salvo = missaoRepository.save(missao);

        return new MissaoDTO(salvo);
    }

    public Missao atualizarMissao(Long id, Missao missaoAtualizada) {
        Missao missaoExistente = missaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));

        missaoExistente.setClassificacaoMissao(missaoAtualizada.getClassificacaoMissao());
        missaoExistente.setTipoMissao(missaoAtualizada.getTipoMissao());
        missaoExistente.setStatusMissao(missaoAtualizada.getStatusMissao());

        return missaoRepository.save(missaoExistente);
    }

    public Missao buscarMissaoPorID(Long id) {
        return missaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Missão não encontrada"));
    }

    public List<Missao> buscarTodasMissoes() {
        return missaoRepository.findAll();
    }

    public List<Missao> buscarPorNivelDificuldade(ClassificacaoMissao classificacaoMissao) {
        return missaoRepository.findByClassificacaoMissao(classificacaoMissao);
    }

    public List<Missao> buscarPorStatusMissao(StatusMissao statusMissao) {
        return missaoRepository.findByStatusMissao(statusMissao);
    }
}
