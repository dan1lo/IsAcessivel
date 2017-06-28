package br.com.jguedes.tcc.gerenciadorrelatorioarquivo;

public enum AmbienteArquivacaoJSON implements AmbienteArquivacao {

	/**
	 * teste com JSON, com HTML, com conte&uacute;do das linhas
	 */
	JSON_comHTML_comCONTEUDOLINHAS(true, true), //

	/**
	 * teste com JSON, sem HTML, com conte&uacute;do das linhas
	 */
	JSON_semHTML_comCONTEUDOLINHAS(false, true), //

	/**
	 * teste com JSON, sem HTML, sem conte&uacute;do das linhas
	 */
	JSON_semHTML_semCONTEUDOLINHAS(false, false), //

	/**
	 * teste com JSON, com HTML, sem conte&uacute;do das linhas
	 */
	JSON_comHTML_semCONTEUDOLINHAS(true, false);//

	private AmbienteArquivacaoJSON(boolean comHTML, boolean comCONTEUDOLINHAS) {
		setComHTML(comHTML);
		setComCONTEUDOLINHAS(comCONTEUDOLINHAS);
	}

	private boolean comHTML, comCONTEUDOLINHAS;

	private void setComHTML(boolean comHTML) {
		this.comHTML = comHTML;
	}

	@Override
	public boolean isComHTML() {
		return comHTML;
	}

	private void setComCONTEUDOLINHAS(boolean comCONTEUDOLINHAS) {
		this.comCONTEUDOLINHAS = comCONTEUDOLINHAS;
	}

	@Override
	public boolean isComCONTEUDOLINHAS() {
		return comCONTEUDOLINHAS;
	}

	@Override
	public String toString() {

		return "\t\tFORMATO DE ARQUIVO: JSON\n\t\tMANTER ARQUIVOS COM CONTEÚDO HTML: " + (isComHTML() ? "SIM" : "NÃO")
				+ "\n\t\tINSERIR CONTEÚDO DE LINHAS NOS ARQUIVOS DE RELATÓRIO: "
				+ (isComCONTEUDOLINHAS() ? "SIM" : "NÃO");

	}

}
