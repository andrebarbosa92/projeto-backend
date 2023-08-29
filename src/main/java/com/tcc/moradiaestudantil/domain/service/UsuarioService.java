package com.tcc.moradiaestudantil.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.tcc.moradiaestudantil.domain.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> listarTodos (){
		List<Usuario> listAluno = usuarioRepository.findAll();
		
		return listAluno.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}
	
	public UsuarioDTO buscarId(Long id) {
		Optional<Usuario> opt = usuarioRepository.findById(id);
		Usuario usuario = (Usuario) opt.get();
		UsuarioDTO dto = new UsuarioDTO(usuario);
		
		return dto;
//		return opt.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado. ID: "+ id));
	}
}
