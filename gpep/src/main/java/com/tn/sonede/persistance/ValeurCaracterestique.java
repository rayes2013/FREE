package com.tn.sonede.persistance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "ValeurCaracterestique", catalog = "public")
public class ValeurCaracterestique implements java.io.Serializable {

	private Integer id;
	private Ressource ressource;
	private Caracterestique caracterestique;
	private String valeur;
	private Date dtAjout;
	private Date dtModif;
	private Boolean valide;
	

	public ValeurCaracterestique() {
	}

	public ValeurCaracterestique(Integer id) {
		this.id = id;
	}
	
	public ValeurCaracterestique(Integer id,String valeur, Date dtAjout, Date dtModif,Boolean valide,Ressource ressource,Caracterestique caracterestique) {
		this.id = id;
		this.valeur = valeur;
		this.dtAjout = dtAjout;
		this.dtModif = dtModif;
		this.valide = valide;
		this.ressource = ressource;
		this.caracterestique = caracterestique;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "valeur")
	public String getValeur() {
		return this.valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	@Column(name = "valide")
	public Boolean getValide() {
		return this.valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtvalid")
	public Date getDtModif() {
		return this.dtModif;
	}

	public void setDtModif(Date dtModif) {
		this.dtModif = dtModif;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dtsaisie")
	public Date getDtAjout() {
		return this.dtAjout;
	}

	public void setDtAjout(Date dtAjout) {
		this.dtAjout = dtAjout;
	}

	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_res",nullable = false)
	public Ressource getRessource() {
		return this.ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_car",nullable = false)
	public Caracterestique getCaracterestique() {
		return this.caracterestique;
	}

	public void setCaracterestique(Caracterestique caracterestique) {
		this.caracterestique = caracterestique;
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
		result = prime * result + ((valide == null) ? 0 : valide.hashCode());
		result = prime * result + ((valeur == null) ? 0 : valeur.hashCode());
		result = prime * result + ((dtAjout == null) ? 0 : dtAjout.hashCode());
		result = prime * result + ((dtModif == null) ? 0 : dtModif.hashCode());
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
		ValeurCaracterestique other = (ValeurCaracterestique) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
