package com.texo.cidades.dao;

import java.util.List;

import com.texo.cidades.dominio.Cidade;


public interface CidadesRepositoryCustom {
	
	List<Cidade> buscaPelaColunaETexto(String coluna, String texto);
}
