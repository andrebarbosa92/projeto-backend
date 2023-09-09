package io.github.meucafofo.meucafofo_servico.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1964160326362683759L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	protected Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sobrenome")
	private String sobrenome;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNasc;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Email
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "senha")
	private String senha;
	
	@Column(name = "ativo")
	private Boolean ativo;

	public Usuario(Long id, String nome, String sobrenome, LocalDate dataNasc, String sexo, @Email String email, String senha, boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
		this.email = email;
		this.senha = senha;
		this.ativo = ativo;
	}

	@JsonIgnore
    @OneToMany(mappedBy = "acusador")
    private List<Denuncia> denunciasAcusador = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "alvo")
    private List<Denuncia> denunciasAlvo = new ArrayList<>();
    
    @JsonIgnore
	@OneToMany(mappedBy = "locador")
 	private List<Moradia> moradia;
}
