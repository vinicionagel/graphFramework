package com.texo.cidades.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texo.cidades.dao.CidadesRepository;
import com.texo.cidades.dominio.Cidade;



@Service
public class CidadeService {
	
	@Autowired
	private CidadesRepository cidades;
	
	public Cidade adicionarCidade(Cidade cidade) {
		return cidades.save(cidade);
	}
	
	public void removerCidade(Cidade cidade) {
		cidades.delete(cidade);
	}
	

    public void saveAll(List<Cidade> cidadesAdicionar) {
        cidades.saveAll(cidadesAdicionar);
    }

}
