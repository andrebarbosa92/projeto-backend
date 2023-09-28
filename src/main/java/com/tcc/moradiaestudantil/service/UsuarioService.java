package com.tcc.moradiaestudantil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.enums.UserRole;
import com.tcc.moradiaestudantil.exception.AuthorizationException;
import com.tcc.moradiaestudantil.exception.ObjectNotFoundException;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;
import com.tcc.moradiaestudantil.utils.ServiceResponse;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll (){
		List<Usuario> listUsuario = usuarioRepository.findAll();
		
		return listUsuario.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		
		Usuario user = UsuarioLogadoService.autheticated();
		if(user == null || user.getRole() != UserRole.ADMIN && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		Optional<Usuario> opt = usuarioRepository.findById(id);
		if(opt.isEmpty()) new ObjectNotFoundException("Usuario não encontrado. ID: "+ id);
		UsuarioDTO dto = new UsuarioDTO(opt.get());
		return dto;
	}
	
	@Transactional
	public ServiceResponse insertUsuario (UsuarioDTO usuarioDTO) {
		
		if(usuarioRepository.findByEmail(usuarioDTO.getEmail()) != null) throw new RegisterException("Email já cadastrado!"); 
		
		String cpf = validCpf(usuarioDTO.getCpf());
		Optional<Usuario> cpfVerify = usuarioRepository.findByCpf(cpf);
		if (cpfVerify.isPresent()) throw new RegisterException("CPF já cadastrado!");
		
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setCpf(usuarioDTO.getCpf());
		usuario.setDataNasc(usuarioDTO.getDataNasc());
		usuario.setSexo(usuarioDTO.getSexo());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setSenha(this.passwordEncoder.encode(usuarioDTO.getSenha()));
		usuario.setTelefone(usuarioDTO.getTelefone());
		usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
		usuario.setStatus(usuarioDTO.getStatus());
		usuario.setRole(usuarioDTO.getRole());
		
		usuarioRepository.save(usuario);
		
		return new ServiceResponse("Cadastro realizado com sucesso!", true);
	}
	
	public Usuario verificarAtualizarId(Long id) {
		Optional<Usuario> opt = usuarioRepository.findById(id);
		return opt.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado. ID: "+ id));
	}

	@Transactional
	public void updateUsuario(Long id, UsuarioDTO usuarioDto) {
		
		try {
			UsuarioDTO newDto = findById(id);
			updateData(newDto, usuarioDto);
			Usuario entityUsuario = new Usuario(newDto);
			entityUsuario = usuarioRepository.save(entityUsuario);
			
		}catch (Exception e) {
			throw new ObjectNotFoundException("Usuario não encontrado. ID: "+ usuarioDto.getId());
		}
	}
	
	@Transactional
	public ServiceResponse updateSenha(Long id, String senhaAntiga, String senhaNova) {
		
		try {
			UsuarioDTO dto = findById(id);
			String encodePassword = dto.getSenha();
			Boolean isPwdRight = passwordEncoder.matches(senhaAntiga, encodePassword);
			if(isPwdRight) {
				dto.setSenha(this.passwordEncoder.encode(senhaNova));
				Usuario entityUsuario = new Usuario(dto);
				usuarioRepository.save(entityUsuario);
				return new ServiceResponse("Senha atualizada com sucesso!", true);
			} else {
				throw new RegisterException("");
			}
		} catch (RegisterException e) {
			throw new RegisterException("Senha antiga incorreta!");
		}
	}


	public void deleteById(Long id) {
		Usuario atualizarUsuario = verificarAtualizarId(id);
		if(atualizarUsuario != null) {
			usuarioRepository.deleteById(id);
		}
	}
	
	public String validCpf(String cpf) {
		
		if (cpf.length() != 11) {
            throw new RegisterException("CPF inválido. Deve conter 11 dígitos.");
        }
		return cpf;
	}


	private void updateData(UsuarioDTO newDto, UsuarioDTO usuarioDTO) {
		newDto.setNome(usuarioDTO.getNome() == null ? newDto.getNome() : usuarioDTO.getNome());
		newDto.setCpf(usuarioDTO.getCpf()== null ? newDto.getCpf() : usuarioDTO.getCpf());
		newDto.setDataNasc(usuarioDTO.getDataNasc()== null ? newDto.getDataNasc() : usuarioDTO.getDataNasc());
		newDto.setSexo(usuarioDTO.getSexo()== null ? newDto.getSexo() : usuarioDTO.getSexo());
		newDto.setEmail(usuarioDTO.getEmail()== null ? newDto.getEmail() : usuarioDTO.getEmail());
		newDto.setSenha(usuarioDTO.getSenha()== null ? newDto.getSenha() : this.passwordEncoder.encode(usuarioDTO.getSenha()));
		newDto.setTelefone(usuarioDTO.getTelefone()== null ? newDto.getTelefone() : usuarioDTO.getTelefone());
	}
	
}
