package br.com.jguedes.tcc.view;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

import br.com.jguedes.tcc.view.avaliadoracessibilidademb.AvaliadorAcessibilidadeMB;
import br.com.jguedes.tcc.view.relatoriodeurlmb.RelatorioDeURLMB;
import br.com.jguedes.tcc.view.resumodeavaliacaolistmb.ResumoDeAvaliacaoListMB;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named("frontEndMB")
public class FrontEndMB {

	@Autowired
	private AvaliadorAcessibilidadeMB avAcess;

	@Autowired
	private ResumoDeAvaliacaoListMB resAvals;

	private RelatorioDeURLMB relUrl;

	@PostConstruct
	private void init() {
		System.out.println("FrontEnd.init");
	}

	public AvaliadorAcessibilidadeMB getAvAcess() {
		return avAcess;
	}

	public ResumoDeAvaliacaoListMB getResAvals() {
		return resAvals;
	}

	public RelatorioDeURLMB getRelUrl() {
		if (relUrl == null) {
			// relUrl = new RelatorioDeURLMB(avAcess.getCriterio(),
			// resAvals.getResumoDeAvaliacaoAtual());
		}
		return relUrl;
	}

}
