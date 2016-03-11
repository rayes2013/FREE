package com.tn.sonede.persistance;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gouvernerat", catalog = "public")
public class Gouvernerat implements java.io.Serializable {

	
	private Integer id;
	private String code;
	private String designation;
	private Set<Ressource> ressources = new HashSet<Ressource>(0);
	

	public Gouvernerat() {
	}

	public Gouvernerat(Integer id) {
		this.id = id;
	}

	public Gouvernerat(Integer id,String code, String designation, Set<Ressource> ressources) {
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.ressources = ressources;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "code", unique = true, nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "designation", length = 254)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gouvernerat")
	public Set<Ressource> getRessources() {
		return this.ressources;
	}

	public void setRessources(Set<Ressource> ressources) {
		this.ressources = ressources;
	}
	
	@Override
	public String toString() {
		return id+"";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
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
		Gouvernerat other = (Gouvernerat) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
