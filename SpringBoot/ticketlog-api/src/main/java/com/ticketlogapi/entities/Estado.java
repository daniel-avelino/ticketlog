package com.ticketlogapi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_estados")
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private String estado;

	@OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
	private List<Cidade> cidades;

	/*
	 * public enum Estados { SantaCatarina("Santa Catarina"),
	 * RioGrandeDoSul("Rio Grande do Sul"), Parana("Paraná");
	 * 
	 * private String descricao;
	 * 
	 * Estados(String descricao) { this.descricao = descricao; }
	 * 
	 * public String getDescricao() { return descricao; } }
	 * 
	 * public Estado() { }
	 * 
	 * public Estado(Integer id, Estados estado) { Id = id; this.estado = estado; }
	 */

	public Estado() {
	}

	public Estado(Integer id, String estado) {
		Id = id;
		this.estado = estado;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((cidades == null) ? 0 : cidades.hashCode());
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
		Estado other = (Estado) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (cidades == null) {
			if (other.cidades != null)
				return false;
		} else if (!cidades.equals(other.cidades))
			return false;
		if (estado != other.estado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado [Id=" + Id + ", estado=" + estado + ", cidades=" + cidades + "]";
	}

}
