package com.tcc.moradiaestudantil.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.moradiaestudantil.dto.UsuarioDTO;
import com.tcc.moradiaestudantil.enums.Status;
import com.tcc.moradiaestudantil.enums.TipoUsuario;
import com.tcc.moradiaestudantil.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(name="usuario")
@EqualsAndHashCode(of="id")
@Getter
@Setter
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	protected Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf", unique = true)
	private String cpf;
	
	@Column(name = "data_nascimento")
	private String dataNasc;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Email
	@NotBlank
	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "senha")
	private String senha;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "tipo_usuario")
	private Integer tipoUsuario;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "role")
	private UserRole role;
	
    public Usuario(String nome, String cpf, String dataNasc, String sexo, @Email String email, String senha, String telefone, TipoUsuario tipo, Status status, UserRole role) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.sexo = sexo;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.tipoUsuario = tipo.getCodigo();
		this.status = status.getCodigo();
		this.role = role;
	}
    
    public Usuario(UsuarioDTO dto) {
    	this.id = dto.getId();
		this.nome = dto.getNome();
		this.cpf = dto.getCpf();
		this.dataNasc = dto.getDataNasc();
		this.sexo = dto.getSexo();
		this.email = dto.getEmail();
		this.senha = dto.getSenha();
		this.telefone = dto.getTelefone();
		this.tipoUsuario = dto.getTipoUsuario().getCodigo();
		this.status = dto.getStatus().getCodigo();
		this.role = dto.getRole();
    }

	@JsonIgnore
    @OneToMany(mappedBy = "acusador")
    private List<Denuncia> denunciasAcusador = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "alvo")
    private List<Denuncia> denunciasAlvo = new ArrayList<>();
    
    @JsonIgnore
	@OneToMany(mappedBy = "locador")
 	private List<Moradia> moradia;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLER_USER"));
		else return List.of(new SimpleGrantedAuthority("ROLER_USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
