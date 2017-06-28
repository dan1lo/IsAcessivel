package br.com.jguedes.tcc.gerenciadorrelatorioarquivo;

public enum AmbienteArquivacaoXML implements AmbienteArquivacao {
	/**
	 * teste com XML, com HTML, nomes tags completos, XML com conte&uacute;do
	 * das linhas
	 */
	XML_comHTML_nomeTagCOMPLETO_comCONTEUDOLINHAS(true, true, true), //

	/**
	 * teste com XML, sem HTML, nomes tags completos, XML com conte&uacute;do
	 * das linhas
	 */
	XML_semHTML_nomeTagCOMPLETO_comCONTEUDOLINHAS(false, true, true), //

	/**
	 * teste com XML, sem HTML, nomes tags reduzidos, XML com conte&uacute;do
	 * das linhas
	 */
	XML_semHTML_nomeTagREDUZIDO_comCONTEUDOLINHAS(false, false, true), //

	/**
	 * teste com XML, sem HTML, nomes tags reduzidos, XML sem conte&uacute;do
	 * das linhas
	 */
	XML_semHTML_nomeTagREDUZIDO_semCONTEUDOLINHAS(false, false, false), //

	/**
	 * teste com XML, com HTML, nomes tags reduzidos, XML sem conte&uacute;do
	 * das linhas
	 */
	XML_comHTML_nomeTagREDUZIDO_semCONTEUDOLINHAS(true, false, false); //

	private boolean comHTML, nomeTagCOMPLETO, comCONTEUDOLINHAS;

	private AmbienteArquivacaoXML(boolean comHTML, boolean nomeTagCOMPLETO, boolean comCONTEUDOLINHAS) {
		setComHTML(comHTML);
		setNomeTagCOMPLETO(nomeTagCOMPLETO);
		setComCONTEUDOLINHAS(comCONTEUDOLINHAS);
	}

	private void setComHTML(boolean comHTML) {
		this.comHTML = comHTML;
	}

	@Override
	public boolean isComHTML() {
		return comHTML;
	}

	private void setNomeTagCOMPLETO(boolean nomeTagCOMPLETO) {
		this.nomeTagCOMPLETO = nomeTagCOMPLETO;
	}

	public boolean isNomeTagCOMPLETO() {
		return nomeTagCOMPLETO;
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

		return "\t\tFORMATO DE ARQUIVO: XML\n\t\tMANTER ARQUIVOS COM CONTEÚDO HTML: " + (isComHTML() ? "SIM" : "NÃO")
				+ "\n\t\tINSERIR CONTEÚDO DE LINHAS NOS ARQUIVOS DE RELATÓRIO: "
				+ (isComCONTEUDOLINHAS() ? "SIM" : "NÃO") + "\n\t\tCRIAR TAGS COM NOMES COMPLETOS: "
				+ (isNomeTagCOMPLETO() ? "SIM" : "NÃO");

	}
}
