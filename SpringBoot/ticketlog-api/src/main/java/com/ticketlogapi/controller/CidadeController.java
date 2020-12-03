package com.ticketlogapi.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.entities.Estado;
import com.ticketlogapi.services.CidadeService;
import com.ticketlogapi.services.CustosService;
import com.ticketlogapi.services.EstadoService;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeService service;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CustosService custosService;

	@GetMapping
	public ResponseEntity<List<Cidade>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(path = "/{name}")
	public ResponseEntity<List<Cidade>> findByName(@PathVariable String name) {
		return ResponseEntity.ok().body(service.findByName(name));
	}

	@PostMapping(path = "/{estado}")
	public ResponseEntity<Cidade> addCidade(@PathVariable String estado, @RequestBody Cidade cidade) {
		Estado estados = service.cidadePorEstado(estado);
		cidade.setEstado(estados);
		service.addCidade(cidade);
		
		custosService.CalculaCusto(cidade.getId());
		estadoService.sumPopulacao(cidade.getEstadoId());
		custosService.CustoEstado(cidade.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Cidade> deleteById(@PathVariable int id) {
		if (service.cidadeRS(id)) {
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		} else {
			service.deleteCidade(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

}
