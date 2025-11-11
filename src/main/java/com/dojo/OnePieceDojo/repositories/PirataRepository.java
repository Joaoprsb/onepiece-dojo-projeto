package com.dojo.OnePieceDojo.repositories;

import com.dojo.OnePieceDojo.entities.Pirata;
import com.dojo.OnePieceDojo.enums.Racas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PirataRepository extends JpaRepository<Pirata, Long> {

    List<Pirata> findByRaca(Racas raca);

}
