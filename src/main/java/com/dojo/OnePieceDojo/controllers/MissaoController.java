package com.dojo.OnePieceDojo.controllers;

import com.dojo.OnePieceDojo.dtos.MissaoDTO;
import com.dojo.OnePieceDojo.entities.Missao;
import com.dojo.OnePieceDojo.enums.ClassificacaoMissao;
import com.dojo.OnePieceDojo.enums.StatusMissao;
import com.dojo.OnePieceDojo.services.MissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
@Tag(name = "Missões", description = "Endpoints relacionados às missões")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @PostMapping
    @Operation(summary = "Cria uma nova missão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<MissaoDTO> criarMissao(
            @Valid @RequestBody MissaoDTO missaoDTO) {

        MissaoDTO novaMissao = missaoService.criarMissao(missaoDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novaMissao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma missão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<Missao> atualizarMissao(
            @PathVariable Long id,
            @RequestBody Missao missao) {

        return ResponseEntity.ok(
                missaoService.atualizarMissao(id, missao)
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma missão pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<Missao> buscarMissaoPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                missaoService.buscarMissaoPorID(id)
        );
    }

    @GetMapping
    @Operation(summary = "Lista todas as missões com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de missões retornada com sucesso")
    })
    public ResponseEntity<Page<MissaoDTO>> buscarTodasMissoes(
            Pageable pageable) {

        return ResponseEntity.ok(
                missaoService.buscarTodasMissoes(pageable)
        );
    }

    @GetMapping("/classificacao/{classificacaoMissao}")
    @Operation(summary = "Busca missões pela classificação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missões encontradas")
    })
    public ResponseEntity<List<Missao>> buscarPorNivelDificuldade(
            @PathVariable ClassificacaoMissao classificacaoMissao) {

        return ResponseEntity.ok(
                missaoService.buscarPorNivelDificuldade(classificacaoMissao)
        );
    }

    @GetMapping("/status/{statusMissao}")
    @Operation(summary = "Busca missões pelo status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missões encontradas")
    })
    public ResponseEntity<List<Missao>> buscarPorStatusMissao(
            @PathVariable StatusMissao statusMissao) {

        return ResponseEntity.ok(
                missaoService.buscarPorStatusMissao(statusMissao)
        );
    }
}