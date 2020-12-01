package com.ticketlogapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketlogapi.Repositories.CidadeRepository;
import com.ticketlogapi.Repositories.EstadoRepository;
import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.entities.Estado;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	@Autowired
	private CidadeRepository cidadeRepository;

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

	public void sumPopulacao(int id) {
		Estado estado = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		List<Cidade> list = cidadeRepository.findAll();
		int totalPopulacao = list.stream().filter(p -> p.getEstadoId().equals(id))
				.collect(Collectors.summingInt(Cidade::getPopulacao));
		System.out.println(totalPopulacao);
		estado.setPopulacao(totalPopulacao);
		repository.save(estado);

	}

}
