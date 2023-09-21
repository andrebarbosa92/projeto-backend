package com.tcc.moradiaestudantil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcc.moradiaestudantil.domain.entity.Locador;
import com.tcc.moradiaestudantil.domain.entity.Moradia;

@Repository
public interface MoradiaRepository extends JpaRepository<Moradia, Long>{

	
	List<Moradia> findByLocador(Locador locador);
	
	@Query("select m from Moradia m where m.tipoMoradia = ?1 and m.preco <= ?2")
	List<Moradia> buscarTeste(String tipoMoradia, Double preco);
}
