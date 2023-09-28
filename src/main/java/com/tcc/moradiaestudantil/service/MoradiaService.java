package com.tcc.moradiaestudantil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.moradiaestudantil.domain.dto.AnuncioDTO;
import com.tcc.moradiaestudantil.domain.entity.Moradia;
import com.tcc.moradiaestudantil.domain.entity.Usuario;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.exception.AuthorizationException;
import com.tcc.moradiaestudantil.exception.ObjectNotFoundException;
import com.tcc.moradiaestudantil.exception.RegisterException;
import com.tcc.moradiaestudantil.repository.MoradiaRepository;
import com.tcc.moradiaestudantil.repository.UsuarioRepository;
import com.tcc.moradiaestudantil.utils.ServiceResponse;

@Service
public class MoradiaService {

	@Autowired
	private MoradiaRepository moradiaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public ServiceResponse inserirMoradia(Moradia moradia) {
		Optional<Usuario> locador = usuarioRepository.findById(moradia.getLocador().getId());
		if(locador.get().getTipoUsuario() != TipoUsuario.toEnum(2)) throw new RegisterException("ID Locador inválido");
		moradiaRepository.saveAndFlush(moradia);
		return new ServiceResponse("Anuncio cadastrado com sucesso!", true);
	}
	
	@Transactional(readOnly = true)
	public List<Moradia> listarMoradia (){
		return moradiaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<AnuncioDTO> listarMoradiaPorLocador(Integer page, Integer linesPerPage, String orderBy, String direction){
		Usuario user = UsuarioLogadoService.autheticated();
		if(user == null ) {
			throw new AuthorizationException("Acesso Negado!");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),  orderBy);
		Optional<Usuario> locador = usuarioRepository.findById(user.getId());
		if(locador.get().getTipoUsuario() != TipoUsuario.LOCADOR) throw new ObjectNotFoundException("Nenhum anuncio encontrado!.");
		Page<Moradia> list = moradiaRepository.findByLocador(locador.get(), pageRequest);
		Page<AnuncioDTO> dto = list.map(u -> new AnuncioDTO(u));
		return dto;
	}

	public Page<AnuncioDTO> findByTipoMoradiaAndPreco(String tipo, Double preco, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),  orderBy);
		Page<Moradia> moradias = moradiaRepository.buscarTeste(tipo, preco, pageRequest);
		Page<AnuncioDTO> dto = moradias.map(u -> new AnuncioDTO(u));
		return dto;
	}
	
	
	public Page<AnuncioDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),  orderBy);
		Page<Moradia> list = moradiaRepository.findAll(pageRequest);
		Page<AnuncioDTO> dto = list.map(u -> new AnuncioDTO(u));
		return dto;
	}
}
