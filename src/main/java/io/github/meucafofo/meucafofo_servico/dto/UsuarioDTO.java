package io.github.meucafofo.meucafofo_servico.dto;

import java.io.Serializable;
import java.time.LocalDate;

import io.github.meucafofo.meucafofo_servico.domain.entity.Usuario;
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
	private LocalDate dataNasc;
	private String sexo;
	private String email;
//	private String senha;
//	private TipoUsuario tipo;
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.dataNasc = usuario.getDataNasc();
		this.sexo = usuario.getSexo();
		this.email = usuario.getEmail();
//		this.senha = usuario.getSenha();
//		this.tipo = usuario.getTipoUsuario();
	}
}
