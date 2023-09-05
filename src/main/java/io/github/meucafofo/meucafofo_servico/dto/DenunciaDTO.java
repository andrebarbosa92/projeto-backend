package io.github.meucafofo.meucafofo_servico.dto;

import java.io.Serializable;

import io.github.meucafofo.meucafofo_servico.domain.entity.Denuncia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DenunciaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private String motivo;

    private String status;

	public DenunciaDTO(Denuncia d) {
		this.id = d.getId();
		this.motivo = d.getMotivo();
		this.status = d.getStatus();
	}
    
    
}
