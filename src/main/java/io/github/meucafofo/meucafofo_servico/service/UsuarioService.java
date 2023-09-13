package io.github.meucafofo.meucafofo_servico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.meucafofo.meucafofo_servico.domain.entity.Aluno;
import io.github.meucafofo.meucafofo_servico.domain.entity.Locador;
import io.github.meucafofo.meucafofo_servico.domain.entity.Usuario;
import io.github.meucafofo.meucafofo_servico.dto.UsuarioDTO;
import io.github.meucafofo.meucafofo_servico.dto.mapper.GenericModelMapper;
import io.github.meucafofo.meucafofo_servico.dto.request.CadastrarUsuarioDTO;
import io.github.meucafofo.meucafofo_servico.enums.TipoUsuario;
import io.github.meucafofo.meucafofo_servico.exception.BusinessRuleException;
import io.github.meucafofo.meucafofo_servico.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

	private GenericModelMapper<CadastrarUsuarioDTO> mapper;
	private UsuarioRepository usuarioRepository;
	private PasswordEncoder encoder;
	
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> listarTodos() {
		List<Usuario> listAluno = usuarioRepository.findAll();

		return listAluno.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}

	public UsuarioDTO buscarId(Long id) {
		Optional<Usuario> opt = usuarioRepository.findById(id);
		Usuario usuario = (Usuario) opt.get();
		UsuarioDTO dto = new UsuarioDTO(usuario);

		return dto;
	}

	@Transactional
	public Usuario cadastrarUsuario(CadastrarUsuarioDTO dto) {
		var tipoUsuario = TipoUsuario.toEnum(dto.getCodigoTipoUsuario());
		dto.setSenha(encoder.encode(dto.getSenha()));
		
		switch (tipoUsuario) {
		case LOCADOR:
			return usuarioRepository.save(mapper.toEntity(dto, Locador.class));
		case ALUNO:
			return usuarioRepository.save(mapper.toEntity(dto, Aluno.class));
		default:
			throw new BusinessRuleException(
					"Não foi implementado regra de negocio para o usuario: " + tipoUsuario.getDescricao());
		}
	}
}
