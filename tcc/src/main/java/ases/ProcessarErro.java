/*******************************************************************************
 * Copyright 2005, 2006, 2007, 2008 Acessibilidade Brasil
 * Este arquivo &eacute; parte do programa ASES - Avaliador e Simulador para AcessibilidadE de S&iacut;tios
 * O ASES &eacute; um software livre; voc&ecirc; pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen&ccedil;a P&uacute;blica Geral GNU como
 * publicada pela Funda&ccedil;&atilde;o do Software Livre (FSF); na vers&ccedil;&atilde;o 2 da Licen&ccedil;a, ou (na sua opni&atilde;o) qualquer vers&ccedil;&atilde;o posterior.
 * Este programa &eacute; distribuido na esperan&ccedil;a que possa ser  util, mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUA&Ccedil;&Atilde;O a qualquer  MERCADO ou APLICA&Ccedil;&Atilde;O EM PARTICULAR. Veja a Licen&ccedil;a P&uacute;blica Geral GNU para maiores detalhes.
 * Voc&ecirc; deve ter recebido uma c&oacute;pia da Licen&ccedil;a P&uacute;blica Geral GNU, sob o t&iacute;tulo "LICENCA.txt", junto com este programa, se n&atilde;o, escreva para a Funda&ccedil;&atilde;o do Software Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *******************************************************************************/

/*******************************************************************************
 * Copyright (c) 2005, 2006, 2007 Acessibilidade Brasil.
 * 
 * This file is part of ASES.
 *
 * ASES is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * A copy of the license can be found at 
 * http://www.gnu.org/copyleft/lesser.txt.
 *******************************************************************************/

package ases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import br.com.jguedes.tcc.gerenciadorrelatorioarquivo.FachadaArquivador;
import br.com.jguedes.tcc.model.criterioavaliacao.PadraoAcessibilidade;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

/**
 * Classe que ir&atilde;o gerar os erros da p&aacute;gina. Roda pelo menos uma
 * vez para cada p&aacute;gina
 */
public class ProcessarErro {

	private InterfNucleos nucleox;

	/**
	 * Contagem dos Erros por Prioridade
	 */
	private int pri1 = 0, pri2 = 0, pri3 = 0;

	/**
	 * Contagem dos Avisos por Prioridade
	 */
	private int ap1 = 0, ap2 = 0, ap3 = 0;

	/**
	 * Definicao de quais prioridades mostrar no relatorio
	 */
	private boolean mostraP1 = true;

	private boolean mostraP2 = true;

	private boolean mostraP3 = true;

	/**
	 * Cole&ccedil;&atilde;o de Erros de prioridade 1.
	 */
	private HashSet<PontoVerificacao> ptVerif1 = new HashSet<PontoVerificacao>();

	/**
	 * Cole&ccedil;&atilde;o de Erros de prioridade 2.
	 */
	private HashSet<PontoVerificacao> ptVerif2 = new HashSet<PontoVerificacao>();

	/**
	 * Cole&ccedil;&atilde;o de Erros de prioridade 3.
	 */
	private HashSet<PontoVerificacao> ptVerif3 = new HashSet<PontoVerificacao>();

	/**
	 * Cole&ccedil;&atilde;o de Avisos de prioridade 1.
	 */
	private HashSet<PontoVerificacao> avisosP1 = new HashSet<PontoVerificacao>();

	/**
	 * Cole&ccedil;&atilde;o de Avisos de prioridade 2.
	 */
	private HashSet<PontoVerificacao> avisosP2 = new HashSet<PontoVerificacao>();

	/**
	 * Cole&ccedil;&atilde;o de Avisos de prioridade 3.
	 */
	private HashSet<PontoVerificacao> avisosP3 = new HashSet<PontoVerificacao>();

	/**
	 * Conjunto de Pontos de Verifica&ccedil;&atilde; que ser&atilde;o
	 * utilizados para avalia&ccedil;&atilde;o dos erros no conte&uacute;do das
	 * p&aacute;ginas.
	 */
	private HashMap<Integer, PontoVerificacao> pontosVerificacao;

	// private BancoSite bancoSite;

	private RelatorioDaUrl relatorio;

	private ContextoDeAvaliacao contexto;

	public ProcessarErro() {
	}

	/**
	 * Processa os erros da urlString passada como par&acirc;metro
	 * 
	 * @param r
	 * @param contexto
	 */
	public ProcessarErro(RelatorioDaUrl r, ContextoDeAvaliacao contexto) {

		this.relatorio = r;

		this.contexto = contexto;

		pontosVerificacao = PontosDeVerificacaoFactory
				.getPontosDeVerificacao(relatorio.getPadraoAcessibilidade().getValue());

		inicializaMostraPrioridades();

	}

