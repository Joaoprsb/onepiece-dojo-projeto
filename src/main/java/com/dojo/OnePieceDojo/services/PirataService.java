package com.dojo.OnePieceDojo.services;

import com.dojo.OnePieceDojo.dtos.PirataDTO;
import com.dojo.OnePieceDojo.entities.Pirata;
import com.dojo.OnePieceDojo.enums.Racas;
import com.dojo.OnePieceDojo.repositories.PirataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PirataService {

    private final PirataRepository pirataRepository;

    public PirataService(PirataRepository pirataRepository) {
        this.pirataRepository = pirataRepository;
    }

    // Cria um novo pirata
    // Recebe o objeto pirata que vai vir do controller
    public PirataDTO criarPirata(PirataDTO pirataDTO) {
        Pirata pirata = new Pirata();
        pirata.setNome(pirataDTO.getNome());
        pirata.setRaca(pirataDTO.getRaca());
        pirata.setTripulacao(pirataDTO.getTripulacao());
        pirata.setStatus(pirataDTO.getStatus());

        Pirata salvo = pirataRepository.save(pirata);

        return new PirataDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getRaca(),
                salvo.getTripulacao(),
                salvo.getStatus()
        );
    }

    public Pirata atualizarPirata(Long id, Pirata pirataAtualizado) {
        // Busca no banco o pirata que vai ter o ID informado
        // O findById vai retornar
        Pirata pirataExistente = pirataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pirata não encontrado"));

        // Aqui atualiza os dados do pirata com as novas informações
        pirataExistente.setNome(pirataAtualizado.getNome());
        pirataExistente.setRaca(pirataAtualizado.getRaca());
        pirataExistente.setTripulacao(pirataAtualizado.getTripulacao());
        pirataExistente.setStatus(pirataAtualizado.getStatus());

        // Salva o pirata atualizado no banco (UPDATE)
        // Pesquisei e o save percebe que já existe um ID e faz a atualização dele ao invés de inserir um
        return pirataRepository.save(pirataExistente);
    }

    public Pirata buscarPirataPorID(Long id) {
        return pirataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pirata não encontrado"));
    }

    public List<Pirata> buscarPorRaca(Racas raca) {
        return pirataRepository.findByRaca(raca);
    }

    public List<PirataDTO> buscarTodosPiratas() {
        List<Pirata> piratas = pirataRepository.findAll();

        // vai covnerter a lista de entidades para lista de DTOs
        return piratas.stream()
                .map(p -> new PirataDTO(
                        p.getId(),
                        p.getNome(),
                        p.getRaca(),
                        p.getTripulacao(),
                        p.getStatus()
                ))
                .toList();
    }

    public void deletarPirata(Long id) {
        Pirata pirataAtual = pirataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pirata não encontrado"));

        pirataRepository.delete(pirataAtual);
    }
}
