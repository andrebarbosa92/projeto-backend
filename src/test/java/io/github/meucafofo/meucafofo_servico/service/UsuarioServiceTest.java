package io.github.meucafofo.meucafofo_servico.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.meucafofo.meucafofo_servico.domain.entity.Usuario;
import io.github.meucafofo.meucafofo_servico.dto.mapper.GenericModelMapper;
import io.github.meucafofo.meucafofo_servico.dto.request.CadastrarUsuarioDTO;
import io.github.meucafofo.meucafofo_servico.repository.UsuarioRepository;

@SpringBootTest
@ActiveProfiles("test")
class UsuarioServiceTest {
	@Autowired
	private GenericModelMapper<CadastrarUsuarioDTO> mapper;

	@Mock
	private UsuarioRepository usuarioRepository;

	private UsuarioService usuarioService;

	private Usuario usuario;

	@BeforeEach
	public void loadObjects() {
		this.usuarioService = new UsuarioService(mapper, usuarioRepository);
		this.usuario = new Usuario(1L, "Moisés", "Caldas", LocalDate.of(2000, 7, 12), "M", "sesiom.br@gmail.com",
				"(61) 88888-8888", "$2a$12$Smm433aUrFuDD7cf1.iO6.MAzZtXaONA6aNVe/sAr305eC3OtIRWK", false);
	}

	@Test
	void cadastrarUsuarioLocador() {
		// setup
		var dto = new CadastrarUsuarioDTO("Moisés", "Caldas", LocalDate.of(2000, 7, 12), "M", "sesiom.br@gmail.com",
				"(61) 88888-8888", "$2a$12$Smm433aUrFuDD7cf1.iO6.MAzZtXaONA6aNVe/sAr305eC3OtIRWK", 1, null,
				"055.782.631-43");
		Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);
		// act
		var result = usuarioService.cadastrarUsuario(dto);

		// assert
		assertEquals(usuario, result);
	}
}
