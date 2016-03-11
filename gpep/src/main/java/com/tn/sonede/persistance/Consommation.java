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
@Table(name = "Consommation", catalog = "public")
public class Consommation implements java.io.Serializable {

	private Integer id;
	private Ressource ressource;
	private ProduitChimique produitChimique;
	private Double prix;
	private Double quantite;
	private Date dtConst;
	

	public Consommation() {
	}

	public Consommation(Integer id) {
		this.id = id;
	}
	
	public Consommation(Integer id,Ressource ressource,ProduitChimique produitChimique,Double prix,Double quantite,Date dtConst) {
		this.id = id;
		this.ressource = ressource;
		this.produitChimique = produitChimique;
		this.dtConst = dtConst;
		this.quantite = quantite;
		this.prix = prix;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "prix")
	public Double getPrix() {
		return this.prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	@Column(name = "quantite")
	public Double getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_const")
	public Date getDtConst() {
		return this.dtConst;
	}

	public void setDtConst(Date dtConst) {
		this.dtConst = dtConst;
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
	@JoinColumn(name = "id_pc",nullable = false)
	public ProduitChimique getProduitChimique() {
		return this.produitChimique;
	}

	public void setProduitChimique(ProduitChimique produitChimique) {
		this.produitChimique = produitChimique;
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
		result = prime * result + ((prix == null) ? 0 : prix.hashCode());
		result = prime * result + ((quantite == null) ? 0 : quantite.hashCode());
		result = prime * result + ((dtConst == null) ? 0 : dtConst.hashCode());
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
		Consommation other = (Consommation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
