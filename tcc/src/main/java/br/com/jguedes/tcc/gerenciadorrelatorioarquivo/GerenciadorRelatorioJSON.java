package br.com.jguedes.tcc.gerenciadorrelatorioarquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import ases.RelatorioDaUrl;
import br.com.jguedes.tcc.testeJson.RelatorioBean;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

public class GerenciadorRelatorioJSON extends GerenciadorRelatorioArquivo {

	private static final long serialVersionUID = -5841480370839097766L;

	private ContextoDeAvaliacao contexto;

	private RelatorioDaUrl relatorio;

	private AmbienteArquivacaoJSON ambienteArquivacao;

	public GerenciadorRelatorioJSON(AmbienteArquivacaoJSON ambienteArquivacao) {
		this.ambienteArquivacao = ambienteArquivacao;
	}

	@Override
	public void gravarRelatorio(ContextoDeAvaliacao contexto, RelatorioDaUrl relatorio) {

		this.relatorio = relatorio;

		this.contexto = contexto;

		geraArquivoRelatorioEmJson();

	}

	@Override
	public RelatorioDaUrl recuperarRelatorio(ContextoDeAvaliacao contexto, RelatorioDaUrl relatorio) {

		this.contexto = contexto;

		this.relatorio = relatorio;

		buscarRelatorioArquivo();

		return this.relatorio;

	}

	private void buscarRelatorioArquivo() {

		String fileName = this.relatorio.getLinkEvalCode();

		FileReader reader = null;

		try {

			reader = new FileReader(contexto.getFolderTemp() + fileName + ".json");

			carregarRelatorioDaUrl(new Gson().fromJson(reader, RelatorioBean.class));

			reader.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void carregarRelatorioDaUrl(RelatorioBean fromJson) {

		this.relatorio = new RelatorioDaUrl();

		this.relatorio.setLinkEvalCode(fromJson.getLinkEvalCode());
		this.relatorio.setUrl(fromJson.getUrl());
		this.relatorio.setProfundidade(fromJson.getProfundidade());
		this.relatorio.setPadraoAcessibilidade(fromJson.getPadraoAcessibilidade());
		this.relatorio.setMostraP1(fromJson.isMostraP1());
		this.relatorio.setMostraP2(fromJson.isMostraP2());
		this.relatorio.setMostraP3(fromJson.isMostraP3());
		this.relatorio.setAvisosPrioridade1(fromJson.getAvisosPrioridade1());
		this.relatorio.setAvisosPrioridade2(fromJson.getAvisosPrioridade2());
		this.relatorio.setAvisosPrioridade3(fromJson.getAvisosPrioridade3());
		this.relatorio.setErrosPrioridade1(fromJson.getErrosPrioridade1());
		this.relatorio.setErrosPrioridade2(fromJson.getErrosPrioridade2());
		this.relatorio.setErrosPrioridade3(fromJson.getErrosPrioridade3());
		this.relatorio.setListaAvisosP1(fromJson.getListaAvisosP1());
		this.relatorio.setListaAvisosP2(fromJson.getListaAvisosP2());
		this.relatorio.setListaAvisosP3(fromJson.getListaAvisosP3());
		this.relatorio.setListaErrosP1(fromJson.getListaErrosP1());
		this.relatorio.setListaErrosP2(fromJson.getListaErrosP2());
		this.relatorio.setListaErrosP3(fromJson.getListaErrosP3());

	}

	/**
	 * Grava o relat&oacute;rio no HD em Json na pasta cache
	 */
	private void geraArquivoRelatorioEmJson() {

		gravaJsonNoHD(carregarRelatorioBean());

	}

	private void gravaJsonNoHD(RelatorioBean relatorioBean) {

		String fileName = this.relatorio.getLinkEvalCode();

		File outFile = new File(contexto.getFolderTemp() + fileName + ".json");

		try {

			FileWriter writer = new FileWriter(outFile, true);

			new Gson().toJson(relatorioBean, RelatorioBean.class, writer);

			writer.flush();

			writer.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public RelatorioBean carregarRelatorioBean() {

		RelatorioBean r = new RelatorioBean();

		r.setLinkEvalCode(this.relatorio.getLinkEvalCode());
		r.setUrl(this.relatorio.getUrl());
		r.setProfundidade(this.relatorio.getProfundidade());
		r.setPadraoAcessibilidade(this.relatorio.getPadraoAcessibilidade());

		if (this.relatorio.mostraP1()) {
			r.setMostraP1(this.relatorio.mostraP1());
			r.setAvisosPrioridade1(this.relatorio.getAvisosPrioridade1());
			r.setErrosPrioridade1(this.relatorio.getErrosPrioridade1());
			r.setListaAvisosP1(this.relatorio.getListaAvisosP1());
			r.setListaErrosP1(this.relatorio.getListaErrosP1());
		}
		if (this.relatorio.mostraP2()) {
			r.setMostraP2(this.relatorio.mostraP2());
			r.setAvisosPrioridade2(this.relatorio.getAvisosPrioridade2());
			r.setErrosPrioridade2(this.relatorio.getErrosPrioridade2());
			r.setListaAvisosP2(this.relatorio.getListaAvisosP2());
			r.setListaErrosP2(this.relatorio.getListaErrosP2());
		}
		if (this.relatorio.mostraP3()) {
			r.setMostraP3(this.relatorio.mostraP3());
			r.setAvisosPrioridade3(this.relatorio.getAvisosPrioridade3());
			r.setErrosPrioridade3(this.relatorio.getErrosPrioridade3());
			r.setListaAvisosP3(this.relatorio.getListaAvisosP3());
			r.setListaErrosP3(this.relatorio.getListaErrosP3());
		}

		if (ambienteArquivacao.isComCONTEUDOLINHAS()) {

			String[] linhasConteudoHTML = super.recuperarArquivoConteudoHTML(contexto, this.relatorio.getLinkEvalCode())
					.toString().split("\n");

			Map<String, String> conteudo = new HashMap<String, String>();

			for (int i = 0; i < linhasConteudoHTML.length; i++) {

				String numeroLinha = String.format("%04d", (i + 1));

				conteudo.put(numeroLinha, linhasConteudoHTML[i]);

			}

			r.setConteudo(conteudo);

		}

		return r;

	}

}
