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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produitChimique", catalog = "public")
public class ProduitChimique implements java.io.Serializable {

	
	private Integer id;
	private String code;
	private String designation;
	private Set<Consommation> consommations=new HashSet<Consommation>(0);
	

	public ProduitChimique() {
	}

	public ProduitChimique(Integer id) {
		this.id = id;
	}

	public ProduitChimique(Integer id,String code, String designation,Set<Consommation> consommations) {
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.consommations = consommations;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produitChimique",cascade=CascadeType.REMOVE)
	public Set<Consommation> getConsommations() {
		return this.consommations;
	}

	public void setConsommations(Set<Consommation> consommations ) {
		this.consommations = consommations;
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
		ProduitChimique other = (ProduitChimique) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
