package com.tcc.moradiaestudantil.domain.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Moradia")
@EqualsAndHashCode(of = "id")
public class Moradia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_moradia")
	private Integer id;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "preco")
	private Double preco;
	
	@Column(name = "tipo_de_moradia")
	private String tipoMoradia;
	
	@ManyToOne
	@JoinColumn(name="id_locador")
	private Usuario locador;
	
    // Relacionamentos
    @OneToMany(mappedBy = "moradia")
    private List<Agendamento> agendamentos;
    
    
    public Moradia() {
    }

	public Moradia(Integer id, String endereco, String bairro, String cep, String descricao, Double preco,
			String tipoMoradia, Usuario locador) {
		this.id = id;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cep = cep;
		this.descricao = descricao;
		this.preco = preco;
		this.tipoMoradia = tipoMoradia;
		this.locador = locador;
	}
}
