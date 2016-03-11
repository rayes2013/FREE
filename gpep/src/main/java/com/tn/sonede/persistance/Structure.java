package com.tn.sonede.persistance;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "structure", catalog = "public")
public class Structure implements java.io.Serializable {

	
	private Integer id;
	private String code;
	private String designation;
	private Integer type;
	private Set<Structure> structuresFils = new HashSet<Structure>(0);
	private Set<Ressource> ressources = new HashSet<Ressource>(0);
	private Structure structureParent;
	

	public Structure() {
	}

	public Structure(Integer id) {
		this.id = id;
	}

	public Structure(Integer id,String code, String designation, Integer type,Set<Structure> structuresFils,Set<Ressource> ressources,Structure structureParent) {
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.type = type;
		this.structuresFils = structuresFils;
		this.ressources = ressources;
		this.structureParent = structureParent;
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

	@Column(name = "type_struc")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "structureParent",cascade=CascadeType.REMOVE)
	public Set<Structure> getStructuresFils() {
		return this.structuresFils;
	}

	public void setStructuresFils(Set<Structure> structuresFils) {
		this.structuresFils = structuresFils;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "structure",cascade=CascadeType.REMOVE)
	public Set<Ressource> getRessources() {
		return this.ressources;
	}

	public void setRessources(Set<Ressource> ressources) {
		this.ressources = ressources;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_struc")
	public Structure getStructureParent() {
		return this.structureParent;
	}

	public void setStructureParent(Structure structureParent) {
		this.structureParent = structureParent;
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
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Structure other = (Structure) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
