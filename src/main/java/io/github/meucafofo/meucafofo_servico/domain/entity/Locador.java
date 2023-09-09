package io.github.meucafofo.meucafofo_servico.domain.entity;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Locador")
@DiscriminatorValue("LOCADOR")
@EqualsAndHashCode(callSuper = true)
public class Locador extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3959533180189105610L;

	public Locador() {
		super();
	}

	private String cgc;

	public Locador(Long id, String nome, String sobrenome, LocalDate dataNasc, String sexo, String email,
			String telefone, String senha, boolean ativo, String cgc) {
		super(id, nome, sobrenome, dataNasc, sexo, email, telefone, senha, ativo);
		this.cgc = cgc;
	}
}