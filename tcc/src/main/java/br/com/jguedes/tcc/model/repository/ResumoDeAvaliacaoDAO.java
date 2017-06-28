package br.com.jguedes.tcc.model.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ases.ResumoDoRelatorio;
import br.com.jguedes.tcc.model.relatorioresumido.ResumoDeAvaliacao;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

public class ResumoDeAvaliacaoDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8092082634583396605L;

	private String[] resumoDasAvaliacoes;

	public List<ResumoDeAvaliacao> findAll(ContextoDeAvaliacao contexto) {

		this.resumoDasAvaliacoes = ResumoDoRelatorio.getActualPage(contexto).split("\n");

		return gerarRelatoriosResumidos(contexto);

	}

	private ArrayList<ResumoDeAvaliacao> gerarRelatoriosResumidos(ContextoDeAvaliacao contexto) {

		ArrayList<ResumoDeAvaliacao> list = null;

		int i;

		for (i = 0; i < this.resumoDasAvaliacoes.length; i++) {

			String resumoDeAvaliacao[] = getResumoDeAvaliacao(i);

			if (resumoDeAvaliacao.length == 8) {

				if (list == null) {

					list = new ArrayList<ResumoDeAvaliacao>();

				}

				list.add(new ResumoDeAvaliacao(
						new Object[] { (i + 1) + ": " + resumoDeAvaliacao[7], resumoDeAvaliacao[4], // Aviso1
								resumoDeAvaliacao[1], // Erro1
								resumoDeAvaliacao[5], // Aviso2
								resumoDeAvaliacao[2], // Erro2
								resumoDeAvaliacao[6], // Aviso3
								resumoDeAvaliacao[3], // Erro3
								resumoDeAvaliacao[0],// relatorio_Cod
				}));
			}

		}

		return list;

	}

	/**
	 * @param i
	 * @return
	 */
	private String[] getResumoDeAvaliacao(int i) {
		return this.resumoDasAvaliacoes[i].split("\t");
	}

}
