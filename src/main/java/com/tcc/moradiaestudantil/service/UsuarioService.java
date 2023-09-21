package com.tcc.moradiaestudantil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.dto.LoginDTO;
import com.tcc.moradiaestudantil.domain.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;
import com.tcc.moradiaestudantil.utils.LoginResponse;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> listarTodos (){
		List<Usuario> listAluno = usuarioRepository.findAll();
		
		return listAluno.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> opt = usuarioRepository.findById(id);
		Usuario usuario = (Usuario) opt.get();
		UsuarioDTO dto = new UsuarioDTO(usuario);
		
		return dto;
//		return opt.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado. ID: "+ id));
	}

//	public LoginResponse loginUsuario(LoginDTO loginDTO) {
////		String msg = "";
//		Usuario user = usuarioRepository.findByEmail(loginDTO.getEmail());
//		
//		if(user != null) {
//			String password = loginDTO.getSenha();
//			String encodePassword = user.getSenha();
//			Boolean isPwdRight = passwordEncoder.matches(password, encodePassword);
//			if(isPwdRight) {
//				Optional<Usuario> usuario = usuarioRepository.findOneByEmailAndSenha(loginDTO.getEmail(), encodePassword);
//				if(usuario.isPresent()) {
//					return new LoginResponse("Login Sucess", true);
//				} else {
//					return new LoginResponse("Login Failed", false);
//				}
//			} else {
//				return new LoginResponse("Password not match", false);
//			}
//		} else {
//			return new LoginResponse("Email not exists", false);
//		}
//	}
	
	
}
