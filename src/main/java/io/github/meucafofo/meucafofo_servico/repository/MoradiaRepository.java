package io.github.meucafofo.meucafofo_servico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.meucafofo.meucafofo_servico.domain.entity.Locador;
import io.github.meucafofo.meucafofo_servico.domain.entity.Moradia;

public interface MoradiaRepository extends JpaRepository<Moradia, Long>{

	
	List<Moradia> findByLocador(Locador locador);
}
