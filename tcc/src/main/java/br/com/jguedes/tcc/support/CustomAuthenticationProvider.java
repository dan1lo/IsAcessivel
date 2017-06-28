package br.com.jguedes.tcc.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.jguedes.tcc.model.User;
import br.com.jguedes.tcc.util.UsersLogados;

@Named
public class CustomAuthenticationProvider implements AuthenticationProvider {

	public CustomAuthenticationProvider() {
		super();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String nome = authentication.getName().trim().replace(" ", "");

		// String password = authentication.getCredentials().toString();

		String password = "";

		// User user = this.userDAO.findByLoginAndPassword(nome, password);

		if (nome != null && !nome.isEmpty()) {

			User user = new User(nome, nome, password, "ROLE_USER");

			user.setId(new Date().getTime());

			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

			grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

			user.setGrantedAuthorities(grantedAuthorities);

			UserDetails userDetails = user;

			UsersLogados.add(user);

			return new UsernamePasswordAuthenticationToken(userDetails, password, grantedAuthorities);

		}

		return null;

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);

	}

}
