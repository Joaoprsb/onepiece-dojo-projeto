package com.dojo.OnePieceDojo.controllers;

import com.dojo.OnePieceDojo.dtos.PirataDTO;
import com.dojo.OnePieceDojo.entities.Pirata;
import com.dojo.OnePieceDojo.enums.Racas;
import com.dojo.OnePieceDojo.services.PirataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/piratas")
public class PirataController {

    private final PirataService pirataService;

    public PirataController(PirataService pirataService) {
        this.pirataService = pirataService;
    }

    @PostMapping("/criar")
    public PirataDTO criarPirata(@RequestBody PirataDTO pirataDTO) {
        return pirataService.criarPirata(pirataDTO);
    }

    @PutMapping("/atualizar/{id}")
    public Pirata atualizarPirata(@PathVariable Long id, @RequestBody Pirata pirataAtualizado) {
        return pirataService.atualizarPirata(id, pirataAtualizado);
    }

    @GetMapping("/buscar/{id}")
    public Pirata buscarPirataPorId(@PathVariable Long id) {
        return pirataService.buscarPirataPorID(id);
    }

    @GetMapping("/buscarTodos")
    public List<PirataDTO> buscarTodosPiratas() {
        return pirataService.buscarTodosPiratas();
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPirata(@PathVariable Long id) {
        pirataService.deletarPirata(id);
    }

    @GetMapping("/buscarRaca/{raca}")
    public List<Pirata> buscarPorRaca(@PathVariable Racas raca) {
        return pirataService.buscarPorRaca(raca);
    }

}
