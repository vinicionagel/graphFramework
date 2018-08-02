package com.texo.cidades.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.texo.cidades.dominio.Cidade;
import com.texo.cidades.dominio.ProjectionUf;



@Repository
public interface CidadesRepository extends JpaRepository<Cidade, Long>, CidadesRepositoryCustom {

	@Query(" FROM Cidade cidade WHERE cidade.capital = true order by cidade.nome")
    public List<Cidade> buscaSomenteCapitaisOrdenadosPorNome();
	
	
	@Query("SELECT new com.texo.cidades.dominio.ProjectionUf(max(cidade.uf), count(cidade.uf)) FROM Cidade cidade Group By cidade.uf Order By count(cidade.uf) desc ")
	public List<ProjectionUf> buscaEstadoComMaisCidades(Pageable pageable);
	
	@Query("SELECT new com.texo.cidades.dominio.ProjectionUf(min(cidade.uf), count(cidade.uf)) FROM Cidade cidade Group By cidade.uf Order By count(cidade.uf) ")
	public List<ProjectionUf> buscaEstadoComMenosCidades(Pageable pageable);
	
	@Query("SELECT count(cidade) FROM Cidade cidade")
	public Long buscaQuantidadeTotalRegistros();
	
	
}
