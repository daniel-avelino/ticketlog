package com.ticketlogapi.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketlogapi.entities.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
