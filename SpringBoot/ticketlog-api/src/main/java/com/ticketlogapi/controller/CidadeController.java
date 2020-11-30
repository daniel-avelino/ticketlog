package com.ticketlogapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.services.CidadeService;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeService service;

	@GetMapping
	public ResponseEntity<List<Cidade>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Cidade>> findById(@PathVariable int id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
}
