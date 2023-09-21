package com.tcc.moradiaestudantil.domain.entity;

import java.util.Objects;

import com.tcc.moradiaestudantil.domain.dto.AlunoDTO;
import com.tcc.moradiaestudantil.enums.UserRole;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

@Entity(name = "Aluno")
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario{
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	
	public Aluno() {
	}

	public Aluno(Long id, String nome, String dataNasc, String sexo, @Email String email, String senha, String cpf, UserRole role) {
		super(id, nome, dataNasc, sexo, email, senha, role);
		this.cpf = cpf;
	}
	
	public Aluno(AlunoDTO dto) {
		super(dto.getId(), dto.getNome(), dto.getDataNasc(), dto.getSexo(), dto.getEmail(), dto.getSenha(), dto.getRole());
		this.cpf = dto.getCpf();
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
