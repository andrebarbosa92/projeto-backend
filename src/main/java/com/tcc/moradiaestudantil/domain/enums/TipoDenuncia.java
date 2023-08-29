package com.tcc.moradiaestudantil.domain.enums;

public enum TipoDenuncia {

	ANUNCIOFALSO(1, "Anuncio Falso"),
	ASSEDIO(2, "Assédio"),
	SAPM(3, "Spam");
	
	private int codigo;
	private String descricao;
	
	private TipoDenuncia(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoDenuncia toEnum (Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (TipoDenuncia x : TipoDenuncia.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
	
}
