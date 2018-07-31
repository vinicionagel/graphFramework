package com.xpto.cidades.resource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xpto.cidades.dao.CidadesRepository;
import com.xpto.cidades.leituracsv.LeituraCSV;
import com.xpto.cidades.model.Cidade;
import com.xpto.cidades.service.CidadeService;
import com.xpto.cidades.util.CalculoDistanciaUtil;

@CrossOrigin("*")
@RestController("")
public class CidadesResource {

	@Autowired
	private CidadesRepository cidades;
	
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
	
	@GetMapping("cidadesPorColunaEStringFilter")
	public List<Cidade> buscaCidadesPorColuna(@RequestParam String coluna, String filter){
		return cidades.buscaPelaColunaETexto(coluna, filter);
	}
	
	@GetMapping("cidadesPorColuna")
	public BigInteger buscaCidadesPorColuna(@RequestParam String coluna){
		return cidades.buscaPelaColuna(coluna);
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
	
	@GetMapping(value = "calculaDistanciaCidadeMaisLonges") 
	public Cidade calculaDistanciaCidadeMaisLonges() {
		Cidade cidadeMaiorDistancia = new Cidade();
		List<Cidade> cidadesFonte = cidades.findAll();
		HashSet<Cidade> cidadesDestino = new HashSet<>(cidades.findAll());
		for (Cidade cidadeFonte: cidadesFonte) {
			for (Cidade cidadeDestino: cidadesDestino) {
				double distanciaEntreFonteEDestino = CalculoDistanciaUtil.distanciaEmKM(cidadeFonte.getLatitude().doubleValue(), 
						cidadeFonte.getLongitude().doubleValue(), 
						cidadeDestino.getLatitude().doubleValue(), 
						cidadeDestino.getLongitude().doubleValue());
				if (distanciaEntreFonteEDestino > cidadeMaiorDistancia.getDistanciaEntreDestino()) {
					cidadeMaiorDistancia = cidadeFonte;
					cidadeMaiorDistancia.setCidadeDestino(cidadeDestino);
					cidadeMaiorDistancia.setDistanciaEntreDestino(distanciaEntreFonteEDestino);
				}
			}
			cidadesDestino.remove(cidadeFonte);
		}
		return cidadeMaiorDistancia;	
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