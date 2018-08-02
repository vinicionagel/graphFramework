package com.texo.cidades.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectionUf {
	
	private String uf;
	
	@JsonProperty("numero cidades")
	private Long contador;
	
	public ProjectionUf(String uf, Long contador) {
		super();
		this.uf = uf;
		this.contador = contador;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getContador() {
		return contador;
	}

	public void setContador(Long contador) {
		this.contador = contador;
	}
	
	

}
