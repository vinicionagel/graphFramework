package com.texo.cidades.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.texo.cidades.dominio.Cidade;

import org.springframework.stereotype.Repository;

@Repository
@Transactional(readOnly = true)
public class CidadesRepositoryImpl implements CidadesRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Cidade> buscaPelaColunaETexto(String coluna, String texto) {
		Query query = entityManager.createNativeQuery("SELECT * FROM Cidade em WHERE em."+coluna+"='"+texto+"'",Cidade.class);
		return query.getResultList();
	}
	
}
