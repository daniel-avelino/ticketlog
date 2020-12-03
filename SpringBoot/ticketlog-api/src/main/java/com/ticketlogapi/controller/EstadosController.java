package com.ticketlogapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.entities.Estado;
import com.ticketlogapi.services.CidadeService;
import com.ticketlogapi.services.CustosService;
import com.ticketlogapi.services.EstadoService;

@RestController
@RequestMapping(path = "/estados")
public class EstadosController {

	@Autowired
	private EstadoService services;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private CustosService custosService;

	@GetMapping
	public ResponseEntity<List<Estado>> findAll() {
		return ResponseEntity.ok().body(services.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Estado>> findById(@PathVariable int id) {
		custosService.CustoEstado(id);
		return ResponseEntity.ok().body(services.findById(id));
	}

	@GetMapping(path = "/{id}/cidades")
	public ResponseEntity<Set<Cidade>> findCidades(@PathVariable int id) {
		Estado estado = services.findById(id).orElseThrow(() -> new EntityNotFoundException());
		custosService.CalculaCusto(id);
		custosService.CustoEstado(estado.getId());
		return ResponseEntity.ok().body(services.findAllByEstado(id));
	}

	@PostMapping
	public ResponseEntity<Estado> addEstado(@RequestBody Estado estado) {
		services.addEstado(estado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "{id}/cidades")
	public ResponseEntity<Cidade> addCidade(@PathVariable int id, @RequestBody Cidade cidade) {
		Estado estado = services.findById(id).orElseThrow(() -> new EntityNotFoundException());

		if (cidadeService.cidadeDuplicada(cidade.getCidade())) {
			return new ResponseEntity<Cidade>(HttpStatus.NOT_ACCEPTABLE);
		} else {

			cidade.setEstado(estado);
			cidadeService.addCidade(cidade);
			custosService.CalculaCusto(cidade.getId());
			services.sumPopulacao(cidade.getEstadoId());

			custosService.CustoEstado(estado.getId());

			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

}
