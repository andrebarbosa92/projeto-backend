package com.tcc.moradiaestudantil.domain.dto;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.tcc.moradiaestudantil.domain.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	private String dataNasc;
	private String sexo;
	private String email;
	private String senha;
	private String telefone;
	private Collection<? extends GrantedAuthority> tipoUsuario;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.dataNasc = usuario.getDataNasc();
		this.sexo = usuario.getSexo();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.telefone = usuario.getTelefone();
		this.tipoUsuario = usuario.getAuthorities();
	}
}
