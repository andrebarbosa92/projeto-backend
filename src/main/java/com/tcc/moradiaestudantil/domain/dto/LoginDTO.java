package com.tcc.moradiaestudantil.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {

	private String email;
	private String senha;
	
	public LoginDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
}
