package br.com.jguedes.tcc.view.resumodeavaliacaolistmb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import br.com.jguedes.tcc.model.User;
import br.com.jguedes.tcc.model.criterioavaliacao.PadraoAcessibilidade;
import br.com.jguedes.tcc.model.criterioavaliacao.Profundidade;
import br.com.jguedes.tcc.model.relatorioresumido.ResumoDeAvaliacao;
import br.com.jguedes.tcc.support.Fachada;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named("resAvals")
public class ResumoDeAvaliacaoListMB {

	@Autowired
	private Fachada fachada;

	private User user;

	@PostConstruct
	public void init() {

		setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

	}

	private void setUser(User user) {

		this.user = user;

	}

	private List<ResumoDeAvaliacao> listResumoDeAvaliacao;

	public List<ResumoDeAvaliacao> getListResumoDeAvaliacao() {

		if (listResumoDeAvaliacao == null) {

			setListResumoDeAvaliacao(fachada.getListResumoDeAvaliacao());

		}

		return listResumoDeAvaliacao;

	}

	private void setListResumoDeAvaliacao(List<ResumoDeAvaliacao> listResumoDeAvaliacao) {
		this.listResumoDeAvaliacao = listResumoDeAvaliacao;
	}

	public String visualizar(String linkEvalCodeRelAtual) {

		this.user.getContexto().setLinkEvalCodeRelAtual(linkEvalCodeRelAtual);

		return "exibirrelatoriourl";

	}

	public String atualizar() {
		return "atualizar";
	}

	public PadraoAcessibilidade getPadraoAcessibilidade() {

		return this.user.getContexto().getCriterio().getPadraoAcessibilidade();

	}

	public Profundidade getProfundidade() {

		return this.user.getContexto().getCriterio().getProfundidade();

	}

	public String getUrl() {

		return this.user.getContexto().getCriterio().getUrl();

	}
}
