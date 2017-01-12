package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import model.Cidade;
import model.Polo;
import model.Regiao;

public class excel {
	public static void main(String[] args) throws IOException, BiffException {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setEncoding("CP1250");
		Workbook workbook = Workbook.getWorkbook(new File("todos.xls"), ws);
		Sheet sheet = workbook.getSheet(0);
		int linhas = sheet.getRows();
		System.out.println("Iniciando a leitura da planilha XLS contendo " + linhas + ":");
		HashMap<String, Regiao> regioes = new HashMap<>();
		for (int i = 0; i < linhas; i++) {
			String stRegiao = sheet.getCell(0, i).getContents();
			String stSituacao = sheet.getCell(1, i).getContents();
			String stPolo = sheet.getCell(2, i).getContents();
			String stCidade = sheet.getCell(3, i).getContents();
			String stEstado = sheet.getCell(4, i).getContents();
			Regiao regiao = regioes.get(stRegiao.toLowerCase());
			Polo polo = null;
			Cidade cidade = null;
			if (regioes.containsKey(stRegiao.toLowerCase())) {
				regiao = regioes.get(stRegiao.toLowerCase());
				if (!regiao.getPolos().containsKey(stPolo.toLowerCase())) {
					polo = new Polo(stPolo, new HashMap<>());
					regiao.getPolos().put(stPolo.toLowerCase(), polo);
				} else {
					polo = regiao.getPolos().get(stPolo.toLowerCase());
				}
				if (!polo.getCidades().containsKey(stCidade.toLowerCase())) {
					cidade = new Cidade(stCidade, stEstado);
					polo.getCidades().put(stCidade.toLowerCase(), cidade);
				} else {
					cidade = polo.getCidades().get(stCidade.toLowerCase());
				}
			} else {
				regiao = new Regiao(stRegiao.toLowerCase(), new HashMap<String, Polo>());
				polo = new Polo(stPolo, new HashMap<>());
				cidade = new Cidade(stCidade, stEstado);
				polo.getCidades().put(stCidade.toLowerCase(), cidade);
				regiao.getPolos().put(stPolo.toLowerCase(), polo);
			}
			if (stSituacao.toLowerCase().equals("inscritos")) {
				cidade.incrementaTotalInscritos();
				polo.incrementaTotalInscritos();
				regiao.incrementaTotalInscritos();
			} else if (stSituacao.toLowerCase().equals("concluiram")) {
				cidade.incrementaTotalConcluiram();
				polo.incrementaTotalConcluiram();
				regiao.incrementaTotalConcluiram();
			}
			regioes.put(stRegiao.toLowerCase(), regiao);

		}
		workbook.close();
		regioes.values();
	}

}
