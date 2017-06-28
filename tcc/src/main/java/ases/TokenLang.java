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
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Configura��o do software
 *
 */
public class TokenLang {

	// Erros
	public static String SERIAL_ERRADO;

	public static String SPLASH_ERRADO;

	public static String ERRO_1;

	public static String ERRO_2;

	public static String ERRO_3;

	public static String ERRO_4;

	public static String ERRO_5;

	public static String ERRO_6;

	public static String ERRO_7;

	public static String ERRO_8;

	public static String ERRO_9;

	public static String ERRO_10;

	public static String ERRO_11;

	public static String ERRO_12;

	public static String ERRO_13;

	public static String ERRO_14;

	public static String ERRO_15;

	public static String ERRO_16;

	public static String ERRO_17;

	public static String ERRO_18;

	// Dialogos
	public static String DIALOG_1;

	public static String DIALOG_2;

	public static String DIALOG_3;

	public static String DIALOG_4;

	public static String DIALOG_5;

	public static String DIALOG_6;

	public static String DIALOG_7;

	public static String DIALOG_8;

	public static String DIALOG_9;

	public static String DIALOG_10;

	public static String DIALOG_11;

	public static String DIALOG_12;

	public static String DIALOG_13;

	public static String DIALOG_14;

	public static String DIALOG_15;

	public static String DIALOG_16;

	// Labels
	public static String ADMIN_CAD;

	public static String AVALIADOR_CAD;

	public static String USUARIO_CAD;

	public static String REL_ADM_CAD;

	public static String EMAIL_CAD;

	public static String ERROS_SWT;

	public static String AVISOS_SWT;

	public static String AVISOS_ERROS_SWT;

	public static String DIARIO_SWT;

	public static String SEMANAL_SWT;

	public static String MENSAL_SWT;

	public static String QUINZE_SWT;

	public static String HORA_CAD;

	public static String REGISTRAR_LBL;

	public static String TITULO_WIN;

	public static String ARQ_MNU;

	public static String SAIR_MNU;

	public static String EXEC_MNU;

	public static String CONF_MNU;

	public static String HELP_MNU;

	public static String ABOUT_MNU;

	public static String SALVAR_MNU;

	public static String MNU_PDF;

	public static String ABRIR_MNU;

	public static String INSTITU_LBL;

	public static String ENDERE_LBL;

	public static String TEL_LBL;

	public static String SERIAL_LBL;

	public static String EXECUTEAGORA_MNU;

	public static String LOGIN_NOME_LBL;

	public static String LOGIN_PASS_LBL;

	public static String LBL_AVISO;

	public static String LBL_ARQUIVO;

	public static String LBL_ERRO;

	public static String LBL_SALVAR;

	// public static String LBL_CANCELAR;

	public static String LBL_VOLTAR;

	public static String LBL_CORRIGIR;

	public static String LBL_CNPJ;

	public static String LBL_CHAVE;

	public static String LBL_NIVEL;

	public static String LBL_URL;

	public static String LBL_PRIORIDADE1;

	public static String LBL_PRIORIDADE2;

	public static String LBL_PRIORIDADE3;

	public static String LBL_ERROS_P1;

	public static String LBL_ERROS_P2;

	public static String LBL_ERROS_P3;

	public static String LBL_AVISOS_P1;

	public static String LBL_AVISOS_P2;

	public static String LBL_AVISOS_P3;

	public static String LBL_CODIGO_FONTE;

	public static String LBL_SELECIONA_LINHA;

	public static String LBL_SALVAR_AVALIACAO;

	public static String LBL_DESCRICAO;

	public static String LBL_BORDER_DESC_INPUT;

	public static String BORDER_LBL_TIPOAVAL;

	public static String BORDER_LBL_PROFUNDIDADE;

	public static String BORDER_LBL_PRIORIDADE;

	public static String BORDER_LBL_RELATORIO;

	public static String BORDER_LBL_RESUMO;

	public static String BORDER_LBL_URL;

	public static String BORDER_LBL_LOCAL;

	public static String BTN_EXECUTAR_AGORA;

	public static String BTN_PARAR_EXECUCAO;

	public static String BTN_FINALIZAR;

	public static String BTN_VISUALIZAR;

	public static String CMB_PROFUNDIDADE_OPCAO_1;

	public static String CMB_PROFUNDIDADE_OPCAO_2;

	public static String CMB_PROFUNDIDADE_OPCAO_3;

	public static String CMB_PROFUNDIDADE_OPCAO_4;

	public static String CMB_PROFUNDIDADE_OPCAO_5;

