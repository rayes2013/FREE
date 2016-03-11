package com.tn.sonede.persistance;

// Generated 18 avr. 2014 11:34:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Groupe generated by hbm2java
 */
@Entity
@Table(name = "groupe", catalog = "public")
public class Groupe implements java.io.Serializable {

	private Integer id;
	private String designation;


	public Groupe() {
	}

	public Groupe(Integer id) {
		this.id = id;
	}

	public Groupe(Integer id, String designation) {
		this.id = id;
		this.designation = designation;

	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getCode() {
		return this.id;
	}

	public void setCode(Integer id) {
		this.id = id;
	}

	@Column(name = "designation", length = 254)
	public String getLibelle() {
		return this.designation;
	}

	public void setLibelle(String designation) {
		this.designation = designation;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Groupe other = (Groupe) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  id+"";
	}
	
	

}
