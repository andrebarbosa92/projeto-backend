package com.tcc.moradiaestudantil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.dto.AlunoDTO;
import com.tcc.moradiaestudantil.domain.entity.Aluno;
import com.tcc.moradiaestudantil.exception.ObjectNotFoundException;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.repository.AlunoRepository;
import com.tcc.moradiaestudantil.utils.ServiceResponse;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Transactional(readOnly = true)
	public List<AlunoDTO> findAll (){
		List<Aluno> listAluno = alunoRepository.findAll();
		
		return listAluno.stream().map(u -> new AlunoDTO(u)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public AlunoDTO findById(Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);
		
		Aluno entity = opt.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado. ID: "+ id));
		
		AlunoDTO dto = new AlunoDTO(entity);
		return dto;
	}
	
	@Transactional
	public ServiceResponse insertAluno (AlunoDTO alunoDTO) {
		
		Optional<Aluno> emailVerify = alunoRepository.findByEmail(alunoDTO.getEmail());
		if(emailVerify.isPresent()) throw new RegisterException("Email já cadastrado!"); 
		
		String cpf = validCpf(alunoDTO.getCpf());
		Optional<Aluno> cpfVerify = alunoRepository.findByCpf(cpf);
		if (cpfVerify.isPresent()) throw new RegisterException("CPF já cadastrado!");
		
		Aluno aluno = new Aluno();
		aluno.setNome(alunoDTO.getNome());
		aluno.setCpf(alunoDTO.getCpf());
		aluno.setDataNasc(alunoDTO.getDataNasc());
		aluno.setSexo(alunoDTO.getSexo());
		aluno.setEmail(alunoDTO.getEmail());
		aluno.setSenha(this.passwordEncoder.encode(alunoDTO.getSenha()));
		aluno.setRole(alunoDTO.getRole());
		
		alunoRepository.save(aluno);
		
		return new ServiceResponse("Cadastro realizado com sucesso!", true);
	}
	
	public Aluno verificarAtualizarId(Long id) {
		Optional<Aluno> opt = alunoRepository.findById(id);
		return opt.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado. ID: "+ id));
	}

	@Transactional
	public void updateAluno(AlunoDTO alunoDto) {
		
		try {
			AlunoDTO dto = findById(alunoDto.getId());
			dto.setNome(alunoDto.getNome() == null ? dto.getNome() : alunoDto.getNome());
			dto.setCpf(alunoDto.getCpf() == null ? dto.getCpf() : alunoDto.getCpf());
			dto.setDataNasc(alunoDto.getDataNasc() == null ? dto.getDataNasc() : alunoDto.getDataNasc());
			dto.setSexo(alunoDto.getSexo() == null ? dto.getSexo() : alunoDto.getSexo());
			dto.setEmail(alunoDto.getEmail() == null ? dto.getEmail() : alunoDto.getEmail());
			
			Aluno entityAluno = new Aluno(dto);
			entityAluno = alunoRepository.save(entityAluno);
			
		}catch (Exception e) {
			throw new ObjectNotFoundException("Aluno não encontrado. ID: "+ alunoDto.getId());
		}
	}
	
	@Transactional
	public ServiceResponse updateSenha(Long id, String senhaAntiga, String senhaNova) {
		
		try {
			AlunoDTO dto = findById(id);
			String encodePassword = dto.getSenha();
			Boolean isPwdRight = passwordEncoder.matches(senhaAntiga, encodePassword);
			if(isPwdRight) {
				dto.setSenha(this.passwordEncoder.encode(senhaNova));
				Aluno entityAluno = new Aluno(dto);
				alunoRepository.save(entityAluno);
				return new ServiceResponse("Senha atualizada com sucesso!", true);
			} else {
				throw new RegisterException("");
			}
		} catch (RegisterException e) {
			throw new RegisterException("Senha antiga incorreta!");
		}
	}


	public void deleteById(Long id) {
		Aluno atualizarAluno = verificarAtualizarId(id);
		if(atualizarAluno != null) {
			alunoRepository.deleteById(id);
		}
	}
	
	public String validCpf(String cpf) {
		
		if (cpf.length() != 11) {
            throw new RegisterException("CPF inválido. Deve conter 11 dígitos.");
        }
		return cpf;
	}
}
