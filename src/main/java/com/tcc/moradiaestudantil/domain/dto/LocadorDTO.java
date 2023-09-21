package com.tcc.moradiaestudantil.domain.dto;

import java.io.Serializable;

import com.tcc.moradiaestudantil.domain.entity.Locador;
import com.tcc.moradiaestudantil.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LocadorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	private String dataNasc;
	private String sexo;
	private String email;
	private String senha;
	private String cgc;
	private String telefone;
	private UserRole role;
	
	public LocadorDTO(Locador locador) {
		this.id = locador.getId();
		this.nome = locador.getNome();
		this.dataNasc = locador.getDataNasc();
		this.sexo = locador.getSexo();
		this.email = locador.getEmail();
		this.senha = locador.getSenha();
		this.cgc = locador.getCgc();
		this.telefone = locador.getTelefone();
		this.role = locador.getRole();
	}
}
