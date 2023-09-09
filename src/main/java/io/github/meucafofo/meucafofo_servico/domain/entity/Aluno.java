package io.github.meucafofo.meucafofo_servico.domain.entity;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Aluno")
@DiscriminatorValue("ALUNO")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	
	public Aluno() {
		super();
	}
	
	public Aluno(Long id, String nome, String sobrenome, LocalDate dataNasc, String sexo, String email, String senha, boolean ativo, String cpf) {
		super(id, nome, sobrenome, dataNasc, sexo, email, senha, ativo);
		this.cpf = cpf;
	}
}
