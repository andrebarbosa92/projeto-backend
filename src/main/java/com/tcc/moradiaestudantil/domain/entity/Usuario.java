package com.tcc.moradiaestudantil.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.moradiaestudantil.domain.dto.UsuarioDTO;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name="usuario")
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	protected Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "data_nascimento")
	private String dataNasc;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Email
	@Column(name = "email")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario.toEnum(tipoUsuario);
	}

	public void setTipoUsuario(TipoUsuario tipo) {
		this.tipoUsuario = tipo.getCodigo();
	}
	
	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCodigo();
	}
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

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
