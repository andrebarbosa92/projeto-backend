package com.tcc.moradiaestudantil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.dto.LocadorDTO;
import com.tcc.moradiaestudantil.domain.entity.Locador;
import com.tcc.moradiaestudantil.exception.ObjectNotFoundException;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.repository.LocadorRepository;
import com.tcc.moradiaestudantil.utils.ServiceResponse;

@Service
public class LocadorService {

	@Autowired
	private LocadorRepository locadorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public List<LocadorDTO> findAll(){
		List<Locador> listarLocador = locadorRepository.findAll();
		return listarLocador.stream().map(u -> new LocadorDTO(u)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public LocadorDTO findById(Long id) {
		Optional<Locador> opt = locadorRepository.findById(id);
		
		Locador entity = opt.orElseThrow(() -> new ObjectNotFoundException("Locador não encontrado. ID: "+ id));
		
		LocadorDTO dto = new LocadorDTO(entity);
		return dto;
	}
	
	@Transactional
	public ServiceResponse insertLocador (LocadorDTO locadorDTO) {
		
		Optional<Locador> emailVerify = locadorRepository.findByEmail(locadorDTO.getEmail());
		if(emailVerify.isPresent()) throw new RegisterException("Email já cadastrado!"); 
		
		Optional<Locador> cpfVerify = locadorRepository.findByCgc(locadorDTO.getCgc());
		if (cpfVerify.isPresent()) throw new RegisterException("CPF já cadastrado!");
		
		Locador locador = new Locador();
		locador.setNome(locadorDTO.getNome());
		locador.setCgc(locadorDTO.getCgc());
		locador.setDataNasc(locadorDTO.getDataNasc());
		locador.setSexo(locadorDTO.getSexo());
		locador.setEmail(locadorDTO.getEmail());
		locador.setSenha(this.passwordEncoder.encode(locadorDTO.getSenha()));
		locador.setRole(locadorDTO.getRole());
		
		locadorRepository.save(locador);
		
		return new ServiceResponse("Cadastro realizado com sucesso!", true);
	}
	
	public Locador verificarAtualizarId(Long id) {
		Optional<Locador> opt = locadorRepository.findById(id);
		return opt.orElseThrow(() -> new ObjectNotFoundException("Locador não encontrado. ID: "+ id));
	}
	
	@Transactional
	public void updateLocador(LocadorDTO locadorDto) {
		try {
			LocadorDTO dto = findById(locadorDto.getId());
			dto.setNome(locadorDto.getNome() == null ? dto.getNome() : locadorDto.getNome());
			dto.setDataNasc(locadorDto.getDataNasc() == null ? dto.getDataNasc() : locadorDto.getDataNasc());
			dto.setCgc(locadorDto.getCgc() == null ? dto.getCgc() : locadorDto.getCgc());
			dto.setSexo(locadorDto.getSexo() == null ? dto.getSexo() : locadorDto.getSexo());
			dto.setEmail(locadorDto.getEmail() == null ? dto.getEmail() : locadorDto.getEmail());
			if(!locadorDto.getSenha().equals(dto.getSenha())) {
				dto.setSenha(locadorDto.getSenha() == null ? dto.getSenha() : this.passwordEncoder.encode(locadorDto.getSenha()));
			}
			Locador entity = new Locador(dto);
			locadorRepository.save(entity);
		}catch (Exception e) {
			new ObjectNotFoundException("Locador não encontrado. ID: "+ locadorDto.getId());
		}
	}
	
	@Transactional
	public ServiceResponse updateSenha(Long id, String senhaAntiga, String senhaNova) {
		
		try {
			LocadorDTO dto = findById(id);
			String encodePassword = dto.getSenha();
			Boolean isPwdRight = passwordEncoder.matches(senhaAntiga, encodePassword);
			if(isPwdRight) {
				dto.setSenha(this.passwordEncoder.encode(senhaNova));
				Locador entityLocador = new Locador(dto);
				locadorRepository.save(entityLocador);
				return new ServiceResponse("Senha atualizada com sucesso!", true);
			} else {
				return new ServiceResponse("Senha antiga incorreta!", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteById(Long id) {
		Locador deletearLocador = verificarAtualizarId(id);
		if(deletearLocador != null) {
			locadorRepository.deleteById(id);
		}
	}
}
