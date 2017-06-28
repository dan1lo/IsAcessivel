package br.com.jguedes.tcc.util;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import br.com.jguedes.tcc.gerenciadorrelatorioarquivo.AmbienteArquivacaoJSON;
import br.com.jguedes.tcc.gerenciadorrelatorioarquivo.FachadaArquivador;

public class AvaliadorDeAcessibilidade implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8734164138205344653L;

	private ContextoDeAvaliacao contexto;

	private String userContextoID;

	public AvaliadorDeAcessibilidade(ContextoDeAvaliacao contexto) {

		this.contexto = contexto;
	}

	public AvaliadorDeAcessibilidade(String userContextoID) {

		this.userContextoID = userContextoID;

		this.contexto = UsersLogados.get(this.userContextoID).getContexto();

	}

	private void avaliar() {

		contexto.setTotLinks(0);

		FachadaArquivador.inicializarDiretorioAvaliacao(contexto.getFolderTemp());

		FachadaArquivador.setAmbienteArquivacao(AmbienteArquivacaoJSON.JSON_semHTML_semCONTEUDOLINHAS);

		// initDiretorios(contexto);

		// salvarCriterioNoHD(contexto.getCriterio(), contexto.getUser());

		// contexto.atualizarNoHD();

		String url = contexto.getCriterio().getUrl();

		String path = contexto.getUserFolder();

		try {

			new Crowler4j(url, path);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private void initDiretorios(ContextoDeAvaliacao contexto) {

		String urlInicial = contexto.getCriterio().getUrl();

		String urlSemHttp = urlInicial.replaceAll("http://", "");

		File diretorio = null;

		String pathRoot = contexto.getFolderTemp();

		if (urlSemHttp.indexOf("/") != -1) {

			diretorio = new File(pathRoot + urlSemHttp.substring(0, urlSemHttp.indexOf("/")));

		} else {

			diretorio = new File(pathRoot + urlSemHttp);

		}

		String strDiretorio = pathRoot + diretorio.getName();

		contexto.setNomeDoProjeto(diretorio.getName());

		if (!diretorio.isDirectory()) {

			diretorio.mkdirs();

		}

		strDiretorio = strDiretorio + "/reparo";

		diretorio = new File(strDiretorio);

		if (!diretorio.isDirectory()) {

			diretorio.mkdir();

		}

		strDiretorio = strDiretorio + "/temp";

		diretorio = new File(strDiretorio);

		if (!diretorio.isDirectory()) {

			diretorio.mkdir();

		}

	}

	// private void salvarCriterioNoHD(CriterioAvaliacao criterio, User user) {
	//
	// UserContexto.toXML(criterio, FileName.CRITERIODEAVALIACAO, user);
	//
	// }

	@Override
	public void run() {

		long i = new Date().getTime();

		avaliar();

		contexto.setImprimirNoConsole(true);

		contexto.logger("AvaliadorDeAcessibilidade.run(): INICIANDO...\n");
		contexto.logger("Critérios da avaliação:");
		contexto.logger("\tURL: " + contexto.getCriterio().getUrl());
		contexto.logger("\tPadrão de acessibilidade: " + contexto.getCriterio().getPadraoAcessibilidade());
		contexto.logger("\tProfundidade: " + contexto.getCriterio().getProfundidade());
		contexto.logger("\tVerificar prioridade 1: " + (contexto.getCriterio().isPrioridade1() ? "Sim" : "Não"));
		contexto.logger("\tVerificar prioridade 2: " + (contexto.getCriterio().isPrioridade2() ? "Sim" : "Não"));
		contexto.logger("\tVerificar prioridade 3: " + (contexto.getCriterio().isPrioridade3() ? "Sim" : "Não") + "\n");

		contexto.logger(
				"AvaliadorDeAcessibilidade.run(): DONE\t" + ((new Date().getTime() - i) / 1000) + " segundos.\n");

		contexto.logger("QUANTIDADE DE LINKS ANALISADOS: " + contexto.getTotLinks());

		contexto.logger(FachadaArquivador.getDadosDePasta(contexto.getFolderTemp()));

	}
}
