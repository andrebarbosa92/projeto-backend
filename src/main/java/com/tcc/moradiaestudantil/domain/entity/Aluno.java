package com.tcc.moradiaestudantil.domain.entity;

import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

@Entity(name = "Aluno")
@DiscriminatorValue("Aluno")
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	
	public Aluno() {
	}

	public Aluno(Long id, String nome, String dataNasc, String sexo, @Email String email, String senha, String cpf) {
		super(id, nome, dataNasc, sexo, email, senha);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(id, other.id);
	}
}
