package io.github.meucafofo.meucafofo_servico.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoDenuncia {

	ANUNCIOFALSO(1, "Anuncio Falso"),
	ASSEDIO(2, "Assédio"),
	SAPM(3, "Spam");
	
	private int codigo;
	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoDenuncia toEnum (int cod) {
		for (TipoDenuncia tipoDenuncia : TipoDenuncia.values()) {
			if(cod == tipoDenuncia.getCodigo()) {
				return tipoDenuncia;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
