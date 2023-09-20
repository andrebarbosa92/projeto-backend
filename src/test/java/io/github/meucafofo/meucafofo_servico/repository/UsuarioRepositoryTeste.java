package io.github.meucafofo.meucafofo_servico.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.meucafofo.meucafofo_servico.domain.entity.Locador;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UsuarioRepositoryTeste {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@Test void cadastrarLocadorComSucesso() {
		// setup
		var locador = new Locador(null, "Moisés", "Caldas", LocalDate.of(2000, 7, 12), "M", "sesiom.br@gmail.com", "(61) 88888-8888", "$2a$12$Smm433aUrFuDD7cf1.iO6.MAzZtXaONA6aNVe/sAr305eC3OtIRWK", false, "055.782.631-43");
		
		// act
		var locadorRM = usuarioRepository.save(locador);
		
		// assert
		assertNotNull(locadorRM.getId());
	}
}
