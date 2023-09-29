package com.tcc.moradiaestudantil.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(Include.NON_NULL)
@Builder
public class UsuarioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4339610453976677074L;
	
	private Long id;
	private String nome;
	private String cpf;
	private String dataNasc;
	private String sexo;
	private String email;
	private String senha;
	private String telefone;
	private TipoUsuario tipoUsuario;
	private Status status;
	private UserRole role;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.dataNasc = usuario.getDataNasc();
		this.sexo = usuario.getSexo();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.telefone = usuario.getTelefone();
		this.tipoUsuario = TipoUsuario.toEnum(usuario.getTipoUsuario());
		this.status = Status.toEnum(usuario.getStatus());
		this.role = usuario.getRole();
	}
}
