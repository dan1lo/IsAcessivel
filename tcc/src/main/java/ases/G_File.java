/*******************************************************************************
 * Copyright 2005, 2006, 2007, 2008 Acessibilidade Brasil
 * Este arquivo � parte do programa ASES - Avaliador e Simulador para AcessibilidadE de S�tios
 * O ASES � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
 * publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a, ou (na sua opni�o) qualquer vers�o posterior.
 * Este programa � distribuido na esperan�a que possa ser  util, mas SEM NENHUMA GARANTIA; sem uma garantia implicita de ADEQUA��O a qualquer  MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
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

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;
//
//import br.org.acessobrasil.silvinha2.mli.GERAL;

/**
 * Classe gen�rica para IO leitura e escrita simples de arquivos como na maioria
 * das linguagens
 * 
 * Change Log: v1.2 14/11/2007: M�todo para limpar o diret�rio clearDir() v1.1
 * 22/08/2007: Colocada uma caixa de di�logo para abrir e salvar arquivos v1:
 * Carregador gen�rico para ler e gravar arquivos
 * 
 * @version 1.2
 * @author Fabio Issamu Oshiro
 * 
 */
public class G_File {

	private String path;

	private StringBuilder conteudo;

	/**
	 * Deixar como false!!!
	 */
	private boolean lazyLoad = false;

	public String erroDesc;

	/**
	 * Indica o caminho do arquivo manualmente
	 * 
	 * @param path
	 *            :parametro para indicar o caminho do arquivo
	 * 
	 */
	public G_File(String path) {

		int posBarra = path.indexOf("\\");

		int posDoisPontos = path.indexOf(":");

		if (posBarra == -1) {

			posBarra = path.indexOf("/");

		}

		if (posDoisPontos > posBarra) {

			this.path = path.substring(0, posBarra) + path.substring(posBarra).replace(":", "-");

			System.out.println("G_File.path = '" + this.path + "'");

		} else {

			this.path = path;// .replaceAll(":", "-");

		}

	}

	/**
	 * Indica o caminho do o arquivo atrav�s de componente Swing
	 */
	public G_File() {
		JFileChooser fc = new JFileChooser();
		int opt = fc.showOpenDialog(null);
		if (opt == JFileChooser.APPROVE_OPTION) {
			path = fc.getSelectedFile().getPath();
		}
	}

	/**
	 * Limitar o tipo de extens�o e indicar o caminho mais recente
	 * 
	 * @param recentPath
	 *            passa o Path recente para que ele n�o necessite navegar
	 *            novamente at� o �ltimo folder aberto
	 * @param filtro
	 *            Array com os tipos de extens�es aceitas Ex: ".css"
	 */
	public G_File(String recentPath, String filtro[]) {
		JFileChooser fc = new JFileChooser();

		G_FileFilter ff = new G_FileFilter(filtro);
		fc.addChoosableFileFilter(ff);

		fc.setSelectedFile(new File(recentPath + " "));
		int opt = fc.showOpenDialog(null);
		if (opt == JFileChooser.APPROVE_OPTION) {
			path = fc.getSelectedFile().getPath();
		}
	}

	/**
	 * Limitar o tipo de extens�o e indicar o caminho mais recente
	 */
	public G_File(String recentPath, String filtro) {
		JFileChooser fc = new JFileChooser();
		G_FileFilter ff = new G_FileFilter(filtro, "Arquivos *" + filtro);
		fc.addChoosableFileFilter(ff);
		fc.setSelectedFile(new File(recentPath + " "));
		int opt = fc.showOpenDialog(null);
		if (opt == JFileChooser.APPROVE_OPTION) {
			path = fc.getSelectedFile().getPath();
		}
	}

