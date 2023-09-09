package io.github.meucafofo.meucafofo_servico.domain.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Aluno")
@DiscriminatorValue("ALUNO")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	
	public Aluno(Long id, String nome, String sobrenome, LocalDate dataNasc, String sexo, String email, String senha, boolean ativo, String cpf) {
		super(id, nome, sobrenome, dataNasc, sexo, email, senha, ativo);
		this.cpf = cpf;
	}
}
