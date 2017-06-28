package br.com.jguedes.tcc.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.jguedes.tcc.model.criterioavaliacao.CriterioAvaliacao;
import br.com.jguedes.tcc.support.UserContexto;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ContextoDeAvaliacao implements Serializable {

	private static final long serialVersionUID = 3248195330696365305L;

	@XmlElement
	private String userContextoID;

	@XmlTransient
	private CriterioAvaliacao criterio;

	@XmlElement
	private String nomeDoProjeto;

	@XmlElement
	private int totLinks;

	@XmlElement
	private String linkEvalCodeRelAtual;

	@XmlTransient
	private boolean imprimirNoConsole;

	@XmlTransient
	private String diretorioPastasTemporariasUsuarios = "/home/joaoguedes/IsAcessivel/tempUsers/";

	// @XmlTransient
	// private ResumoDeAvaliacao resumoDeAvaliacaoAtual;

	public ContextoDeAvaliacao(String userContextoID) {

		this.userContextoID = userContextoID;

		ifExistsDeleteUserFolder();

		imprimirNoConsole = true;

		logger("ContextoDeAvaliacao(String userContextoID): contexto criado com sucesso!");

		imprimirNoConsole = false;

	}

	public String getUserFolder() {

		if (diretorioPastasTemporariasUsuarios.lastIndexOf(File.separator) == diretorioPastasTemporariasUsuarios.length() - 1) {

			diretorioPastasTemporariasUsuarios = diretorioPastasTemporariasUsuarios.substring(0, diretorioPastasTemporariasUsuarios.lastIndexOf(File.separator));

		}

		return diretorioPastasTemporariasUsuarios.concat(File.separator).concat(userContextoID).concat(File.separator);

	}

	public String getLinkEvalCodeRelAtual() {
		return linkEvalCodeRelAtual;
	}

	public void setLinkEvalCodeRelAtual(String linkEvalCodeRelAtual) {
		this.linkEvalCodeRelAtual = linkEvalCodeRelAtual;
	}

	public CriterioAvaliacao getCriterio() {
		return criterio;
	}

	public void setCriterio(CriterioAvaliacao criterio) {
		this.criterio = criterio;
	}

	public String getNomeDoProjeto() {
		return nomeDoProjeto;
	}

	public void setNomeDoProjeto(String nomeDoProjeto) {
		this.nomeDoProjeto = nomeDoProjeto;
	}

	public String getFolderTemp() {

		return getUserFolder() + "temp" + File.separator;

	}

	public String getFolderConfig() {
		return getUserFolder() + "config" + File.separator;
	}

	public String getFolderCache() {
		return getUserFolder() + "cache" + File.separator;
	}

	/**
	 * Incrementa a quantidade de links e atualiza no HD
	 */
	public void incrementaTotLinks() {
		this.totLinks++;
		// atualizarNoHD();
	}

	public int getTotLinks() {
		return totLinks;
	}

	public void setTotLinks(int totLinks) {
		this.totLinks = totLinks;
	}

	public void logger(String texto) {

		texto = "[" + this.userContextoID + "]\t" + texto;

		escreve(this.getFolderTemp() + "contexto.log", texto);

		// G_Log.loggerInNewLine((this.getFoldersTemp() + "contexto.log"),
		// texto);

		// try {
		//
		// FileWriter arq = new FileWriter(this.getFolderTemp() +
		// "contexto.log", true);
		//
		// arq.write(texto + "\n");
		//
		// arq.close();
		//
		// } catch (IOException e) {
		// }

		if (imprimirNoConsole) {

			System.out.println(texto);

		}

	}

	public void atualizarNoHD() {

		UserContexto.toXML(this, FileName.CONTEXTODEAVALIACAO, this.getFolderTemp());

	}

	/**
	 * Grava os links no HD
	 */
	public void gravaLink(String link) {

		escreve(this.getFolderTemp() + "links.log", link);

		// try {
		//
		// FileWriter arq = new FileWriter(this.getFolderTemp() + "links.log",
		// true);
		//
		// arq.write(link + "\n");
		//
		// arq.close();
		//
		// } catch (IOException e) {
		// }

	}

	private synchronized void escreve(String path, String texto) {

		try {

			FileWriter arq = new FileWriter(path, true);

			arq.write(texto + "\n");

			arq.close();

		} catch (IOException e) {
		}

	}

	public void ifExistsDeleteUserFolder() {

		File f = new File(this.getUserFolder());

		deleteDiretorio(f);

	}

	private void deleteDiretorio(File f) {
		// Make sure the file or directory exists and isn't write protected
		if (!f.exists()) {
			// throw new IllegalArgumentException("Delete: no such file or
			// directory: " + f.getName());
			return;
		}
		if (!f.canWrite())
			throw new IllegalArgumentException("Delete: write protected: " + f.getName());

		// If it is a directory, make sure it is empty
		if (f.isDirectory()) {

			if (f.listFiles().length > 0) {

				for (File subfile : f.listFiles()) {

					deleteDiretorio(subfile);

				}

			}

		}

		// Attempt to delete it
		boolean success = f.delete();

		if (!success)
			throw new IllegalArgumentException("Delete: deletion failed");

	}

	public void setImprimirNoConsole(boolean imprimirNoConsole) {

		this.imprimirNoConsole = imprimirNoConsole;

	}

}
