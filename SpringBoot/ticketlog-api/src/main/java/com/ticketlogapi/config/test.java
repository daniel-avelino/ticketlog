package com.ticketlogapi.config;

import java.util.Arrays;

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
@Profile("prod")
public class test implements CommandLineRunner {

	@Autowired
	private EstadoRepository estadoRep;

	@Autowired
	private CidadeRepository cidadeRep;

	@Override
	public void run(String... args) throws Exception {

		Estado estado1 = new Estado(null, "Santa Catarina");

		Estado estado2 = new Estado(null, "Rio Grande do Sul");

		Estado estado3 = new Estado(null, "Paraná");

		estadoRep.saveAll(Arrays.asList(estado1, estado2, estado3));

		Cidade cidade1 = new Cidade(null, "Balneário Cambóriu", estado1, 10000L);

		Cidade cidade2 = new Cidade(null, "Balneário Cambóriu", estado1, 150000L);

		cidadeRep.saveAll(Arrays.asList(cidade1, cidade2));

		RestTemplate template = new RestTemplate();

		String uri = "https://economia.awesomeapi.com.br/json/USD-BRL/";

		usd[] dollar = template.getForObject(uri, usd[].class);

	}

}
