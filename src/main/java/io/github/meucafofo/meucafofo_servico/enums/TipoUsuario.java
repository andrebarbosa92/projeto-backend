package io.github.meucafofo.meucafofo_servico.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoUsuario {

	ALUNO(1, "ALUNO"),
	ADMINISTRADOR(2, "ADMINISTRADOR"),
	LOCADOR(3, "LOCADOR");
	
	private int codigo;
	private String descricao;
	
	public static TipoUsuario toEnum (int cod) {		
		for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
			if(tipoUsuario.getCodigo() == cod) {
				return tipoUsuario;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
