package com.tcc.moradiaestudantil.enums;

public enum Status {

	PENDENTE(0, "pendente"),
	APROVADO(1, "aprovado"),
	REPROVADO(2, "reprovado");

	private int codigo;
	private String descricao;
	
	private Status(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum (Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (Status x : Status.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
