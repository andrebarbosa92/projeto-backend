package io.github.meucafofo.meucafofo_servico.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.meucafofo.meucafofo_servico.domain.entity.Locador;

@SpringBootTest
@ActiveProfiles("test")
class UsuarioRepositoryTeste {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@Test void cadastrarLocadorComSucesso() {
		// setup
		var locador = new Locador(null, "Moisés", "Caldas", LocalDate.of(2000, 7, 12), "M", "sesiom.br@gmail.com", "$2a$12$Smm433aUrFuDD7cf1.iO6.MAzZtXaONA6aNVe/sAr305eC3OtIRWK", false, "055.782.631-43");
		
		// act
		var locadorRM = usuarioRepository.save(locador);
		
		// assert
		assertNotNull(locadorRM.getId());
	}
}
