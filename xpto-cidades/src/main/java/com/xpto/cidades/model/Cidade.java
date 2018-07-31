package com.xpto.cidades.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Cidade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ibge_id")
	private Long ibgeId;
	
	@Column(name = "uf")
	private String uf;
	
	@Column(name = "name")
	private String nome;
	
	@Column(name = "capital")
	private boolean capital;
	
	@Column(name = "lon")
	private BigDecimal longitude;
	
	@Column(name = "lat")
	private BigDecimal latitude;
	
	@Column(name = "no_accents")
	private String nomeSemAcentos;
	
	@Column(name = "alternative_names")
	private String nomeAlternativo;
	
	@Column(name = "microregion")
	private String microRegiao;
	
	@Column(name = "mesoregion")
	private String grandeRegiao;
	
	@Transient
	private Cidade cidadeDestino;
	
	@Transient
	private double distanciaEntreDestino;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getNomeSemAcentos() {
		return nomeSemAcentos;
	}

	public void setNomeSemAcentos(String nomeSemAcentos) {
		this.nomeSemAcentos = nomeSemAcentos;
	}

	public String getNomeAlternativo() {
		return nomeAlternativo;
	}

	public void setNomeAlternativo(String nomeAlternativo) {
		this.nomeAlternativo = nomeAlternativo;
	}

	public String getMicroRegiao() {
		return microRegiao;
	}

	public void setMicroRegiao(String microRegiao) {
		this.microRegiao = microRegiao;
	}

	public String getGrandeRegiao() {
		return grandeRegiao;
	}

	public void setGrandeRegiao(String grandeRegiao) {
		this.grandeRegiao = grandeRegiao;
	}
	
	public Cidade getCidadeDestino() {
		return cidadeDestino;
	}

	public void setCidadeDestino(Cidade cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}
	
	public double getDistanciaEntreDestino() {
		return distanciaEntreDestino;
	}

	public void setDistanciaEntreDestino(double distanciaEntreDestino) {
		this.distanciaEntreDestino = distanciaEntreDestino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cidade [id=");
		builder.append(id);
		builder.append(", ibgeId=");
		builder.append(ibgeId);
		builder.append(", uf=");
		builder.append(uf);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", capital=");
		builder.append(capital);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", nomeSemAcentos=");
		builder.append(nomeSemAcentos);
		builder.append(", nomeAlternativo=");
		builder.append(nomeAlternativo);
		builder.append(", microRegiao=");
		builder.append(microRegiao);
		builder.append(", grandeRegiao=");
		builder.append(grandeRegiao);
		builder.append("]");
		builder.append(", Destino=");
		builder.append(cidadeDestino.getNome());
		builder.append("]");
		builder.append(", KMS=");
		builder.append(cidadeDestino.getDistanciaEntreDestino());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
