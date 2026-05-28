package com.dojo.OnePieceDojo.controllers;

import com.dojo.OnePieceDojo.dtos.PirataDTO;
import com.dojo.OnePieceDojo.entities.Pirata;
import com.dojo.OnePieceDojo.enums.Racas;
import com.dojo.OnePieceDojo.services.PirataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/piratas")
public class PirataController {

    private final PirataService pirataService;

    public PirataController(PirataService pirataService) {
        this.pirataService = pirataService;
    }

    @PostMapping
    public ResponseEntity<PirataDTO> criarPirata(
            @Valid @RequestBody PirataDTO pirataDTO) {
        PirataDTO novoPirata = pirataService.criarPirata(pirataDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoPirata);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pirata> atualizarPirata(
            @PathVariable Long id,
            @RequestBody Pirata pirataAtualizado) {
        return ResponseEntity.ok(
                pirataService.atualizarPirata(id, pirataAtualizado)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pirata> buscarPirataPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                pirataService.buscarPirataPorID(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<PirataDTO>> buscarTodosPiratas() {
        return ResponseEntity.ok(
                pirataService.buscarTodosPiratas()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPirata(
            @PathVariable Long id) {
        pirataService.deletarPirata(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/raca/{raca}")
    public ResponseEntity<List<Pirata>> buscarPorRaca(
            @PathVariable Racas raca) {
        return ResponseEntity.ok(
                pirataService.buscarPorRaca(raca)
        );
    }
}