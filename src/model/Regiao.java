package model;

import java.util.HashMap;

public class Regiao {
	public Regiao(String regiao, HashMap<String, Polo> polos) {
		this.regiao = regiao;
		this.polos = polos;

	}
	private String regiao;
	private HashMap<String,Polo> polos;
	private int totalInscritos;
	private int totalConcluiram;

	public String getRegiao() {
		return this.regiao;
	}
	public HashMap<String, Polo> getPolos() {
		return this.polos;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public void setPolos(HashMap<String, Polo> polos) {
		this.polos = polos;
	}
	public int getTotalInscritos() {
		return this.totalInscritos;
	}
	public int getTotalConcluiram() {
		return this.totalConcluiram;
	}
	public void setTotalInscritos(int totalInscritos) {
		this.totalInscritos = totalInscritos;
	}
	public void setTotalConcluiram(int totalConcluiram) {
		this.totalConcluiram = totalConcluiram;
	}
	public void incrementaTotalInscritos() {
		this.totalInscritos ++;
	}
	public void incrementaTotalConcluiram() {
		this.totalConcluiram ++;
	}
}
