package br.com.jguedes.tcc.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import br.com.jguedes.tcc.model.User;
import br.com.jguedes.tcc.model.relatorioresumido.ResumoDeAvaliacao;
import br.com.jguedes.tcc.model.repository.ResumoDeAvaliacaoDAO;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

@Controller
public class ResumoDeAvaliacaoBC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6195687053870427999L;

	private ResumoDeAvaliacaoDAO resumoDeAvaliacaoDAO;

	public ResumoDeAvaliacaoBC() {
		this.resumoDeAvaliacaoDAO = new ResumoDeAvaliacaoDAO();
	}

	public List<ResumoDeAvaliacao> getList() {

		return getList(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getContexto());

	}

	public List<ResumoDeAvaliacao> getList(ContextoDeAvaliacao contexto) {

		List<ResumoDeAvaliacao> lista = null;

		while ((lista = resumoDeAvaliacaoDAO.findAll(contexto)) == null) {

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return lista;
	}

}
