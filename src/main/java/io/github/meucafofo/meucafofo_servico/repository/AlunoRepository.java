package io.github.meucafofo.meucafofo_servico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.meucafofo.meucafofo_servico.domain.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
