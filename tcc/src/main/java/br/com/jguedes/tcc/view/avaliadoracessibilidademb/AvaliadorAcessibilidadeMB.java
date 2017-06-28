package br.com.jguedes.tcc.view.avaliadoracessibilidademb;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeException;
import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeExceptionPrioridadeInDefinida;
import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeExceptionUrlVazia;
import br.com.jguedes.tcc.model.User;
import br.com.jguedes.tcc.model.criterioavaliacao.CriterioAvaliacao;
import br.com.jguedes.tcc.model.criterioavaliacao.PadraoAcessibilidade;
import br.com.jguedes.tcc.model.criterioavaliacao.Profundidade;
import br.com.jguedes.tcc.support.Fachada;
import br.com.jguedes.tcc.support.UserContexto;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;
import br.com.jguedes.tcc.util.FileName;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Named("avAcess")
public class AvaliadorAcessibilidadeMB implements Serializable {

	private static final long serialVersionUID = -7338366453105302082L;

	@Autowired
	private Fachada fachada;

	private CriterioAvaliacao criterio;

	private User user;

	private ContextoDeAvaliacao contexto;

	@PostConstruct
	private void init() {

		this.criterio = new CriterioAvaliacao();

		setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

	}

	public String avaliar() {

		this.user.getContexto().setCriterio(criterio);

		try {

			if (fachada.avaliar()) {

				return "success";

			}

		} catch (AvaliadorAcessibilidadeExceptionUrlVazia | IOException
				| AvaliadorAcessibilidadeExceptionPrioridadeInDefinida | AvaliadorAcessibilidadeException e) {

			e.printStackTrace();

			return "";

		}

		return "";

	}

	public void setCriterio(CriterioAvaliacao criterio) {
		this.criterio = criterio;
	}

	public CriterioAvaliacao getCriterio() {
		return this.criterio;
	}

	public List<PadraoAcessibilidade> listaPadroes() {
		return Arrays.asList(PadraoAcessibilidade.values());
	}

	public List<Profundidade> listaProfundidade() {

		Profundidade p[] = { Profundidade.NIVEL1, Profundidade.NIVEL2, Profundidade.NIVEL3, Profundidade.SITE,
				Profundidade.PAGINA };

		return Arrays.asList(p);

	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {

		this.user = user;

	}

	public ContextoDeAvaliacao getContexto() {
		contexto = (ContextoDeAvaliacao) UserContexto.fromXML(FileName.CONTEXTODEAVALIACAO, contexto.getFolderTemp());
		// contexto.setUser(user);
		contexto.setCriterio(
				(CriterioAvaliacao) UserContexto.fromXML(FileName.CRITERIODEAVALIACAO, contexto.getFolderTemp()));
		return contexto;
	}

}
