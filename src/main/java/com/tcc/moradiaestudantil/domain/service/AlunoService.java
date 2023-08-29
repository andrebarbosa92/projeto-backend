package com.tcc.moradiaestudantil.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.entity.Aluno;
import com.tcc.moradiaestudantil.domain.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	public Aluno cadastrarAluno (Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	
	@Transactional(readOnly = true)
	public List<Aluno> listarTodos (){
		List<Aluno> listAluno = alunoRepository.findAll();
		
		return listAluno;
//		return listAluno.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}
	
	public Aluno buscarId(Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);
		Aluno aluno = (Aluno) opt.get();
//		UsuarioDTO dto = new UsuarioDTO(usuario);
		
		return aluno;
//		return opt.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado. ID: "+ id));
	}
}
