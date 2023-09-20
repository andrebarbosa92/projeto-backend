package io.github.meucafofo.meucafofo_servico.dto.request;

import java.time.LocalDate;

import io.github.meucafofo.meucafofo_servico.interfaces.ConstraintDTOInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CadastrarUsuarioDTO implements ConstraintDTOInterface {
	private String nome;
	private String sobrenome;
	private LocalDate dataNasc;
	private String sexo;
	private String email;
	private String telefone;
	private String senha;
	private Integer codigoTipoUsuario;
	private String cpf;
	private String cgc;
}
