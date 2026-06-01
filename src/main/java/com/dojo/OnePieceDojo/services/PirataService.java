package com.dojo.OnePieceDojo.services;

import com.dojo.OnePieceDojo.dtos.PirataDTO;
import com.dojo.OnePieceDojo.entities.Pirata;
import com.dojo.OnePieceDojo.enums.Racas;
import com.dojo.OnePieceDojo.exception.PirataNotFoundException;
import com.dojo.OnePieceDojo.repositories.PirataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PirataService {
    private final PirataRepository pirataRepository;

    public PirataService(PirataRepository pirataRepository) {
        this.pirataRepository = pirataRepository;
    }

    public PirataDTO criarPirata(PirataDTO pirataDTO) {
        Pirata pirata = new Pirata();
        pirata.setNome(pirataDTO.getNome());
        pirata.setRaca(pirataDTO.getRaca());
        pirata.setTripulacao(pirataDTO.getTripulacao());
        pirata.setStatus(pirataDTO.getStatus());

        Pirata salvo = pirataRepository.save(pirata);

        return new PirataDTO(salvo);
    }

    public PirataDTO atualizarPirata(Long id, PirataDTO pirataDTO) {
        Pirata pirataExistente = pirataRepository.findById(id)
                .orElseThrow(() -> new PirataNotFoundException("Pirata não encontrado"));

        pirataExistente.setNome(pirataDTO.getNome());
        pirataExistente.setRaca(pirataDTO.getRaca());
        pirataExistente.setTripulacao(pirataDTO.getTripulacao());
        pirataExistente.setStatus(pirataDTO.getStatus());

        Pirata salvo = pirataRepository.save(pirataExistente);

        return new PirataDTO(salvo);
    }

    public Page<PirataDTO> buscarTodosPiratas(Pageable pageable) {
        Page<Pirata> piratas = pirataRepository.findAll(pageable);

        return piratas.map(PirataDTO::new);
    }

    public PirataDTO buscarPirataPorID(Long id) {
        Pirata pirata = pirataRepository.findById(id)
                .orElseThrow(() -> new PirataNotFoundException("Pirata não encontrado."));

        return new PirataDTO(pirata);
    }

    public List<PirataDTO> buscarPorRaca(Racas raca) {
        return pirataRepository.findByRaca(raca)
                .stream()
                .map(PirataDTO::new)
                .toList();
    }

    public void deletarPirata(Long id) {
        Pirata pirataAtual = pirataRepository.findById(id)
                .orElseThrow(() -> new PirataNotFoundException("Pirata não encontrado"));

        pirataRepository.delete(pirataAtual);
    }
}
