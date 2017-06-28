package br.com.jguedes.tcc.gerenciadorrelatorioarquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import ases.G_File;
import ases.RelatorioDaUrl;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

public abstract class GerenciadorRelatorioArquivo implements Serializable {

	private static final long serialVersionUID = -2579841987777799989L;

	public abstract void gravarRelatorio(ContextoDeAvaliacao contexto, RelatorioDaUrl relatorio);

	public abstract RelatorioDaUrl recuperarRelatorio(ContextoDeAvaliacao contexto, RelatorioDaUrl relatorio);

	public void criarArquivoConteudoHTML(StringBuilder conteudoHTML, ContextoDeAvaliacao contexto, String fileName) {

		criarArquivoConteudoHTML(conteudoHTML.toString(), contexto, fileName);

	}

	public void criarArquivoConteudoHTML(String conteudoHTML, ContextoDeAvaliacao contexto, String fileName) {

		G_File arq = new G_File(contexto.getFolderTemp() + fileName);

		arq.write(conteudoHTML);

	}

	public StringBuilder recuperarArquivoConteudoHTML(ContextoDeAvaliacao contexto, String fileName) {

		G_File arq = new G_File(contexto.getFolderTemp() + fileName);

		if (arq.exists()) {

			return new StringBuilder(arq.read());

		}

		return getConteudoAlternativo(contexto);

	}

	public void deleteArquivoConteudoHTML(ContextoDeAvaliacao contexto, String fileName) {

		File t = new File(contexto.getFolderTemp() + fileName);

		if (t.exists()) {
			t.delete();
		}

	}

	private StringBuilder getConteudoAlternativo(ContextoDeAvaliacao contexto) {
		StringBuilder sb = new StringBuilder();

		StringBuilder sbd = new StringBuilder();

		final int mb = 1024;

		if (contexto.getCriterio().getProfundidade().getValue() > 5) {

			File file = new File(contexto.getCriterio().getUrl());

			FileInputStream fis = null;

			try {

				if (file.exists()) {

					fis = new FileInputStream(file);

					byte[] dados = new byte[mb];

					int bytesLidos = 0;

					while ((bytesLidos = fis.read(dados)) > 0) {

						sb.append(new String(dados, 0, bytesLidos));

					}

					fis.close();

				} else {

					return null;

				}

			} catch (Exception e) {

				return null;

			}

			finally {

				if (fis != null) {

					try {

						fis.close();

					} catch (Exception e) {

					}

				}

			}

			sbd.append(sb.toString().trim());

		} else {

			File file = new File(contexto.getFolderTemp() + this.hashCode());

			FileInputStream fis = null;

			ObjectInputStream ois = null;

			try {

				if (file.exists()) {

					fis = new FileInputStream(file);

					ois = new ObjectInputStream(fis);

					sbd = new StringBuilder((String) ois.readObject());

				} else {

					return null;

				}

			} catch (Exception f) {

				return null;

			} finally {

				if (fis != null) {

					try {

						fis.close();

					} catch (Exception f) {

					}

				}

				if (ois != null) {

					try {

						ois.close();

					} catch (Exception f) {

					}

				}

			}

		}

		return sbd;
	}

}
