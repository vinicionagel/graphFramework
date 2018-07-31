package com.xpto.cidades.dao;

import java.math.BigInteger;
import java.util.List;

import com.xpto.cidades.model.Cidade;

public interface CidadesRepositoryCustom {

	BigInteger buscaPelaColuna(String nome);
	
	List<Cidade> buscaPelaColunaETexto(String coluna, String texto);
}
