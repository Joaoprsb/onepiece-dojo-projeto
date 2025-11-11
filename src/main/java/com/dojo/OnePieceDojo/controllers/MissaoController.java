package com.dojo.OnePieceDojo.controllers;

import com.dojo.OnePieceDojo.dtos.MissaoDTO;
import com.dojo.OnePieceDojo.entities.Missao;
import com.dojo.OnePieceDojo.enums.ClassificacaoMissao;
import com.dojo.OnePieceDojo.enums.StatusMissao;
import com.dojo.OnePieceDojo.services.MissaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @PostMapping("/criar")
    public MissaoDTO criarMissao(@RequestBody MissaoDTO missaoDTO) {
        return missaoService.criarMissao(missaoDTO);
    }

    @PutMapping("/atualizar/{id}")
    public Missao atualizarMissao(@PathVariable Long id, @RequestBody Missao missao) {
        return missaoService.atualizarMissao(id, missao);
    }

    @GetMapping("/buscar/{id}")
    public Missao buscarMissaoPorId(@PathVariable Long id) {
        return missaoService.buscarMissaoPorID(id);
    }

    @GetMapping("/buscarTodas")
    public List<Missao> buscarTodasMissoes() {
        return missaoService.buscarTodasMissoes();
    }

    @GetMapping("/danger/{classificacaoMissao}")
    public List<Missao> buscarPorNivelDificuldade(@PathVariable ClassificacaoMissao classificacaoMissao) {
        return missaoService.buscarPorNivelDificuldade(classificacaoMissao);
    }

    @GetMapping("/status/{statusMissao}")
    public List<Missao> buscarPorStatusMissao(@PathVariable StatusMissao statusMissao) {
        return missaoService.buscarPorStatusMissao(statusMissao);
    }
}
