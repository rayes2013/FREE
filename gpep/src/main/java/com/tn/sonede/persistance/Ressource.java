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
import javax.persistence.Table;

@Entity
@Table(name = "ressource", catalog = "public")
public class Ressource implements java.io.Serializable {

	
	private Integer id;
	private String designation;
	private Integer annee;
	private String region;
	private Structure structure;
	private Gouvernerat gouvernerat;
	private TypeRessource typeRessource;
	private Set<ValeurCaracterestique> valeurs=new HashSet<ValeurCaracterestique>(0);
	private Set<Consommation> consommations=new HashSet<Consommation>(0);
	

	public Ressource() {
	}

	public Ressource(Integer id) {
		this.id = id;
	}

	public Ressource(Integer id, String designation, Integer annee,String region,Structure structure,Gouvernerat gouvernerat,TypeRessource typeRessource,Set<ValeurCaracterestique> valeurs,Set<Consommation> consommations) {
		this.id = id;
		this.designation = designation;
		this.annee = annee;
		this.region = region;
		this.structure = structure;
		this.gouvernerat = gouvernerat;
		this.typeRessource=typeRessource;
		this.valeurs=valeurs;
		this.consommations=consommations;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "designation", length = 254)
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Column(name = "region")
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	@Column(name = "annee")
	public Integer getAnnee() {
		return this.annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_gouv")
	public Gouvernerat getGouvernerat() {
		return this.gouvernerat;
	}

	public void setGouvernerat(Gouvernerat gouvernerat) {
		this.gouvernerat = gouvernerat;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_struc")
	public Structure getStructure() {
		return this.structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_type")
	public TypeRessource getTypeRessource() {
		return this.typeRessource;
	}

	public void setTypeRessource(TypeRessource typeRessource) {
		this.typeRessource = typeRessource;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ressource",cascade=CascadeType.REMOVE)
	public Set<ValeurCaracterestique> getValeurs() {
		return this.valeurs;
	}

	public void setValeurs(Set<ValeurCaracterestique> valeurs ) {
		this.valeurs = valeurs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ressource",cascade=CascadeType.REMOVE)
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
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((annee == null) ? 0 : annee.hashCode());
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
		Ressource other = (Ressource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
