package com.ticketlogapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketlogapi.Repositories.CidadeRepository;
import com.ticketlogapi.Repositories.EstadoRepository;
import com.ticketlogapi.entities.Cidade;
import com.ticketlogapi.entities.Estado;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> findAll() {
		return repository.findAll();
	}

	public Optional<Cidade> findById(int id) {
		return repository.findById(id);
	}

	public List<Cidade> findByName(String name) {
		List<Cidade> list = repository.findAll();
		List<Cidade> listCidades = list.stream().filter(x -> x.getCidade().equalsIgnoreCase(name))
				.collect(Collectors.toList());
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

	public Estado cidadePorEstado(String estados) {
		List<Estado> list = estadoRepository.findAll();
		Estado estado = (Estado) list.stream().filter(x -> x.getEstado().equalsIgnoreCase(estados));
		return estado;
	}
	
	
	public boolean cidadeRS(int id) {
		Optional<Cidade> cidade = repository.findById(id);

		if (cidade.get().getEstado().getId().equals(1)) {
			return true;
		} else {
			return false;
		}
	}

	public void deleteCidade(int id) {
		repository.deleteById(id);
	}

	public void importCidade() {

	}

}
