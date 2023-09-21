package com.tcc.moradiaestudantil.domain.dto;

import java.io.Serializable;

import com.tcc.moradiaestudantil.domain.entity.Aluno;
import com.tcc.moradiaestudantil.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AlunoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	private String dataNasc;
	private String sexo;
	private String email;
	private String senha;
	private String cpf;
	private String telefone;
	private UserRole role;
	
	public AlunoDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.dataNasc = aluno.getDataNasc();
		this.sexo = aluno.getSexo();
		this.email = aluno.getEmail();
		this.senha = aluno.getSenha();
		this.cpf = aluno.getCpf();
		this.telefone = aluno.getTelefone();
		this.role = aluno.getRole();
	}
}
