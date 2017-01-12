package model;

import java.util.HashMap;

public class Polo {
	public Polo(String polo, HashMap<String, Cidade> cidades) {
		super();
		this.polo = polo;
		this.cidades = cidades;

	}

	private String polo;
	private int totalInscritos;
	private int totalConcluiram;
	private HashMap<String, Cidade> cidades;

	public String getPolo() {
		return this.polo;
	}

	public int getTotalInscritos() {
		return this.totalInscritos;
	}

	public int getTotalConcluiram() {
		return this.totalConcluiram;
	}

	public HashMap<String, Cidade> getCidades() {
		return this.cidades;
	}

	public void setPolo(String polo) {
		this.polo = polo;
	}

	public void setTotalInscritos(int totalInscritos) {
		this.totalInscritos = totalInscritos;
	}

	public void setTotalConcluiram(int totalConcluiram) {
		this.totalConcluiram = totalConcluiram;
	}

	public void setCidades(HashMap<String, Cidade> cidades) {
		this.cidades = cidades;
	}

	public void incrementaTotalInscritos() {
		this.totalInscritos++;
	}

	public void incrementaTotalConcluiram() {
		this.totalConcluiram++;
	}

}
