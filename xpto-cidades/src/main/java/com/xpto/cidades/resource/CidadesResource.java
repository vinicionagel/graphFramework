package com.xpto.cidades.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xpto.cidades.dao.Cidades;
import com.xpto.cidades.leituracsv.LeituraCSV;
import com.xpto.cidades.model.Cidade;
import com.xpto.cidades.service.CidadeService;

@CrossOrigin("*")
@RestController("")
public class CidadesResource {

	@Autowired
	private Cidades cidades;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("popular")
	public String popular(){
		new LeituraCSV().processaCVS().forEach(cidade -> cidades.save(cidade));
		return "Cidades Populadas";
	}
	
	@GetMapping(value = "somenteCapitais", produces = "application/json; charset=UTF-8")
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
	
	@GetMapping("ufComMaisEMenosCidades")
	public List<Cidade> buscaEstadosComMaisCidades(){
		List<Cidade> cidadeMaisEMenosMunicipios = new ArrayList<>();
		cidadeMaisEMenosMunicipios.addAll(cidades.buscaEstadoComMaisCidades(PageRequest.of(0,1)));
		cidadeMaisEMenosMunicipios.addAll(cidades.buscaEstadoComMenosCidades(PageRequest.of(0,1)));
		return cidadeMaisEMenosMunicipios;
	}
	
	@PostMapping("novaCidade")
	public Cidade adicionarCidade(@RequestBody @Valid Cidade cidade) {
		return cidadeService.adicionarCidade(cidade);	
	}
	
	@PostMapping(value = "removerCidade")
	public String removerCidade(@RequestBody @Valid Cidade cidade) {
		return cidadeService.removerCidade(cidade);	
	}
	
	
	
}