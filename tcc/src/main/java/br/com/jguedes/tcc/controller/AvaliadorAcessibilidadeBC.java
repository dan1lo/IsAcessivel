package br.com.jguedes.tcc.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeException;
import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeExceptionPrioridadeInDefinida;
import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeExceptionUrlVazia;
import br.com.jguedes.tcc.model.User;
import br.com.jguedes.tcc.model.criterioavaliacao.Profundidade;
import br.com.jguedes.tcc.util.AvaliadorDeAcessibilidade;

@Controller
public class AvaliadorAcessibilidadeBC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4811983488258655547L;

	private User user;

	/**
	 * @param contexto.getCriterio()
	 * @return
	 */
	private boolean isAvaliarNaRede() {
		return user.getContexto().getCriterio().getProfundidade() == Profundidade.PAGINA || //
				user.getContexto().getCriterio().getProfundidade() == Profundidade.NIVEL1 || //
				user.getContexto().getCriterio().getProfundidade() == Profundidade.NIVEL2 || //
				user.getContexto().getCriterio().getProfundidade() == Profundidade.NIVEL3 || //
				user.getContexto().getCriterio().getProfundidade() == Profundidade.SITE;
	}

	/**
	 * @param contexto
	 * @return
	 * @throws AvaliadorAcessibilidadeException
	 */
	private boolean executarAgoraNaRede() throws AvaliadorAcessibilidadeException {

		Thread t = new Thread(new AvaliadorDeAcessibilidade(user.getUserContextoID()));

		t.start();

		return true;

	}

	private boolean conferePrioridades() throws AvaliadorAcessibilidadeExceptionPrioridadeInDefinida {

		Boolean pri1 = user.getContexto().getCriterio().isPrioridade1();
		Boolean pri2 = user.getContexto().getCriterio().isPrioridade2();
		Boolean pri3 = user.getContexto().getCriterio().isPrioridade3();
		if (!pri1 && !pri2 && !pri3) {
			throw new AvaliadorAcessibilidadeExceptionPrioridadeInDefinida("Nenhuma prioridade definida");
		}
		return true;
	}

	private boolean confereUrl() throws AvaliadorAcessibilidadeExceptionUrlVazia, IOException {

		String urlString = user.getContexto().getCriterio().getUrl();

		if (urlString.trim().equals("")) {

			throw new AvaliadorAcessibilidadeExceptionUrlVazia("Url vazia");

		}

		if (urlString.startsWith("www")) {

			urlString = "http://" + urlString;

		}

		URL url = null;

		try {

			url = new URL(urlString);

		} catch (MalformedURLException mue) {

			throw mue;

		}

		if (url != null) {

			try {

				url.openConnection().connect();

			} catch (IOException ioe) {

				if (url.getProtocol().equals("https")) {

					// Melhorar verificacao, testar conexao se for https

				} else {

					throw ioe;

				}

			} catch (Exception e) {

				throw e;

			}

		}

		if (urlString != null && urlString.length() > 0) {

			urlString = urlString.startsWith("/") ? urlString.substring(1) : urlString;

			user.getContexto().getCriterio().setUrl(urlString);

		}

		return true;

	}

	public boolean avaliar() throws AvaliadorAcessibilidadeExceptionUrlVazia, IOException,
			AvaliadorAcessibilidadeExceptionPrioridadeInDefinida, AvaliadorAcessibilidadeException {

		user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		confereUrl();

		conferePrioridades();

		if (isAvaliarNaRede()) {

			return executarAgoraNaRede();

		}

		return false;

	}

}
