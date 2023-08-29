package com.tcc.moradiaestudantil.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Denuncia")
@AllArgsConstructor
@NoArgsConstructor
public class Denuncia implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="data_hora")
	private LocalDateTime horario;
    
    private String motivo;

    private String status;

    // Relacionamentos
    
    @ManyToOne
    @JoinColumn(name = "id_acusador")
    private Usuario acusador;
    
    @ManyToOne
    @JoinColumn(name = "id_alvo")
    private Usuario alvo;
}
