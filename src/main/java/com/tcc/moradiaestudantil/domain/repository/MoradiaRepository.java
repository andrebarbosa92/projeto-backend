package com.tcc.moradiaestudantil.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.moradiaestudantil.domain.entity.Locador;
import com.tcc.moradiaestudantil.domain.entity.Moradia;

@Repository
public interface MoradiaRepository extends JpaRepository<Moradia, Long>{

	
	List<Moradia> findByLocador(Locador locador);
}
