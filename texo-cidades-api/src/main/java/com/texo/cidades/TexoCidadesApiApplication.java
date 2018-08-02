package com.texo.cidades;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.texo.cidades.service.CidadeService;
import com.texto.cidades.leituraCSV.LeituraCSV;

@SpringBootApplication
public class TexoCidadesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TexoCidadesApiApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(CidadeService cidadeService) {
		return args -> {
			cidadeService.saveAll(new LeituraCSV().processaCVS());
		};
	}
}