	/**
	 * Abre a caixa de di�logo para salvar como
	 * 
	 * @param conteudo
	 *            Texto a ser salvo
	 * @param filtro
	 *            Tipo de extens�o permitida
	 * @return true
	 */
	public boolean openDialogSaveAs(String conteudo, String filtro[], Component parentComponent) {
		JFileChooser fc = new JFileChooser();
		G_FileFilter ff = new G_FileFilter(filtro);
		fc.addChoosableFileFilter(ff);
		if (this.path != null && !this.path.equals("") && !this.path.trim().equals("null")) {
			fc.setSelectedFile(new File(this.path + " "));
		}

		fc.setDialogTitle("Salvar");
		int salvar = fc.showSaveDialog(parentComponent);
		boolean retorno = false;
		if (salvar == JFileChooser.APPROVE_OPTION) {
			String fullPath = fc.getSelectedFile().getPath();
			boolean extensaoOk = false;
			for (int i = 0; i < filtro.length; i++) {
				if (fullPath.endsWith(filtro[i])) {
					extensaoOk = true;
					break;
				}
			}
			if (!extensaoOk) {
				fullPath += filtro[0];
			}
			File file = new File(fullPath);
			if (file.exists()) {
				// int confirmar = JOptionPane.showConfirmDialog(fc,
				// GERAL.SOBRESCREVER_ARQUIVO, GERAL.BTN_SALVAR,
				// JOptionPane.YES_NO_OPTION);
				// if (confirmar == JOptionPane.CANCEL_OPTION) {
				// // JOptionPane.showMessageDialog(fc, TokenLang.DIALOG_7);
				// } else {
				// this.path = fullPath;
				// this.write(conteudo);
				// retorno = true;
				// }
			} else {
				this.path = fullPath;
				this.write(conteudo);
				retorno = true;
			}
		}
		return retorno;
	}

	/**
	 * Abre a caixa de di�logo salvar como
	 * 
	 */
	public boolean openDialogSaveAs(String filtro[]) {
		JFileChooser fc = new JFileChooser();
		G_FileFilter ff = new G_FileFilter(filtro);
		fc.addChoosableFileFilter(ff);
		if (this.path != null && !this.path.equals("")) {
			fc.setSelectedFile(new File(this.path + " "));
		}
		fc.setDialogTitle("Salvar");
		int salvar = fc.showSaveDialog(null);
		boolean retorno = false;
		if (salvar == JFileChooser.APPROVE_OPTION) {
			String fullPath = fc.getSelectedFile().getPath();
			File file = new File(fullPath);
			if (file.exists()) {
				// int confirmar = JOptionPane.showConfirmDialog(fc,
				// GERAL.SOBRESCREVER_ARQUIVO, GERAL.BTN_SALVAR,
				// JOptionPane.YES_NO_OPTION);
				// if (confirmar == JOptionPane.CANCEL_OPTION) {
				// // JOptionPane.showMessageDialog(fc, TokenLang.DIALOG_7);
				// } else {
				// String conteudo = this.read();
				// this.path = fullPath;
				// this.write(conteudo);
				// retorno = true;
				// }
			} else {
				String conteudo = this.read();
				this.path = fullPath;
				this.write(conteudo);
				retorno = true;
			}
		}
		return retorno;
	}

	/**
	 * Salva uma BufferedImage como JPG
	 * 
	 * @param bufferedImageAtual
	 * @return true
	 * @throws IOException
	 */
	public boolean openDialogSaveAs(BufferedImage bufferedImageAtual) throws IOException {
		JFileChooser fc = new JFileChooser();
		G_FileFilter ff = new G_FileFilter(new String[] { ".jpg" });
		fc.addChoosableFileFilter(ff);
		if (this.path != null && !this.path.equals("")) {
			fc.setSelectedFile(new File(this.path + " "));
		}
		fc.setDialogTitle("Salvar");
		int salvar = fc.showSaveDialog(null);
		boolean retorno = false;
		if (salvar == JFileChooser.APPROVE_OPTION) {
			String fullPath = fc.getSelectedFile().getPath();
			File file = new File(fullPath);
			if (file.exists()) {
				// int confirmar = JOptionPane.showConfirmDialog(fc,
				// GERAL.SOBRESCREVER_ARQUIVO, GERAL.BTN_SALVAR,
				// JOptionPane.YES_NO_OPTION);
				// if (confirmar == JOptionPane.CANCEL_OPTION) {
				// // JOptionPane.showMessageDialog(fc, TokenLang.DIALOG_7);
				// } else {
				// this.path = fullPath;
				// ImageIO.write(bufferedImageAtual, "jpg", new File(fullPath));
				// retorno = true;
				// }
			} else {
				this.path = fullPath;
				ImageIO.write(bufferedImageAtual, "jpg", new File(fullPath));
				retorno = true;
			}
		}
		return retorno;

	}

