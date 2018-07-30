package com.xpto.cidades.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpto.cidades.dao.Cidades;
import com.xpto.cidades.leituracsv.LeituraCSV;
import com.xpto.cidades.model.Cidade;

@CrossOrigin("*")
@RestController("")
public class CidadesResource {

	@Autowired
	private Cidades cidades;
	
	@GetMapping("popular")
	public String popular(){
		new LeituraCSV().processaCVS().forEach(cidade -> cidades.save(cidade));
		return "Cidades Populadas";
	}
	
	@GetMapping("somenteCapitais")
	public List<Cidade> buscaSomenteCapitaisOrdenadas(){
		return cidades.buscaSomenteCapitaisOrdenadosPorNome();
	}
	
	@GetMapping("cidadesPorEstado")
	public List<Cidade> buscaCidadesPorEstadoSelecionado(@RequestParam String estado){
		return cidades.buscaCidadesPorEstadoSelecionado(estado);
	}
	 
	@GetMapping("quantidadeRegistros")
	public Long buscaQuantidadeDeRegistros(){
		return cidades.buscaQuantidadeDeRegistros();
	}
	
	@GetMapping("quantidadeCidadesPorEstado")
	public List<Cidade> buscaQuantidadeDeCidadesPorEstado(){
		return cidades.buscaQuantidadeCidadesPorEstado();
	}
	
	@GetMapping("cidadesPeloIdIbge")
	public List<Cidade> buscaCidadesPorEstadoSelecionado(@RequestParam Long ibgeId){
		return cidades.buscaCidadesPeloIdIbge(ibgeId);
	}
}