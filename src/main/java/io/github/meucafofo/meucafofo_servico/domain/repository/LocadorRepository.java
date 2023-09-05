package io.github.meucafofo.meucafofo_servico.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.meucafofo.meucafofo_servico.domain.entity.Locador;

@Repository
public interface LocadorRepository extends JpaRepository<Locador, Long>{

}