	public void parseWAI() {

		// Zera as coisas
		inicializar();

		try {

			// Marcacao retorna os erros
			separarPrioridade(marcacao());

		} catch (Exception e) {

			e.printStackTrace();

			return;

		}

		relatorio.setErrosPrioridade1(pri1);

		relatorio.setErrosPrioridade2(pri2);

		relatorio.setErrosPrioridade3(pri3);

		relatorio.setAvisosPrioridade1(ap1);

		relatorio.setAvisosPrioridade2(ap2);

		relatorio.setAvisosPrioridade3(ap3);

		relatorio.setListaErrosP1(ptVerif1);

		relatorio.setListaErrosP2(ptVerif2);

		relatorio.setListaErrosP3(ptVerif3);

		relatorio.setListaAvisosP1(avisosP1);

		relatorio.setListaAvisosP2(avisosP2);

		relatorio.setListaAvisosP3(avisosP3);

		relatorio.setMostraP1(mostraP1);

		relatorio.setMostraP2(mostraP2);

		relatorio.setMostraP3(mostraP3);

		if (relatorio.getProfundidade().getValue() > 5) {

			GravadorDeTemporarios.gravarTemp(relatorio, this.contexto);

		}

		if (relatorio.getProfundidade().getValue() <= 5) {

			relatorio.gravarRelatorio(contexto);

			// relatorio.geraArquivoRelatorioEmXml2(this.contexto);

		}

		if (relatorio.getProfundidade().getValue() > 5) {

			// this.gerente.getContexto().getRelatoriosDeThreadParaGerente().add(relatorio);

		}

		ResumoDoRelatorio.addLine(this.contexto, relatorio.getLinkEvalCode(), relatorio.getUrl(),
				relatorio.getErrosPrioridade1(), relatorio.getErrosPrioridade2(), relatorio.getErrosPrioridade3(),
				relatorio.getAvisosPrioridade1(), relatorio.getAvisosPrioridade2(), relatorio.getAvisosPrioridade3());

		relatorio.setListaErrosP1(null);

		relatorio.setListaErrosP2(null);

		relatorio.setListaErrosP3(null);

		relatorio.setListaAvisosP1(null);

		relatorio.setListaAvisosP2(null);

		relatorio.setListaAvisosP3(null);

		relatorio = null;

	}

	private void inicializaMostraPrioridades() {

		mostraP1 = this.contexto.getCriterio().isPrioridade1();

		mostraP2 = this.contexto.getCriterio().isPrioridade2();

		mostraP3 = this.contexto.getCriterio().isPrioridade3();

	}

	/**
	 * M&eacute;todo utilizado para zerar os contadores.
	 */
	private void inicializar() {

		this.pri1 = 0;

		this.pri2 = 0;

		this.pri3 = 0;

		this.ptVerif1 = new HashSet<PontoVerificacao>();

		this.ptVerif2 = new HashSet<PontoVerificacao>();

		this.ptVerif3 = new HashSet<PontoVerificacao>();

		this.avisosP1 = new HashSet<PontoVerificacao>();

		this.avisosP2 = new HashSet<PontoVerificacao>();

		this.avisosP3 = new HashSet<PontoVerificacao>();

	}

	/**
	 * Separa as prioridades do arquivo enviado
	 * 
	 * @param po
	 */
	private void separarPrioridade(ArrayList<PontoVerificacao> po) {

		for (PontoVerificacao conjErros : po) {

			int prioridade = conjErros.getPrioridade();

			char exigencia = conjErros.getExigencia();

			switch (prioridade) {

			case 1: {

				switch (exigencia) {

				case 'e': // ERRO
					ptVerif1.add(conjErros);
					break;

				default: // AVISO OU GENERICO
					avisosP1.add(conjErros);
					break;
				}

			}
				break;

			case 2: {

				switch (exigencia) {

				case 'e': // ERRO
					ptVerif2.add(conjErros);
					break;

				default: // AVISO OU GENERICO
					avisosP2.add(conjErros);
					break;
				}

			}
				break;

			case 3: {

				switch (exigencia) {

				case 'e': // ERRO
					ptVerif3.add(conjErros);
					break;

				default: // AVISO OU GENERICO
					avisosP3.add(conjErros);
					break;

				}

			}
				break;

			default:
				break;

			}

		}

	}

