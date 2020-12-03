package com.ticketlogapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketlogapi.Repositories.CidadeRepository;
import com.ticketlogapi.entities.Cidade;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<Cidade> findAll() {
		return repository.findAll();
	}

	public Optional<Cidade> findById(int id) {
		return repository.findById(id);
	}

	public List<Cidade> findByName(String name) {
		List<Cidade> list = repository.findAll();
		List<Cidade> listCidades = list.stream().filter(x -> x.getCidade().equalsIgnoreCase(name)).collect(Collectors.toList());
		return listCidades;
	}

	public boolean cidadeDuplicada(String cidade) {
		List<Cidade> list = repository.findAll();
		return list.stream().anyMatch(x -> x.getCidade().equals(cidade));
	}

	public void addCidade(Cidade cidade) {
		repository.save(cidade);
	}

	public void addImport(List<Cidade> cidade) {

	}

	public void deleteCidade(int id) {
		repository.deleteById(id);
	}

	public void importCidade() {

	}

}
