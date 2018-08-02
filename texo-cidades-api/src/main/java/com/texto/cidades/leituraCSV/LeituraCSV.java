package com.texto.cidades.leituraCSV;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.texo.cidades.dominio.Cidade;

@Service
public class LeituraCSV {
	
	private static final String VIRGULA = ",";
	private static final String CAMINHO = "C:\\Users\\vinicio\\Documents\\workspace-sts-3.9.4.RELEASE\\texo-cidades-api\\base de dados\\Trabalho Java - Cidades.csv";
	
	
	public List<Cidade> processaCVS() {
	    List<Cidade> inputList = new ArrayList<Cidade>();
	    try{
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(CAMINHO)));
	    	inputList = reader.lines().skip(1).map((line) ->{
	    		String[] dados = line.split(VIRGULA);
	    		Cidade cidade = new Cidade();
	    		cidade.setIbgeId(Long.parseLong(dados[0]));
	    		cidade.setUf(dados[1]);
	    		cidade.setNome(dados[2]);
	    		cidade.setCapital(Boolean.parseBoolean(dados[3]));
	    		cidade.setLongitude(new BigDecimal(dados[4]));
	    		cidade.setLatitude(new BigDecimal(dados[5]));
	    		cidade.setNomeSemAcentos(dados[6]);
	    		cidade.setNomeAlternativo(dados[7]);
	    		cidade.setMicroRegiao(dados[8]);
	    		cidade.setGrandeRegiao(dados[9]);
	    		return cidade;
	    	}).parallel().collect(Collectors.toList());
	    	reader.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    return inputList;
	}
	
	
}
