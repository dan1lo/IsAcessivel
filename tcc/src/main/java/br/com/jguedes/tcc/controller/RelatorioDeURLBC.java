package br.com.jguedes.tcc.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import ases.RelatorioDaUrl;
import br.com.jguedes.tcc.model.repository.RelatorioDeURLDAO;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

@Controller
public class RelatorioDeURLBC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 951804333348230615L;

	private RelatorioDeURLDAO relatorioDeURLDAO;

	@PostConstruct
	private void init() {

		relatorioDeURLDAO = new RelatorioDeURLDAO();

	}

	public RelatorioDaUrl getRelatorioDeURL(ContextoDeAvaliacao contexto) {

		return relatorioDeURLDAO.getRelatorioDeURL(contexto);

	}

}
