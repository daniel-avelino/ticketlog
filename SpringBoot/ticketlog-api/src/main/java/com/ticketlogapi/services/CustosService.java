package com.ticketlogapi.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ticketlogapi.entities.Cidade;

@Service
public class CustosService {

	@Autowired
	private CidadeService cidadeService;

	private final double porPessoa = 123.45;

	private final double desconto = 0.123;

	private final int valorCorte = 50000;

	public void CalculaCusto(int id) {
		Cidade cidade = cidadeService.findById(id).orElseThrow(() -> new EntityNotFoundException());

		cidade.setCustoCidadeUS(verificaCustos(cidade));

		cidadeService.addCidade(cidade);
	}

	public Double verificaCustos(Cidade cidade) {
		double custo = 0.0;

		custo = USDservices(cidade.getPopulacao() * porPessoa);

		if (cidade.getPopulacao() >= valorCorte) {
			int populacaoDesconto = cidade.getPopulacao() - valorCorte;
			custo += USDservices(((populacaoDesconto * porPessoa) - (populacaoDesconto * desconto * porPessoa)));
		}

		return custo;
	}

	public Double USDservices(Double valueUSD) {
		RestTemplate template = new RestTemplate();

		String uri = "https://economia.awesomeapi.com.br/json/USD-BRL/";

		usd[] dollar = template.getForObject(uri, usd[].class);
		Double valorConvertido = valueUSD * dollar[0].getAsk();

		return valorConvertido;
	}

}