	/**
	 * Parametro para ver se deixa ou n�o na mem�ria. caso colocado como false a
	 * cada read() ele l� novamente do HD
	 * 
	 * @param na_memoria
	 *            se true (default) deixa o arquivo na mem�ria
	 */
	public void setLazyLoad(boolean na_memoria) {
		lazyLoad = na_memoria;
	}

	/**
	 * Na primeira execu��o coloca o conteudo do arquivo no atributo
	 * StringBuilder conteudo em outras execu��es se na_memoria � falso, se for
	 * le novamente do disco caso seja verdadeiro este metodo n�o faz nada. caso
	 * colocado como false a cada read() ele l� novamente do HD
	 */

	private void readConteudo() {

		if (conteudo != null && lazyLoad)
			return;
		conteudo = new StringBuilder();
		if (this.path == null)
			return;
		if (!this.exists())
			return;

		try {
			FileReader fr;
			fr = new FileReader(this.path);

			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			while (linha != null) {
				conteudo.append(linha).append('\n');
				linha = br.readLine();
			}
			br.close();
			return;
		} catch (FileNotFoundException e1) {
			System.out.println("Aviso G_File.readConteudo()\n\tnot found:" + path);
			e1.printStackTrace();
			return;
		} catch (IOException e) {
			System.out.println("Aviso G_File.readConteudo()\n\tIOException:" + path);
			e.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("Aviso G_File.readConteudo()\n\tException:" + path);
			e.printStackTrace();
			return;
		}
		/*
		 * final int mb = 1024; File file = new File(this.path); FileInputStream
		 * fis = null; try { if (file.exists()) { fis = new
		 * FileInputStream(file);
		 * 
		 * byte[] dados = new byte[mb]; int bytesLidos = 0; while ((bytesLidos =
		 * fis.read(dados)) > 0) { conteudo.append(new String(dados, 0,
		 * bytesLidos)); } fis.close(); } else { return; } } catch (Exception e)
		 * { erroDesc = e.getMessage(); return; } finally { if (fis != null) {
		 * try { fis.close(); } catch (Exception e) { } } }
		 */
		// return ;
		// conteudo = sb.toString().trim();
	}

	/**
	 * executa readConteudo e retorna o atributo conteudo como String
	 * 
	 * @return vers�o String do StringBuilder conteudo
	 */
	public String read() {

		// tentativa de lazy load
		readConteudo();

		if (conteudo.indexOf("á") != -1) {

			// Julgo que � UTF-8
			try {
				// System.out.println("Convertido para UTF8");
				return new String(conteudo.toString().getBytes(), "UTF8");

			} catch (UnsupportedEncodingException e) {
				// e.printStackTrace();
			}
		}

		return conteudo.toString();
		/*
		 * try { //System.out.println("Convertido ISO-8859-15"); //return new
		 * String(conteudo.toString().getBytes(),"ISO-8859-15"); } catch
		 * (UnsupportedEncodingException e) { //e.printStackTrace(); } return
		 * "";
		 */
	}

