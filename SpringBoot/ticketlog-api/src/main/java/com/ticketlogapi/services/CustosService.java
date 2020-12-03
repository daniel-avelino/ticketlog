package com.ticketlogapi.services;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.entities.Estado;

@Service
public class CustosService {

	private final double porPessoa = 123.45;

	private final double desconto = 0.123;

	private final int valorCorte = 50000;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private EstadoService estadoService;

	public void CalculaCusto(int id) {
		Cidade cidade = cidadeService.findById(id).orElseThrow(() -> new EntityNotFoundException());

		cidade.setCustoCidadeUS(verificaCustos(cidade));

		cidadeService.addCidade(cidade);
	}

	public void CustoEstado(int id) {

		Estado estado = estadoService.findById(id).orElseThrow(() -> new EntityNotFoundException());
		List<Cidade> list = cidadeService.findAll();
		double custoTotal = list.stream().filter(p -> p.getEstadoId().equals(id))
				.collect(Collectors.summingDouble(Cidade::getCustoCidadeUS));
		System.out.println(custoTotal);
		estado.setCustoEstado(custoTotal);
		estadoService.addEstado(estado);

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
