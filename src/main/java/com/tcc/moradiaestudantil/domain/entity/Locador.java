package com.tcc.moradiaestudantil.domain.entity;

import java.util.Objects;

import com.tcc.moradiaestudantil.domain.dto.LocadorDTO;
import com.tcc.moradiaestudantil.enums.UserRole;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Locador")
@DiscriminatorValue("LOCADOR")
public class Locador extends Usuario{
	private static final long serialVersionUID = 1L;
	
	private String cgc;

	public Locador() {
	}
	
	public Locador(Long id, String nome, String dataNasc, String sexo, @Email String email, String senha, String cgc, UserRole role) {
		super(id, nome, dataNasc, sexo, email, senha, role);
		this.cgc = cgc;
	}
	
	public Locador(LocadorDTO dto) {
		super(dto.getId(), dto.getNome(), dto.getDataNasc(), dto.getSexo(), dto.getEmail(), dto.getSenha(), dto.getRole());
		this.cgc = dto.getCgc();
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
