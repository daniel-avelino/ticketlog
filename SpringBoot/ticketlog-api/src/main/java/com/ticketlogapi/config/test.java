package com.ticketlogapi.config;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.ticketlogapi.Repositories.CidadeRepository;
import com.ticketlogapi.Repositories.EstadoRepository;
import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.entities.Estado;
import com.ticketlogapi.services.usd;

@Configuration
@Profile("dev")
public class test implements CommandLineRunner {

	@Autowired
	private EstadoRepository estadoRep;

	@Autowired
	private CidadeRepository cidadeRep;

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Estado estado1 = new Estado(null, "Santa Catarina");
		 * 
		 * Estado estado2 = new Estado(null, "Rio Grande do Sul");
		 * 
		 * Estado estado3 = new Estado(null, "Paraná");
		 * 
		 * estadoRep.saveAll(Arrays.asList(estado1, estado2, estado3));
		 * 
		 * Cidade cidade1 = new Cidade(null, "Balneário Cambóriu", estado1, 10000);
		 * 
		 * Cidade cidade2 = new Cidade(null, "Joinville", estado1, 150000);
		 * 
		 * Cidade cidade3 = new Cidade(null, "Londrina", estado3, 77777);
		 * 
		 * Cidade cidade4 = new Cidade(null, "Porto Alegre", estado2, 99999);
		 * 
		 * cidadeRep.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
		 * 
		 * RestTemplate template = new RestTemplate();
		 * 
		 * String uri = "https://economia.awesomeapi.com.br/json/USD-BRL/";
		 * 
		 * usd[] dollar = template.getForObject(uri, usd[].class);
		 */
	}

}
