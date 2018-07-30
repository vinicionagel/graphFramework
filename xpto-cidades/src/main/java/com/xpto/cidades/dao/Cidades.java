package com.xpto.cidades.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xpto.cidades.model.Cidade;



public interface Cidades extends JpaRepository<Cidade, Long> {

	@Query(" FROM Cidade cidade WHERE cidade.capital = true order by cidade.nome")
    public List<Cidade> buscaSomenteCapitaisOrdenadosPorNome();
	
	@Query("SELECT count(cidade) FROM Cidade")
    public Long buscaQuantidadeDeRegistros();
	
	@Query("SELECT cidade.nome FROM Cidade cidade WHERE lower(cidade.uf) = lower(?1)")
	public List<Cidade> buscaCidadesPorEstadoSelecionado(String estado);
	
	@Query("SELECT count(cidade), cidade.uf FROM Cidade cidade  group by cidade.uf ")
	public List<Cidade> buscaQuantidadeCidadesPorEstado();
	
	@Query(" FROM Cidade cidade WHERE cidade.ibgeId = ?1")
	public List<Cidade> buscaCidadesPeloIdIbge(Long ibgeId);
	
	@Query("SELECT max(cidade.uf), count(cidade.uf) FROM Cidade cidade Group By cidade.uf Order By count(cidade.uf) desc ")
	public List<Cidade> buscaEstadoComMaisCidades(Pageable pageable);
	
	@Query("SELECT min(cidade.uf), count(cidade.uf) FROM Cidade cidade Group By cidade.uf Order By count(cidade.uf) ")
	public List<Cidade> buscaEstadoComMenosCidades(Pageable pageable);
	
}
