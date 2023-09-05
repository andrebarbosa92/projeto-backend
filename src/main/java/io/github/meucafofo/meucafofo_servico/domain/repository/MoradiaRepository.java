package io.github.meucafofo.meucafofo_servico.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.meucafofo.meucafofo_servico.domain.entity.Locador;
import io.github.meucafofo.meucafofo_servico.domain.entity.Moradia;

@Repository
public interface MoradiaRepository extends JpaRepository<Moradia, Long>{

	
	List<Moradia> findByLocador(Locador locador);
}
