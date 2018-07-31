package com.xpto.cidades.dao;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.stereotype.Repository;

@Repository
@Transactional(readOnly = true)
public class CidadesRepositoryImpl implements CidadesRepositoryCustom {


    @PersistenceContext
    EntityManager entityManager;

	@Override
	public BigInteger buscaPelaColuna(String nome) {
		Query query = entityManager.createNativeQuery("SELECT count (DISTINCT em."+nome+" ) FROM Cidade em ");
        return (BigInteger) query.getSingleResult();
	}
	
}
