package br.com.jguedes.tcc.view.relatoriodeurlmb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import ases.RelatorioDaUrl;
import ases.RelatorioTableModel;
import br.com.jguedes.tcc.model.User;
import br.com.jguedes.tcc.support.Fachada;
import br.com.jguedes.tcc.util.ContextoDeAvaliacao;

@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Named("relUrl")
public class RelatorioDeURLMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5728988926083510364L;

	@Autowired
	private Fachada fachada;

	private User user;

	private String titulo;

	private RelatorioDaUrl relatorio;

	private String none = "inline";

	private String titOcorTotais, titP1, titP2, titP3, titErrosP1, titErrosP2, titErrosP3, titAvisosP1, titAvisosP2,
			titAvisosP3;

	private ContextoDeAvaliacao contexto;

	@PostConstruct
	private void init() {

		setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		setContexto(this.user.getContexto());

		this.setRelatorio(fachada.getRelatorioDeURL(contexto));

		this.setTitulo("IsAcessível - Relatório de avaliação de acessibilidade no padrão "
				+ this.user.getContexto().getCriterio().getPadraoAcessibilidade());

		this.setTitOcorTotais((relatorio.getErrosPrioridade1() + relatorio.getAvisosPrioridade1()
				+ relatorio.getErrosPrioridade2() + relatorio.getAvisosPrioridade2() + relatorio.getErrosPrioridade3()
				+ relatorio.getAvisosPrioridade3()) + " ocorrências ("
				+ (relatorio.getErrosPrioridade1() + relatorio.getErrosPrioridade2() + relatorio.getErrosPrioridade3())
				+ " erros | " + (relatorio.getAvisosPrioridade1() + relatorio.getAvisosPrioridade2()
						+ relatorio.getAvisosPrioridade3())
				+ " avisos)");

		this.setTitP1("Prioridade 1: " + (relatorio.getErrosPrioridade1() + relatorio.getAvisosPrioridade1())
				+ " ocorrências");

		this.setTitP2("Prioridade 2: " + (relatorio.getErrosPrioridade2() + relatorio.getAvisosPrioridade2())
				+ " ocorrências");

		this.setTitP3("Prioridade 3: " + (relatorio.getErrosPrioridade3() + relatorio.getAvisosPrioridade3())
				+ " ocorrências");

		this.setTitErrosP1("Erros P1: " + relatorio.getErrosPrioridade1() + " ocorrências");
		this.setTitAvisosP1("Avisos P1: " + relatorio.getAvisosPrioridade1() + " ocorrências");

		this.setTitErrosP2("Erros P2: " + relatorio.getErrosPrioridade2() + " ocorrências");
		this.setTitAvisosP2("Avisos P2: " + relatorio.getAvisosPrioridade2() + " ocorrências");

		this.setTitErrosP3("Erros P3: " + relatorio.getErrosPrioridade3() + " ocorrências");
		this.setTitAvisosP3("Avisos P3: " + relatorio.getAvisosPrioridade3() + " ocorrências");

	}

	public void setContexto(ContextoDeAvaliacao contexto) {
		this.contexto = contexto;
	}

	public String getTitOcorTotais() {
		return titOcorTotais;
	}

	private void setTitOcorTotais(String titOcorTotais) {
		this.titOcorTotais = titOcorTotais;
	}

	public String getTitP1() {
		return titP1;
	}

	public String getTitP2() {
		return titP2;
	}

	public String getTitP3() {
		return titP3;
	}

	private void setTitP1(String titP1) {
		this.titP1 = titP1;
	}

	private void setTitP2(String titP2) {
		this.titP2 = titP2;
	}

	private void setTitP3(String titP3) {
		this.titP3 = titP3;
	}

	public String getTitErrosP2() {
		return titErrosP2;
	}

	private void setTitErrosP2(String titErrosP2) {
		this.titErrosP2 = titErrosP2;
	}

	public String getTitErrosP3() {
		return titErrosP3;
	}

	private void setTitErrosP3(String titErrosP3) {
		this.titErrosP3 = titErrosP3;
	}

	public String getTitAvisosP1() {
		return titAvisosP1;
	}

	private void setTitAvisosP1(String titAvisosP1) {
		this.titAvisosP1 = titAvisosP1;
	}

	public String getTitAvisosP2() {
		return titAvisosP2;
	}

	private void setTitAvisosP2(String titAvisosP2) {
		this.titAvisosP2 = titAvisosP2;
	}

	public String getTitAvisosP3() {
		return titAvisosP3;
	}

	private void setTitAvisosP3(String titAvisosP3) {
		this.titAvisosP3 = titAvisosP3;
	}

	public String getTitErrosP1() {
		return titErrosP1;
	}

	private void setTitErrosP1(String titErrosP1) {
		this.titErrosP1 = titErrosP1;
	}

	public String getNone() {
		return none;
	}

	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	private void setRelatorio(RelatorioDaUrl relatorio) {
		this.relatorio = relatorio;
	}

	public RelatorioDaUrl getRelatorio() {
		return relatorio;
	}

	public List<ErroOrAviso> errosP1() {

		return listarErrosOrAvisos(new RelatorioTableModel(relatorio.getListaErrosP1(), "ErroP1"));
	}

	public List<ErroOrAviso> errosP2() {

		return listarErrosOrAvisos(new RelatorioTableModel(relatorio.getListaErrosP2(), "ErroP2"));
	}

	public List<ErroOrAviso> errosP3() {

		return listarErrosOrAvisos(new RelatorioTableModel(relatorio.getListaErrosP3(), "ErroP3"));
	}

	public List<ErroOrAviso> avisosP1() {

		return listarErrosOrAvisos(new RelatorioTableModel(relatorio.getListaAvisosP1(), "AvisoP1"));

	}

	public List<ErroOrAviso> avisosP2() {

		return listarErrosOrAvisos(new RelatorioTableModel(relatorio.getListaAvisosP2(), "AvisoP2"));
	}

	public List<ErroOrAviso> avisosP3() {

		return listarErrosOrAvisos(new RelatorioTableModel(relatorio.getListaAvisosP3(), "AvisoP3"));
	}

	private List<ErroOrAviso> listarErrosOrAvisos(RelatorioTableModel rtm) {

		List<ErroOrAviso> list = null;

		ErroOrAviso erroOrAviso = null;

		for (int row = 0; row < rtm.getRowCount(); row++) {

			if (list == null) {

				list = new ArrayList<ErroOrAviso>();

			}

			erroOrAviso = new ErroOrAviso();

			String regra = String.valueOf(rtm.getValueAt(row, 0)).concat(": ")
					.concat(String.valueOf(rtm.getValueAt(row, 1)));

			erroOrAviso.setRegra(regra);

			erroOrAviso.setOcorrencias(String.valueOf(rtm.getValueAt(row, 2)));

			erroOrAviso.setLinhas(String.valueOf(rtm.getValueAt(row, 3)));

			list.add(erroOrAviso);

		}

		return list;

	}

	public void setUser(User user) {

		this.user = user;

	}

}