	/**
	 * Principal m&eacute;todo para a avalia&ccedil;&atilde;o do sistema, ele
	 * apresenta os erros que o sistema advem M&eacute;todo que retorna uma
	 * instancia de ArrayList <PontoVerificacao>
	 * 
	 * para cada ponto de verifica&ccedil;&atilde;o ele agrupa todos as
	 * ocorrencias de erros neste mesmo ponto adicionando n linhas onde ocorre
	 * este erro
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws ExceptionMariano
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<PontoVerificacao> marcacao() throws ClassNotFoundException, SQLException, Exception {

		ArrayList<PontoVerificacao> erros = new ArrayList<PontoVerificacao>();

		String nucleo = "Antigo";

		nucleo = "Estruturado";

		ArrayList<Object> validados = new ArrayList<Object>();

		validados = (ArrayList<Object>) escolheNucleo(nucleo);

		PontoVerificacao pv = null;

		ArmazenaErroOuAviso erroOuAviso = null;

		for (Object erro : validados) {

			char flagErroOuAviso = ' ';

			if (nucleo.equals("Antigo")) {

				pv = new PontoVerificacao(pontosVerificacao.get(

						((ArmazenaErroOuAvisoAntigo) erro).getPv3()

				)

				);

			} else {

				// PARA QUALQUER OUTRO NUCLEO

				erroOuAviso = (ArmazenaErroOuAviso) erro;

				String codigoRegra = erroOuAviso.getIdTextoRegra();

				String texto = nucleox.getRegras().getTextoRegra(codigoRegra);

				int idRegra = 0;

				int prioridade = nucleox.getRegras().getPrioridadeRegra(codigoRegra);

				ArrayList<Integer> nlinhas = new ArrayList<Integer>();

				nlinhas.add(erroOuAviso.getLinha());

				if (((ArmazenaErroOuAviso) erro).isAviso()) {

					flagErroOuAviso = 'a';

				} else {

					flagErroOuAviso = 'e';

					// evitar guardar os avisos por uma questao de demora
					// bancoSite.insertTabelaErro(portalIdBanco, pagIdBanco,
					// codigoRegra, prioridade, tagTratada, linha,
					// coluna, tagName, flagErroOuAviso);

				}

				pv = new PontoVerificacao(texto, idRegra, prioridade, codigoRegra, nlinhas,
						String.valueOf(flagErroOuAviso));

				pv.setWcagEmag(nucleox.getRegras().getCodWcagEmag());

			}

			if (pv != null) {

				int exigencia = pv.getExigencia();

				int prioridade = pv.getPrioridade();

				switch (exigencia) {

				case 'e':

					switch (prioridade) {

					case 1:

						pri1++;

						break;

					case 2:

						pri2++;

						break;

					case 3:

						pri3++;

						break;

					default:

						new Throwable("Erro na contagem de prioridades");

					}

					break;

				case 'a':

					switch (prioridade) {

					case 1:

						ap1++;

						break;

					case 2:

						ap2++;

						break;

					case 3:

						ap3++;

						break;

					default:

						new Throwable("Erro na contagem de prioridades");

					}

					break;

				}

				int linha, coluna, tagLen;

				String umaTagInteira = "";

				if (nucleo.equals("Antigo")) {

					linha = ((ArmazenaErroOuAvisoAntigo) erro).getPosicao().getLinha() + 1;

					coluna = 0;

					tagLen = 0;

				} else {

					linha = erroOuAviso.getLinha();

					coluna = erroOuAviso.getColuna();

					tagLen = erroOuAviso.getTagCompleta().length();

					umaTagInteira = erroOuAviso.getTagCompleta();

				}

				/*
				 * Agrupa os pontos
				 */
				if (erros.contains(pv)) {

					/*
					 * o ponto j� existe, agrupa a ocorrencia nele
					 */
					erros.get(erros.indexOf(pv)).getLinhas().add(linha);

					erros.get(erros.indexOf(pv)).getColunas().add(coluna);

					erros.get(erros.indexOf(pv)).getTagLength().add(tagLen);

					// ver se n�o ocupa muita memoria
					// erros.get(erros.indexOf(pv)).getTagInteira().add(umaTagInteira);

					if (flagErroOuAviso == 'e') {

						erros.get(erros.indexOf(pv)).getAvisoOuErro().add("e");

					} else {

						erros.get(erros.indexOf(pv)).getAvisoOuErro().add("a");

					}

				} else {

					/*
					 * O ponto n�o existe, criamos um novo
					 */
					ArrayList<Integer> linhas = new ArrayList<Integer>();

					linhas.add(linha);

					ArrayList<Integer> colunas = new ArrayList<Integer>();

					colunas.add(linha);

					ArrayList<Integer> tagLengths = new ArrayList<Integer>();

					tagLengths.add(linha);

					// ver se n�o ocupa muita memoria
					ArrayList<String> tagInteira = new ArrayList<String>();

					tagInteira.add(umaTagInteira);

					ArrayList<String> avOuErr = new ArrayList<String>();

					if (flagErroOuAviso == 'e') {

						avOuErr.add("e");

					} else {

						avOuErr.add("a");

					}

					pv.setLinhas(linhas);

					pv.setColunas(colunas);

					pv.setTagLength(tagLengths);

					pv.setAvisoOuErro(avOuErr);

					// pv.setTagInteira(tagInteira);

					erros.add(pv);

				}

			}

		}

		return erros;

	}

	private Object escolheNucleo(String nucleo) throws ClassNotFoundException, SQLException, Exception {

		nucleox = MethodFactNucleos.mFNucleos(nucleo);

		if (nucleo.equals("Antigo")) {

			return nucleox.getValidados(relatorio, this.contexto);

		} else {

			nucleox.setCodHTML(
					FachadaArquivador.recuperarArquivoConteudoHTML(contexto, relatorio.getLinkEvalCode()).toString());

			if (this.contexto.getCriterio().getPadraoAcessibilidade() == PadraoAcessibilidade.WCAG) {

				nucleox.setWCAGEMAG(InterfNucleos.WCAG);

			} else {

				nucleox.setWCAGEMAG(InterfNucleos.EMAG);

			}

			nucleox.avalia();

			/*
			 * precisa atender o preenchimento da interface validado e este
			 * estar num array
			 */
			return nucleox.getErroOuAviso();

		}

	}

}