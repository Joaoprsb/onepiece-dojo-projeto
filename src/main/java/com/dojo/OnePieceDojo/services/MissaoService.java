package com.dojo.OnePieceDojo.services;

import com.dojo.OnePieceDojo.dtos.MissaoDTO;
import com.dojo.OnePieceDojo.entities.Missao;
import com.dojo.OnePieceDojo.entities.Pirata;
import com.dojo.OnePieceDojo.enums.ClassificacaoMissao;
import com.dojo.OnePieceDojo.enums.StatusMissao;
import com.dojo.OnePieceDojo.exception.MissaoNotFoundException;
import com.dojo.OnePieceDojo.repositories.MissaoRepository;
import com.dojo.OnePieceDojo.repositories.PirataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new MissaoNotFoundException("Pirata não encontrado"));

        Missao missao = new Missao();
        missao.setClassificacaoMissao(missaoDTO.getClassificacaoMissao());
        missao.setTipoMissao(missaoDTO.getTipoMissao());
        missao.setStatusMissao(missaoDTO.getStatusMissao());
        missao.setPirata(pirata);

        Missao salvo = missaoRepository.save(missao);

        return new MissaoDTO(salvo);
    }

    public MissaoDTO atualizarMissao(Long id, MissaoDTO missaoDTO) {
        Missao missaoExistente = missaoRepository.findById(id)
                .orElseThrow(() -> new MissaoNotFoundException("Missão não encontrada"));

        missaoExistente.setClassificacaoMissao(missaoDTO.getClassificacaoMissao());
        missaoExistente.setTipoMissao(missaoDTO.getTipoMissao());
        missaoExistente.setStatusMissao(missaoDTO.getStatusMissao());

        Missao missaoSalva = missaoRepository.save(missaoExistente);

        return new MissaoDTO(missaoSalva);

    }

    public MissaoDTO buscarMissaoPorID(Long id) {
        Missao missao = missaoRepository.findById(id)
                .orElseThrow(() -> new MissaoNotFoundException("Missão não encontrada."));

        return new MissaoDTO(missao);
    }

    public Page<MissaoDTO> buscarTodasMissoes(Pageable pageable) {
        Page<Missao> missoes = missaoRepository.findAll(pageable);

        return missoes.map(MissaoDTO::new);
    }

    public List<MissaoDTO> buscarPorNivelDificuldade(ClassificacaoMissao classificacaoMissao) {
        return missaoRepository.findByClassificacaoMissao(classificacaoMissao)
                .stream()
                .map(MissaoDTO::new)
                .toList();
    }

    public List<MissaoDTO> buscarPorStatusMissao(StatusMissao statusMissao) {
        return missaoRepository.findByStatusMissao(statusMissao)
                .stream()
                .map(MissaoDTO::new)
                .toList();
    }
}
