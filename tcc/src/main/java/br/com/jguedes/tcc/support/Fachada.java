package br.com.jguedes.tcc.support;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ases.RelatorioDaUrl;
import br.com.jguedes.tcc.controller.AvaliadorAcessibilidadeBC;
import br.com.jguedes.tcc.controller.RelatorioDeURLBC;
import br.com.jguedes.tcc.controller.ResumoDeAvaliacaoBC;
import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeException;
import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeExceptionPrioridadeInDefinida;
import br.com.jguedes.tcc.exececoes.AvaliadorAcessibilidadeExceptionUrlVazia;
import br.com.jguedes.tcc.model.relatorioresumido.ResumoDeAvaliacao;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

@Component
public class Fachada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8887009371827162949L;

	@Autowired
	private AvaliadorAcessibilidadeBC avaliadorAcessibilidadeBC;

	@Autowired
	private ResumoDeAvaliacaoBC resumoDeAvaliacaoBC;

	@Autowired
	private RelatorioDeURLBC relatorioDeURLBC;

	////////////////////////////////////////////
	// Fachada para AvaliadorAcessibilidadeBC
	////////////////////////////////////////////

	public boolean avaliar() throws AvaliadorAcessibilidadeExceptionUrlVazia, IOException,
			AvaliadorAcessibilidadeExceptionPrioridadeInDefinida, AvaliadorAcessibilidadeException {

		return avaliadorAcessibilidadeBC.avaliar();

	}

	////////////////////////////////////////////
	// Fachada para ResumoDeAvaliacaoBC
	////////////////////////////////////////////

	public List<ResumoDeAvaliacao> getListResumoDeAvaliacao() {
		return resumoDeAvaliacaoBC.getList();
	}

	public List<ResumoDeAvaliacao> getListResumoDeAvaliacao(ContextoDeAvaliacao contexto) {
		return resumoDeAvaliacaoBC.getList(contexto);
	}

	////////////////////////////////////////////
	// Fachada para RelatorioDeURLBC
	////////////////////////////////////////////

	public RelatorioDaUrl getRelatorioDeURL(ContextoDeAvaliacao contexto) {
		return relatorioDeURLBC.getRelatorioDeURL(contexto);
	}

}
