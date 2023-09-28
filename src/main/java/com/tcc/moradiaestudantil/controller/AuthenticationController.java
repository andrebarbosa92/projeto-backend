package com.tcc.moradiaestudantil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.moradiaestudantil.config.TokenService;
import com.tcc.moradiaestudantil.domain.dto.AuthenticationDTO;
import com.tcc.moradiaestudantil.domain.dto.LoginResponseDTO;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.service.UsuarioLogadoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse servletResponse) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((Usuario) auth.getPrincipal());
		servletResponse.addHeader("Authorization", token);
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/refresh-token") 
	public ResponseEntity<?> refreshToken(HttpServletResponse servletResponse){
		Usuario user = UsuarioLogadoService.autheticated();
		var token = tokenService.generateToken(user);
		servletResponse.addHeader("Authorization", token);
		return ResponseEntity.noContent().build();
	}
	
}
