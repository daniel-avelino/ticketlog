package com.ticketlogapi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Cidade>> findById(@PathVariable int id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Cidade> addCidade(@RequestBody Cidade cidade) {
		service.addCidade(cidade);
		custosService.CalculaCusto(cidade.getId());
		estadoService.sumPopulacao(cidade.getEstadoId());
		custosService.CustoEstado(cidade.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Cidade> deleteById(@PathVariable int id) {
		service.deleteCidade(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
