package model;

public class Cidade {
	public Cidade(String cidade, String estado) {
		this.cidade = cidade;
		this.estado = estado;

	}
	private String cidade;
	private String estado;
	private int totalInscritos;
	private int totalConcluiram;


	public String getCidade() {
		return this.cidade;
	}
	public String getEstado() {
		return this.estado;
	}
	public int getTotalInscritos() {
		return this.totalInscritos;
	}
	public int getTotalConcluiram() {
		return this.totalConcluiram;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void incrementaTotalInscritos() {
		this.totalInscritos ++;
	}
	public void incrementaTotalConcluiram() {
		this.totalConcluiram ++;
	}

}
