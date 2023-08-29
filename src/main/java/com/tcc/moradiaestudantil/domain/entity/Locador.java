package com.tcc.moradiaestudantil.domain.entity;

import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Locador")
@DiscriminatorValue("Locador")
public class Locador extends Usuario{
	private static final long serialVersionUID = 1L;
	
	private String cgc;

	public Locador() {
	}
	
	public Locador(Long id, String nome, String dataNasc, String sexo, @Email String email, String senha, String cgc) {
		super(id, nome, dataNasc, sexo, email, senha);
		this.cgc = cgc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locador other = (Locador) obj;
		return Objects.equals(id, other.id);
	}
	
	

//    @OneToMany(mappedBy = "locador")
//    private List<DocumentoIdentidade> documentosIdentidade;
//
//    @OneToMany(mappedBy = "locador")
//    private List<DocumentoMoradia> documentosMoradia;
//
//    @OneToMany(mappedBy = "locador")
//    private List<Mensagem> mensagensEnviadas;

//    @OneToMany(mappedBy = "locador")
//    private List<Denuncia> denuncias;
	
	
}
