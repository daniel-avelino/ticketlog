package com.ticketlogapi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_cidades")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private String Cidade;

	private Integer Populacao;

	@ManyToOne
	private Estado estado;

	public Cidade() {
	}

	public Cidade(Integer id, String cidade, Estado estado, int populacao) {
		Id = id;
		Cidade = cidade;
		this.estado = estado;
		Populacao = populacao;
	}

	public int getPopulacao() {
		return Populacao;
	}

	public void setPopulacao(int populacao) {
		Populacao = populacao;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public Integer getEstadoId() {
		return estado.getId();
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Cidade == null) ? 0 : Cidade.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (Cidade == null) {
			if (other.Cidade != null)
				return false;
		} else if (!Cidade.equals(other.Cidade))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [Id=" + Id + ", Cidade=" + Cidade + ", estado=" + estado + "]";
	}

}
