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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "type_ressource", catalog = "public")
public class TypeRessource implements java.io.Serializable {

	
	private Integer id;
	private String code;
	private String designation;
	private Integer nature;
	private Boolean consommationPC;
	private Set<Formule> formules;
	private Set<Caracterestique> caracterestiques = new HashSet<Caracterestique>(0);
	private Set<Ressource> ressources = new HashSet<Ressource>(0);
	

	public TypeRessource() {
	}

	public TypeRessource(Integer id) {
		this.id = id;
	}

	public TypeRessource(Integer id,String code, String designation, Integer nature,Boolean consommationPC,Set<Formule> formules,Set<Caracterestique> caracterestiques,Set<Ressource> ressources) {
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.nature = nature;
		this.consommationPC = consommationPC;
		this.formules = formules;
		this.caracterestiques=caracterestiques;
		this.ressources=ressources;
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

	@Column(name = "nature")
	public Integer getNature() {
		return this.nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}
	
	@Column(name = "consommationPC")
	public Boolean getConsommationPC() {
		return this.consommationPC;
	}

	public void setConsommationPC(Boolean consommationPC) {
		this.consommationPC = consommationPC;
	}
	
	@ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
	@JoinTable(name = "caracterestiques_type", catalog = "public", joinColumns = { 
			@JoinColumn(name = "id_type", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_car", 
					nullable = false, updatable = false) })
	public Set<Caracterestique> getCaracterestiques() {
		return this.caracterestiques;
	}

	public void setCaracterestiques(Set<Caracterestique> caracterestiques) {
		this.caracterestiques = caracterestiques;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeRessource",cascade=CascadeType.REMOVE)
	public Set<Formule> getFormules() {
		return this.formules;
	}

	public void setFormules(Set<Formule> formules ) {
		this.formules = formules;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeRessource",cascade=CascadeType.REMOVE)
	public Set<Ressource> getRessources() {
		return this.ressources;
	}

	public void setRessources(Set<Ressource> ressources ) {
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
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime * result + ((consommationPC == null) ? 0 : consommationPC.hashCode());
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
		TypeRessource other = (TypeRessource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
