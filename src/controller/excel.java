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
		Workbook workbook = Workbook.getWorkbook(new File("todos.xls"), ws );
		Sheet sheet = workbook.getSheet(0);
		int linhas = sheet.getRows();
		System.out.println("Iniciando a leitura da planilha XLS contendo "+linhas+":");
		HashMap<String,Regiao> regioes = new HashMap<String,Regiao>();
		for(int i = 0; i < linhas; i++){
			String stRegiao 	= sheet.getCell(0, i).getContents();
			String stSituacao = sheet.getCell(1, i).getContents();
			String stPolo 	= sheet.getCell(2, i).getContents();
			String stCidade 	= sheet.getCell(3, i).getContents();
			String stEstado 	= sheet.getCell(4, i).getContents();
			if(regioes.containsKey(stRegiao.toLowerCase())){
				Regiao regiao = regioes.get(stRegiao.toLowerCase());
				if (regiao.getPolos().containsKey(stPolo.toLowerCase())){
					Polo polo = regiao.getPolos().get(stPolo.toLowerCase());
					if(stSituacao.toLowerCase().equals("inscritos")){
						if(polo.getCidadesIncritos().containsKey(stCidade.toLowerCase())){
							polo.getCidadesIncritos().get(stCidade.toLowerCase()).incrementaTotalInscritos();
							polo.incrementaTotalInscritos();
							regiao.incrementaTotalInscritos();
						}else{
							Cidade cidade = new Cidade(stCidade,stEstado);
							cidade.incrementaTotalInscritos();
							polo.incrementaTotalInscritos();
							polo.getCidadesIncritos().put(stCidade.toLowerCase(), cidade);
							regiao.incrementaTotalInscritos();
						}

					}else if (stSituacao.toLowerCase().equals("concluiram")){
						if(polo.getCidadesConcluiram().containsKey(stCidade.toLowerCase())){
							polo.getCidadesConcluiram().get(stCidade.toLowerCase()).incrementaTotalConcluiram();
							polo.incrementaTotalConcluiram();
							regiao.incrementaTotalConcluiram();;
						}else{
							Cidade cidade = new Cidade(stCidade,stEstado);
							cidade.incrementaTotalConcluiram();
							polo.incrementaTotalConcluiram();
							polo.getCidadesConcluiram().put(stCidade.toLowerCase(), cidade);
							regiao.incrementaTotalConcluiram();
						}
					}

				}else{
					Polo polo = new Polo(stPolo, new HashMap<>(), new HashMap<>());
					if(stSituacao.toLowerCase().equals("inscritos")){
						Cidade cidade = new Cidade(stCidade,stEstado);
						cidade.incrementaTotalInscritos();
						polo.incrementaTotalInscritos();
						polo.getCidadesIncritos().put(stCidade.toLowerCase(), cidade);
						regiao.getPolos().put(stPolo.toLowerCase(), polo);
						regiao.incrementaTotalInscritos();
					}else if (stSituacao.toLowerCase().equals("concluiram")){
						Cidade cidade = new Cidade(stCidade,stEstado);
						cidade.incrementaTotalConcluiram();
						polo.incrementaTotalConcluiram();
						polo.getCidadesConcluiram().put(stCidade.toLowerCase(), cidade);
						regiao.getPolos().put(stPolo.toLowerCase(), polo);
						regiao.incrementaTotalConcluiram();
					}

				}

			}else{
				Regiao regiao = new Regiao(stRegiao.toLowerCase(), new HashMap<String, Polo>());
				Polo polo = new Polo(stPolo, new HashMap<>(), new HashMap<>());


				if(stSituacao.toLowerCase().equals("inscritos")){
					Cidade cidade = new Cidade(stCidade,stEstado);
					cidade.incrementaTotalInscritos();
					polo.incrementaTotalInscritos();
					polo.getCidadesIncritos().put(stCidade.toLowerCase(), cidade);
					regiao.getPolos().put(stPolo.toLowerCase(), polo);
					regiao.incrementaTotalInscritos();
				}else if (stSituacao.toLowerCase().equals("concluiram")){
					Cidade cidade = new Cidade(stCidade,stEstado);
					cidade.incrementaTotalConcluiram();
					polo.incrementaTotalConcluiram();
					polo.getCidadesConcluiram().put(stCidade.toLowerCase(), cidade);
					regiao.getPolos().put(stPolo.toLowerCase(), polo);
					regiao.incrementaTotalConcluiram();
				}
				regioes.put(stRegiao.toLowerCase(), regiao);
			}

		}
	workbook.close();
	regioes.values();
	}

}
