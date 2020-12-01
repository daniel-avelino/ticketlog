package com.ticketlogapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.entities.Estado;
import com.ticketlogapi.services.EstadoService;

@RestController
@RequestMapping(path = "/estados")
public class EstadosController {

	@Autowired
	private EstadoService services;

	@GetMapping
	public ResponseEntity<List<Estado>> findAll() {
		return ResponseEntity.ok().body(services.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Estado>> findById(@PathVariable int id) {
		return ResponseEntity.ok().body(services.findById(id));
	}

	@PostMapping
	public ResponseEntity<Estado> addEstado(@RequestBody Estado estado) {
		services.addEstado(estado);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Estado> deleteById(@PathVariable int id) {
		services.deleteEstado(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
