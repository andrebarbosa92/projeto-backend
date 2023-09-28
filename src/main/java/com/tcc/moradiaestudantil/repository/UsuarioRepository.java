package com.tcc.moradiaestudantil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.tcc.moradiaestudantil.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


	Optional<Usuario> findOneByEmailAndSenha(String email, String encodePassword);
	
	Optional<Usuario> findByCpf(String cpf);

	UserDetails findByEmail(String email);
}
