package com.ticketlogapi.services;

import java.util.List;
import java.util.Optional;

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

	public void addCidade(Cidade cidade) {
		repository.save(cidade);
	}

	public void deleteCidade(int id) {
		repository.deleteById(id);
	}

}
