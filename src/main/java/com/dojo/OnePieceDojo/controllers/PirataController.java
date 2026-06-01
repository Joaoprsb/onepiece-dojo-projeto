package com.dojo.OnePieceDojo.controllers;

import com.dojo.OnePieceDojo.dtos.PirataDTO;
import com.dojo.OnePieceDojo.enums.Racas;
import com.dojo.OnePieceDojo.services.PirataService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/piratas")
@Tag(name = "Piratas", description = "Endpoints relacionados aos piratas")
public class PirataController {

    private final PirataService pirataService;

    public PirataController(PirataService pirataService) {
        this.pirataService = pirataService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo pirata")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pirata criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    public ResponseEntity<PirataDTO> criarPirata(
            @Valid @RequestBody PirataDTO pirataDTO) {
        PirataDTO novoPirata = pirataService.criarPirata(pirataDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoPirata);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pirata")
    public ResponseEntity<PirataDTO> atualizarPirata(
            @PathVariable Long id,
            @RequestBody PirataDTO pirataDTO) {
        return ResponseEntity.ok(
                pirataService.atualizarPirata(id, pirataDTO)
        );
    }

    @GetMapping
    @Operation(summary = "Lista todos os piratas com paginação")
    public ResponseEntity<Page<PirataDTO>> buscarTodosPiratas(Pageable pageable) {
        return ResponseEntity.ok(
                pirataService.buscarTodosPiratas(pageable)
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um pirata por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pirata encontrado."),
            @ApiResponse(responseCode = "404", description = "Pirata não encontrado.")
    })
    public ResponseEntity<PirataDTO> buscarPirataPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                pirataService.buscarPirataPorID(id)
        );
    }

    @GetMapping("/raca/{raca}")
    @Operation(summary = "Busca um pirata por raça")
    public ResponseEntity<List<PirataDTO>> buscarPorRaca(
            @PathVariable Racas raca) {
        return ResponseEntity.ok(
                pirataService.buscarPorRaca(raca)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um pirata")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pirata removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pirata não encontrado.")
    })
    public ResponseEntity<Void> deletarPirata(
            @PathVariable Long id) {
        pirataService.deletarPirata(id);
        return ResponseEntity.noContent().build();
    }
}