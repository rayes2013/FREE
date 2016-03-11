package com.tn.sonede.persistance;

// Generated 31 mars 2014 16:08:34 by Hibernate Tools 3.4.0.CR1
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Personnel generated by hbm2java
 */
@Entity
@Table(name = "utilisateur", catalog = "public")
public class Utilisateur implements java.io.Serializable {

	private String login;
	private String password;
	private Boolean etat;
	private Groupe groupe;
	private Agent agent;
	

	public Utilisateur() {
	}

	public Utilisateur(String login) {
		this.login = login;
	}

	public Utilisateur(String login, String password, Boolean etat,Groupe groupe,Agent agent) {
		this.login = login;
		this.password = password;
		this.etat = etat;
		this.groupe=groupe;
		this.agent=agent;
	}

	@Id
	@Column(name = "login", unique = true, nullable = false)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", length = 254)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "etat")
	public Boolean getEtat() {
		return this.etat;
	}

	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "groupe")
	public Groupe getGroupe() {
		return this.groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	
	@OneToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "matricule")
	public Agent getAgent() {
		return this.agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return login+"";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((etat == null) ? 0 : etat.hashCode());
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
		Utilisateur other = (Utilisateur) obj;
		
		if (login != other.login)
			return false;
		
		
		return true;
	}

	

}
