package br.com.jguedes.tcc.gerenciadorrelatorioarquivo;

import java.io.File;
import java.io.Serializable;

import ases.RelatorioDaUrl;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

public class FachadaArquivador implements Serializable {

	private static final long serialVersionUID = 7967232016513424533L;

	private static AmbienteArquivacao ambienteArquivacao;

	private static GerenciadorRelatorioArquivo arquivador;

	public static void setAmbienteArquivacao(AmbienteArquivacao ambienteArquivacao) {

		FachadaArquivador.ambienteArquivacao = ambienteArquivacao;

		if (ambienteArquivacao instanceof AmbienteArquivacaoXML) {

			arquivador = new GerenciadorRelatorioXML((AmbienteArquivacaoXML) ambienteArquivacao);

		} else if (ambienteArquivacao instanceof AmbienteArquivacaoJSON) {

			arquivador = new GerenciadorRelatorioJSON((AmbienteArquivacaoJSON) ambienteArquivacao);

		}

	}

	public static void gravarRelatorio(ContextoDeAvaliacao contexto, RelatorioDaUrl relatorioDaUrl) {

		arquivador.gravarRelatorio(contexto, relatorioDaUrl);

		if (!ambienteArquivacao.isComHTML()) {

			arquivador.deleteArquivoConteudoHTML(contexto, relatorioDaUrl.getLinkEvalCode());

		}

	}

	public static void criarArquivoConteudoHTML(String conteudoHTML, ContextoDeAvaliacao contexto, String fileName) {

		arquivador.criarArquivoConteudoHTML(conteudoHTML, contexto, fileName);

	}

	public static RelatorioDaUrl recuperarRelatorio(ContextoDeAvaliacao contexto, RelatorioDaUrl relatorioDaUrl) {

		return arquivador.recuperarRelatorio(contexto, relatorioDaUrl);

	}

	public static StringBuilder recuperarArquivoConteudoHTML(ContextoDeAvaliacao contexto, String fileName) {

		return arquivador.recuperarArquivoConteudoHTML(contexto, fileName);

	}

	public static String getDadosDePasta(String folder) {

		File fTemp = new File(folder);

		StringBuilder sb = new StringBuilder();

		sb.append("QUANTIDADE DE ARQUIVOS GERADOS: " + fTemp.listFiles().length);

		long z = 0;
		for (File f : fTemp.listFiles()) {

			z += f.length();

		}

		String b = z + " bytes";

		if (z <= 1024) {
			b = z + " bytes";
		} else if (Long.divideUnsigned(z, 1024L) <= 1024L) {
			b = Long.divideUnsigned(z, 1024L) + " kb";
		} else if (Long.divideUnsigned(z, 1024L * 1024L) <= 1024L) {
			b = Long.divideUnsigned(z, 1024L * 1024L) + " mb";
		} else if (Long.divideUnsigned(z, 1024L * 1024L * 1024L) <= 1024L) {
			b = Long.divideUnsigned(z, 1024L * 1024L * 1024L) + " gb";
		} else if (Long.divideUnsigned(z, 1024L * 1024L * 1024L * 1024L) <= 1024L) {
			b = Long.divideUnsigned(z, 1024L * 1024L * 1024L * 1024L) + " tb";
		}

		sb.append("\n");

		sb.append("\t\tESPAÇO OCUPADO PELA PASTA: " + b);

		sb.append("\n");

		sb.append(ambienteArquivacao.toString());

		return sb.toString();

	}

	public static void inicializarDiretorioAvaliacao(String folderTemp) {

		System.out.println("inicializarDiretorioAvaliacao(String folderTemp)");

		File folder = new File(folderTemp);

		if (folder.exists()) {

			for (File f : folder.listFiles()) {

				if (f.isFile() && !f.getName().equals("contexto.log")) {

					f.delete();

				}

			}

		}

	}

}
