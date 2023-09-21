package com.tcc.moradiaestudantil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.moradiaestudantil.domain.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	
	Optional<Aluno> findByEmail(String email);

	Optional<Aluno> findByCpf(String cpf);

}
