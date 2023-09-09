package io.github.meucafofo.meucafofo_servico.mapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.meucafofo.meucafofo_servico.domain.entity.Locador;
import io.github.meucafofo.meucafofo_servico.dto.mapper.GenericModelMapper;
import io.github.meucafofo.meucafofo_servico.dto.request.CadastrarUsuarioDTO;

@SpringBootTest
@ActiveProfiles("test")
class GenericModelMapperTest {

	@Autowired
	private GenericModelMapper<CadastrarUsuarioDTO> mapper;

	@Test
	void deveMapearLocador() {
		// setup
		var cadastrarUsuarioDTO = new CadastrarUsuarioDTO("Moisés", "Caldas", LocalDate.of(2000, 7, 12), "M",
				"sesiom.br@gmail.com", "(61) 88888-8888",
				"$2a$12$Smm433aUrFuDD7cf1.iO6.MAzZtXaONA6aNVe/sAr305eC3OtIRWK", 1, null, "055.782.631-43");

		// act and assert
		assertDoesNotThrow(() -> System.out.println(mapper.toEntity(cadastrarUsuarioDTO, Locador.class)));
	}
}