	/**
	 * Caso o arquivo exista apaga o seu conteudo e coloca a String, caso n�o
	 * exista este � criado e o conteudo da String � colocado no arquivo
	 * 
	 * @param texto
	 *            contendo o novo conteudo do arquivo
	 */
	public boolean write(String texto) {

		if (this.path == null)
			return false;
		File file = new File(this.path);
		if (!file.exists()) {
			try {
				// tenta criar o diret�rio
				file.getParentFile().mkdirs();
			} catch (Exception e) {

			}
		}
		texto += "";
		FileWriter arq;
		try {
			arq = new FileWriter(this.path);
			arq.write(texto);
			arq.flush();
			arq.close();

			if (lazyLoad) {
				conteudo = new StringBuilder();
				conteudo.append(texto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * caso o path tenha algum arquivo e caso o arquivo exista este � deletado
	 */
	public void delete() {
		if (this.path == null)
			return;

		File arq = new File(this.path);
		if (arq.exists()) {
			arq.delete();
		}
	}

	/**
	 * Caso o arquivo exista adiciona a String, caso n�o exista este � criado e
	 * o conteudo da String � colocado no arquivo
	 * 
	 * @param texto
	 *            contendo conteudo a ser adicionado no arquivo
	 */
	public void append(String texto) {
		FileWriter arq;
		try {
			arq = new FileWriter(this.path, true);
			arq.write(texto);
			arq.close();
			if (lazyLoad) {
				if (conteudo == null)
					conteudo = new StringBuilder();
				conteudo.append(texto);
				// a linha abaixo � muuuuiiito lenta com o String conteudo
				// conteudo+=texto;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifica se o arquivo j� existe
	 */
	public boolean exists() {
		File testa = new File(path);
		return testa.exists();
	}

	/**
	 * Cria um arquivo vazio
	 */
	public void newFile() {
		append("");
	}

	/**
	 * Retorna o arquivo de acordo com o path que foi informado
	 */
	public File getFile() {
		if (path != null) {
			return new File(path);
		} else {
			return null;
		}
	}

	/**
	 * Retorna o arquivo como InputStream
	 * 
	 * @return InputStream referente ao arquivo
	 */
	public InputStream getInputStream() {
		InputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fis;
	}

	/**
	 * Atribui o caminho do arquivo
	 * 
	 * @param string
	 */
	public void setPath(String string) {
		this.path = string;
	}

	/**
	 * Retorna o caminho do arquivo de acordo com protocolo "file:"
	 * 
	 * @return path do arquivo para ser usado em navegadores
	 */
	public String getUrlPath() {
		File arq = new File(this.path);
		String url = arq.getAbsolutePath();
		url = "file:///" + url.replace("\\", "/");
		return url;
	}

	/**
	 * Limpa o diret�rio passado como parametro menos o .svn
	 * 
	 * @param nomeDir
	 *            nome do diretorio
	 */
	public static void clearDir(String nomeDir) {
		File dir = new File(nomeDir);
		if (dir.isDirectory()) {
			delFolderCont(dir);
		}
	}

	/**
	 * Fun��o recursiva para limpar diret�rios
	 * 
	 * @param dir
	 */
	private static void delFolderCont(File dir) {
		String path = dir.getAbsolutePath();
		for (String fileName : dir.list()) {
			File file = new File(path + "/" + fileName);
			if (file.getName().equals(".svn"))
				continue;
			if (file.isDirectory()) {
				delFolderCont(file);
				file.delete();
			} else {
				file.delete();
			}
		}
	}

	/**
	 * Deixa passar somente caracteres a-z A-Z 1-0 . - _
	 * 
	 * @param url
	 * @return path
	 */
	public static String url2path(String url) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < url.length(); i++) {
			char aux = url.charAt(i);
			if (('a' <= aux && aux <= 'z') || ('A' <= aux && aux <= 'Z') || ('1' <= aux && aux <= '9') || aux == '0'
					|| aux == '.' || aux == '_' || aux == '-') {
				sb.append(aux);
			} else {
				sb.append(",");
				sb.append((int) aux);
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * SubClasse para criar os filtros de extens�o
	 * 
	 * @author Fabio Issamu Oshiro
	 * 
	 */
	class G_FileFilter extends javax.swing.filechooser.FileFilter {
		private String descricao;

		private HashSet<String> arrExtensao = new HashSet<String>();

		/**
		 * Construtor do filtro
		 * 
		 * @param extensao
		 *            extens�o que ser� aceita
		 * @param descricao
		 *            descri��o do tipo do arquivo
		 */
		public G_FileFilter(String extensao, String descricao) {
			arrExtensao.add(extensao);
			this.descricao = descricao;
		}

		/**
		 * Construtor do filtro
		 * 
		 * @param extensao
		 *            extens�o que ser� aceita
		 */
		public G_FileFilter(String extensao[]) {
			this.descricao = "Arquivos ";
			for (int i = 0; i < extensao.length; i++) {
				arrExtensao.add(extensao[i]);
				this.descricao += "*" + extensao[i] + " ";
			}
		}

		@Override
		public boolean accept(File f) {
			String arr[] = f.getName().toLowerCase().split("\\.");
			String extens;
			if (arr.length > 0) {
				extens = "." + arr[arr.length - 1];
				if (arrExtensao.contains(extens)) {
					return true;
				}
			}
			return f.isDirectory();
		}

		@Override
		public String getDescription() {
			return descricao;
		}
	}

}
