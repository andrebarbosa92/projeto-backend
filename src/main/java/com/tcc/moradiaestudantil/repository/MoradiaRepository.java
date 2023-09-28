package com.tcc.moradiaestudantil.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.domain.entity.Usuario;

@Repository
public interface MoradiaRepository extends JpaRepository<Moradia, Long>{

	
	Page<Moradia> findByLocador(Usuario locador, PageRequest pageable);
	
	@Query("select m from Moradia m where m.tipoMoradia = ?1 and m.preco <= ?2")
	Page<Moradia> buscarTeste(String tipoMoradia, Double preco, PageRequest pageable);

}
