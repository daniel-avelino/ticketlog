package com.ticketlogapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketlogapi.Repositories.EstadoRepository;
import com.ticketlogapi.entities.Estado;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public List<Estado> findAll() {
		return repository.findAll();
	}

	public Optional<Estado> findById(int id) {
		return repository.findById(id);
	}

	public void addEstado(Estado estado) {
		repository.save(estado);
	}

	public void deleteEstado(int id) {
		repository.deleteById(id);
	}
}
