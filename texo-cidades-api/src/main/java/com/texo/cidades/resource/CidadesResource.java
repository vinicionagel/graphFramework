package com.texo.cidades.resource;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.texo.cidades.dao.CidadesRepository;
import com.texo.cidades.dominio.Cidade;
import com.texo.cidades.dominio.CidadeUtil;
import com.texo.cidades.dominio.ProjectionUf;
import com.texo.cidades.service.CidadeService;
import com.texto.cidades.leituraCSV.LeituraCSV;

@CrossOrigin("*")
@RestController("")
public class CidadesResource {

	@Autowired
	private CidadesRepository cidades;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@GetMapping("popular")
	public void popular(){
		cidades.saveAll(new LeituraCSV().processaCVS());
	}
	
	@GetMapping(value = "somenteCapitaisOrdernadas")
	public List<Cidade> buscaSomenteCapitaisOrdenadas(){
		return cidades.buscaSomenteCapitaisOrdenadosPorNome();
	}
	
	@GetMapping("ufComMaisEMenosCidades")
	public List<ProjectionUf> buscaEstadosComMaisCidades(){
		List<ProjectionUf> cidadeMaisEMenosMunicipios = new ArrayList<>();
		cidadeMaisEMenosMunicipios.addAll(cidades.buscaEstadoComMaisCidades(PageRequest.of(0,1)));
		cidadeMaisEMenosMunicipios.addAll(cidades.buscaEstadoComMenosCidades(PageRequest.of(0,1)));
		return cidadeMaisEMenosMunicipios;
	}
	
	
	@DeleteMapping(value = "removerCidade")
	public void removerCidade(@RequestBody @Valid Cidade cidade) {
		cidadeService.removerCidade(cidade);	
	}
	
	@GetMapping(value = "totalRegistros")
	public HashMap<String, Long> buscaTotalRegistros() {
		HashMap<String, Long> map = new HashMap<>();
		map.put("TotalRegistros",cidades.buscaQuantidadeTotalRegistros());
		return map;
	}
	
	@GetMapping(value = "buscaColunaETexto")
	public CidadeUtil buscaPorFiltroEspecifico(@RequestParam String coluna, String texto){
		CidadeUtil cidadesEQuantidade = new CidadeUtil();
		List<Cidade> cidadess = new ArrayList<>(cidades.buscaPelaColunaETexto(coluna, texto));
		cidadesEQuantidade.setQuantidadeCidade((long) cidadess.size());
		cidadesEQuantidade.setCidades(cidadess);
		return cidadesEQuantidade;
	}
	
}