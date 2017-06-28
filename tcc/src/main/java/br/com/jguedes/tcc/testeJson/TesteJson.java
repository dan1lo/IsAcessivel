package br.com.jguedes.tcc.testeJson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.google.gson.Gson;

import ases.PontoVerificacao;
import br.com.jguedes.tcc.model.criterioavaliacao.Profundidade;

public class TesteJson {

	public static void main(String[] args) {
		switch (4) {
		case 1:
			testeJSonPrimefaces();

			break;
		case 2:
			testeJSon_GSON();
			break;
		case 3:
			testeComEnum();
			break;
		case 4:
			testeComRelatorioBean();
			break;
		default:
			testeJSonPrimefaces();
			testeJSon_GSON();
			break;
		}

	}

	private static void testeComRelatorioBean() {
		System.out.println("testeComRelatorioBean...............");

		RelatorioBean relatorio = new RelatorioBean();

		relatorio.setProfundidade(Profundidade.PAGINA);

		HashSet<PontoVerificacao> listaErrosP1 = new HashSet<PontoVerificacao>();

		PontoVerificacao pv = new PontoVerificacao();

		listaErrosP1.add(pv);

		relatorio.setListaErrosP1(listaErrosP1);

		Gson g = new Gson();

		String n = g.toJson(relatorio, RelatorioBean.class);

		RelatorioBean r = g.fromJson(n, RelatorioBean.class);

		FileWriter writer = null;

		FileReader reader = null;

		try {

			File file = new File(
					"/mnt/774ce448-05a2-4fc5-a56c-3261efee833f/Acadêmica/TCC2 - Or. Danilo Monteiro/testeasesweb/jsonteste.json");

			if (file.exists()) {
				file.delete();
			}

			writer = new FileWriter(file, true);

			g.toJson(relatorio, RelatorioBean.class, writer);

			writer.flush();
			writer.close();

			reader = new FileReader(file);

			r = g.fromJson(reader, RelatorioBean.class);

			reader.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		System.out.println(r.getLinkEvalCode());

	}

	private static void testeComEnum() {
		System.out.println("testeComEnum...............");

		Gson g = new Gson();

		String n = g.toJson(Profundidade.PAGINA, Profundidade.class);

		Profundidade r = g.fromJson(n, Profundidade.class);

		System.out.println(r);
	}

	/**
	 * 
	 */
	private static void testeJSon_GSON() {
		System.out.println("testeJSon_GSON...............");
		P p = new P("joao", 38);

		p.getAmigos().add(new P("josé", 20));
		p.getAmigos().add(new P("maria", 24));
		p.getAmigos().add(new P("rita", 40));

		P q = new P("quesia", 40);

		q.getAmigos().add(new P("josé", 20));
		q.getAmigos().add(new P("maria", 24));
		q.getAmigos().add(new P("rita", 40));

		List<P> c = new LinkedList<P>();

		c.add(p);
		c.add(q);
		// c.add(p.getAmigos().get(0));
		// c.add(p.getAmigos().get(1));

		Gson g = new Gson();

		String n = g.toJson(p, P.class);

		// P r = g.fromJson(n, P.class);

		System.out.format(n);
	}

	/**
	 * @param p
	 * @return
	 */
	private static void testeJSonPrimefaces() {
		System.out.println("testeJSonPrimefaces...............");

		P p = new P("joao", 38);

		p.getAmigos().add(new P("josé", 20));
		p.getAmigos().add(new P("maria", 24));
		p.getAmigos().add(new P("rita", 40));

		P q = new P("quesia", 40);

		q.getAmigos().add(new P("josé", 20));
		q.getAmigos().add(new P("maria", 24));
		q.getAmigos().add(new P("rita", 40));

		List<P> c = new LinkedList<P>();

		c.add(p);
		c.add(q);

		JSONObject j = null, k = null;

		// k = new JSONObject(c);

		k = new JSONObject(p);

		j = new JSONObject();

		try {
			j.put("cadastrados", k);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(j.toString());
		System.out.println(k.toString());
	}
}
