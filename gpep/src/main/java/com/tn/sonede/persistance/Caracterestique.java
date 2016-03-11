package com.tn.sonede.persistance;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "caracterestique", catalog = "public")
public class Caracterestique implements java.io.Serializable {

	
	private Integer id;
	private String code;
	private String designation;
	private Integer nature;
	private Integer type;
	private Set<ValeurCaracterestique> valeurs=new HashSet<ValeurCaracterestique>(0);
	private Set<TypeRessource> typeRessources = new HashSet<TypeRessource>(0);
	private String newValeur;
	private Boolean saisie;
	private Boolean valide;
	private Integer idValeur;
	

	public Caracterestique() {
	}

	public Caracterestique(Integer id) {
		this.id = id;
	}
	
	public Caracterestique(Integer id,String code, String designation, Integer nature,Integer  type, Set<TypeRessource> typeRessources,Set<ValeurCaracterestique> valeurs) {
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.nature = nature;
		this.type = type;
		this.typeRessources = typeRessources;
		this.valeurs = valeurs;
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
	
	@Column(name = "type_car")
	public Integer  getType() {
		return this.type;
	}

	public void setType(Integer  type) {
		this.type = type;
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "caracterestiques")
	public Set<TypeRessource> getTypeRessources() {
		return this.typeRessources;
	}

	public void setTypeRessources(Set<TypeRessource> typeRessources) {
		this.typeRessources = typeRessources;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "caracterestique",cascade=CascadeType.REMOVE)
	public Set<ValeurCaracterestique> getValeurs() {
		return this.valeurs;
	}

	public void setValeurs(Set<ValeurCaracterestique> valeurs ) {
		this.valeurs = valeurs;
	}
	
	@Transient
	public String getNewValeur() {
		return this.newValeur;
	}

	public void setNewValeur(String newValeur) {
		this.newValeur= newValeur;
	}
	
	@Transient
	public Boolean getSaisie() {
		return this.saisie;
	}

	public void setSaisie(Boolean saisie) {
		this.saisie= saisie;
	}
	
	@Transient
	public Boolean getValide() {
		return this.valide;
	}

	public void setValide(Boolean valide) {
		this.valide= valide;
	}
	
	@Transient
	public Integer getIdValeur() {
		return this.idValeur;
	}

	public void setIdValeur(Integer idValeur) {
		this.idValeur= idValeur;
	}
	
	public void init() {
		saisie=false;
		valide=false;
		if(valeurs==null||valeurs.size()==0) {
			saisie=true;
			valide=false;
		} else {
			
			ArrayList<ValeurCaracterestique> tmp=new ArrayList<ValeurCaracterestique>(valeurs);
			Collections.sort(tmp, new Comparator<ValeurCaracterestique>() {

				public int compare(ValeurCaracterestique o1, ValeurCaracterestique o2) {
					return o2.getDtAjout().compareTo(o1.getDtAjout());
				}
			});
			newValeur=tmp.get(0).getValeur();
			if(tmp.get(0).getValide()!=null)
				valide=tmp.get(0).getValide();
			else
				valide=false;
			idValeur=tmp.get(0).getId();
		}
		if(nature==1) {
			if(valeurs!=null&&valeurs.size()!=0) {
				ArrayList<ValeurCaracterestique> tmp=new ArrayList<ValeurCaracterestique>(valeurs);
				Collections.sort(tmp, new Comparator<ValeurCaracterestique>() {
					public int compare(ValeurCaracterestique o1, ValeurCaracterestique o2) {
						return o2.getDtAjout().compareTo(o1.getDtAjout());
					}
				});
				Date today1=new Date();
				Date today=new Date(today1.getYear(),today1.getMonth(),today1.getDate());
				if(tmp.get(0).getDtAjout().compareTo(today)<0||valide==false) {
					saisie=true;
				}
			}
		}
		if(nature==2) {
			if(valeurs!=null&&valeurs.size()!=0) {
				ArrayList<ValeurCaracterestique> tmp=new ArrayList<ValeurCaracterestique>(valeurs);
				Collections.sort(tmp, new Comparator<ValeurCaracterestique>() {
					public int compare(ValeurCaracterestique o1, ValeurCaracterestique o2) {
						return o2.getDtAjout().compareTo(o1.getDtAjout());
					}
				});
				Date today1=new Date();
				Date today=new Date(today1.getYear(),today1.getMonth(),0);
				Date dtajout1=tmp.get(0).getDtAjout();
				Date dtajout=new Date(dtajout1.getYear(),dtajout1.getMonth(),0);
				if(dtajout.compareTo(today)<0||valide==false)
					saisie=true;
			}
		}
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
		Caracterestique other = (Caracterestique) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void initReport(Set<ValeurCaracterestique> valeurs2,Date d) {
		ArrayList<ValeurCaracterestique> v = new ArrayList<ValeurCaracterestique>(valeurs2);
		newValeur="0";
		if(valeurs2!=null) {
			for(int i=0;i<v.size();i++) {
				ValeurCaracterestique val=v.get(i);
				if(id==val.getCaracterestique().getId()) {
					if(nature == 0|| nature ==2) {
						newValeur=val.getValeur();
					} else if(nature == 1){
						if(val.getValide()!=null&&val.getValide()==true&&val.getDtModif()!=null&&val.getDtModif().compareTo(d)==0) {
							newValeur=val.getValeur();
						}
					}
				}
			}
		}
	}

}
