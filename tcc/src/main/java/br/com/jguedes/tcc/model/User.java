package br.com.jguedes.tcc.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

@Entity
@Table(name = "user")
public class User extends EntidadeBase<Long> implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private String nome;

	private String login;

	private String password;

	private String role;

	@Transient
	private org.springframework.security.core.userdetails.User user;

	@Transient
	private ContextoDeAvaliacao contexto;

	public User() {
	}

	public User(String nome, String login, String password, String role) {
		super();
		this.setNome(nome);
		this.setLogin(login);
		this.setPassword(password);
		this.setRole(role);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getNome();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void eraseCredentials() {
		this.user.eraseCredentials();

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.user.getAuthorities();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.isEnabled();
	}

	public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {

		if (this.user == null) {

			this.user = new org.springframework.security.core.userdetails.User(getNome(), password, grantedAuthorities);

		}

	}

	public String getUserContextoID() {

		return this.getNome() + "_id_" + super.getId();

	}

	public ContextoDeAvaliacao getContexto() {

		if (contexto == null) {

			contexto = new ContextoDeAvaliacao(this.getUserContextoID());

		}

		return contexto;

	}

}
