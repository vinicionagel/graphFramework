package com.xpto.cidades.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpto.cidades.dao.Cidades;
import com.xpto.cidades.model.Cidade;

@Service
public class CidadeService {
	
	@Autowired
	private Cidades cidades;
	
	public Cidade adicionarCidade(Cidade cidade) {
		return cidades.save(cidade);
	}
	
	public String removerCidade(Cidade cidade) {
		cidades.delete(cidade);
		return "Cidade Removida";
	}

}