package br.com.jguedes.tcc.testeJson;

import java.util.LinkedList;
import java.util.List;

public class P {
	private String nome;
	private int idade;
	private List<P> amigos;

	public P() {
	}

	public P(String nome, int idade) {
		setNome(nome);
		setIdade(idade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public List<P> getAmigos() {
		if (amigos == null) {
			amigos = new LinkedList<P>();
		}
		return amigos;
	}

	public void setAmigos(List<P> amigos) {
		this.amigos = amigos;
	}
}