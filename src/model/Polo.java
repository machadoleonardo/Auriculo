package model;

import java.util.HashMap;

public class Polo {
	public Polo(String polo, HashMap<String, Cidade> cidadesIncritos, HashMap<String, Cidade> cidadesConcluiram) {
		super();
		this.polo = polo;
		this.cidadesIncritos = cidadesIncritos;
		this.cidadesConcluiram = cidadesConcluiram;
	}
	private String polo;
	private int totalInscritos;
	private int totalConcluiram;
	private HashMap<String,Cidade> cidadesIncritos;
	private HashMap<String,Cidade> cidadesConcluiram;

	public String getPolo() {
		return this.polo;
	}
	public int getTotalInscritos() {
		return this.totalInscritos;
	}
	public int getTotalConcluiram() {
		return this.totalConcluiram;
	}
	public HashMap<String, Cidade> getCidadesIncritos() {
		return this.cidadesIncritos;
	}
	public HashMap<String, Cidade> getCidadesConcluiram() {
		return this.cidadesConcluiram;
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
	public void setCidadesIncritos(HashMap<String, Cidade> cidadesIncritos) {
		this.cidadesIncritos = cidadesIncritos;
	}
	public void setCidadesConcluiram(HashMap<String, Cidade> cidadesConcluiram) {
		this.cidadesConcluiram = cidadesConcluiram;
	}
	public void incrementaTotalInscritos() {
		this.totalInscritos ++;
	}
	public void incrementaTotalConcluiram() {
		this.totalConcluiram ++;
	}

}