	public static String CMB_PROFUNDIDADE_LOCAL_OPCAO_1;

	public static String CMB_PROFUNDIDADE_LOCAL_OPCAO_2;

	public static String LOCALIZAR;

	public static String CMB_SALVAR_OPCAO_1;

	public static String CMB_SALVAR_OPCAO_2;

	public static String CMB_SALVAR_OPCAO_3;

	public static String CMB_SALVAR_OPCAO_4;

	public static String MNU_LBL_OPCOES;

	public static String MNU_LBL_ATUALIZAR;

	public static String MNU_LBL_LINGUAGEM;

	public static String FILE_FILTER;

	public static String AVALIACAO_CONCLUIDA;

	public static String TBL_RELATORIO_COL_1;

	public static String TBL_RELATORIO_COL_2;

	public static String TBL_RELATORIO_COL_3;

	public static String TBL_RELATORIO_COL_4;

	public static String BORDER_LBL_PERIDIOCIDADE;

	public static String BORDER_LBL_HORARIO;

	public static String BORDER_LBL_DADOS;

	// regras
	public static String REGRA_1;

	public static String REGRA_2;

	public static String REGRA_3;

	public static String REGRA_4;

	public static String REGRA_5;

	public static String REGRA_6;

	public static String REGRA_7;

	public static String REGRA_8;

	public static String REGRA_9;

	public static String REGRA_10;

	public static String REGRA_11;

	public static String REGRA_12;

	public static String REGRA_13;

	public static String REGRA_14;

	public static String REGRA_15; //

	public static String REGRA_16; //

	public static String REGRA_17;

	public static String REGRA_18;

	public static String REGRA_19;

	public static String REGRA_20;

	public static String REGRA_21;

	public static String REGRA_22;

	public static String REGRA_23;

	public static String REGRA_24;

	public static String REGRA_25;

	public static String REGRA_26;

	public static String REGRA_27;

	public static String REGRA_28;

	public static String REGRA_29;

	public static String REGRA_30;

	public static String REGRA_31;

	public static String REGRA_32;

	public static String REGRA_33;

	public static String REGRA_34;

	public static String REGRA_35;

	public static String REGRA_36;

	public static String REGRA_37;

	public static String REGRA_38;

	public static String REGRA_39;

	public static String REGRA_40;

	public static String REGRA_41;

	public static String REGRA_42;

	public static String REGRA_43;

	public static String REGRA_44;

	public static String REGRA_45;

	public static String REGRA_46;

	public static String REGRA_47; //

	public static String REGRA_48;//

	public static String REGRA_49;

	public static String REGRA_50;

	public static String REGRA_51;//

	public static String REGRA_52;

	public static String REGRA_53;

	public static String REGRA_54;//

	public static String REGRA_55;//

	public static String REGRA_56;//

	public static String REGRA_57;//

	public static String REGRA_58;

	public static String REGRA_59;

	public static String REGRA_60;//

	public static String REGRA_61;

	public static String REGRA_62;//

	public static String REGRA_63;//

	public static String REGRA_64;//

	public static String REGRA_65;

	public static String REGRA_66;

	public static String REGRA_67;

	public static Properties props;

	public static String IDIOMA;

	public static String LANG;

	public TokenLang() {
	}

	public TokenLang(String language) throws IOException, IllegalAccessException {
		this.loadConfiguration(language);
	}

	private void loadConfiguration(String language) throws IOException, IllegalAccessException {
		PropertyLoader pl = new PropertyLoader();
		Properties props = pl.loadLanguage(language);
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			String value = props.getProperty(field.getName());
			if (value != null) {
				field.set(this, new String(value));
			}
		}
		/*
		 * Carrega o idioma salvo no painel config
		 */
		Properties opts = new PropertyLoader().loadProperty("config_geral");
		LANG = opts.getProperty("lang");
	}

	public static String getRegra(int idRegra) {
		try {
			return (String) TokenLang.class.getField("REGRA_" + String.valueOf(idRegra)).get(null);
		} catch (Exception e) {
			return "";
		}
	}

	public static String getRegra(String idRegra) {
		return getRegra(Integer.parseInt(idRegra));
	}

	public static Properties getProperties() {
		return props;
	}

	public static void setProperty(String key, String value, Object classe) {
		TokenLang.props.setProperty(key, value);
		try {
			PropertyLoader.saveProperty(classe, TokenLang.props);
		} catch (Exception e) {
		}
	}

	// public static void main(String[] args) {
	// try {
	// TokenLang tl = new TokenLang("pt");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
