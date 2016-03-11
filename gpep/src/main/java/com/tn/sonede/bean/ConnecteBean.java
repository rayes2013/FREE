package com.tn.sonede.bean;



import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.tn.sonede.persistance.Agent;
import com.tn.sonede.persistance.Utilisateur;
import com.tn.sonede.service.AgentService;
import com.tn.sonede.service.UtilisateurService;




@ManagedBean(name = "connecteBean")
@SessionScoped
public class ConnecteBean implements Serializable {

	@ManagedProperty(value = "#{agentService}")
	private AgentService agentService;

	@ManagedProperty(value = "#{utilisateurService}")
	private UtilisateurService utilisateurService;

	private int matricule;
	
	private String utilisateur1;
	
	private Agent agent;
	private String path;


	private Utilisateur utilisateur;

	
	

	public AgentService getagentService() {
		return agentService;
	}

	public void setUtilisateurService(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	public UtilisateurService getUtilisateurService() {
		return utilisateurService;
	}

	public void setagentService(AgentService agentService) {
		this.agentService = agentService;
	}
	
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ConnecteBean() {

		
		
	}

	 @PostConstruct  
	    public void init() {      
		 User connecte = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 new ArrayList(connecte.getAuthorities());
		utilisateur=utilisateurService.getUtilisateurByID(connecte.getUsername()) ;
		utilisateur1=utilisateur.getAgent().getNom() + " " + utilisateur.getAgent().getPrenom();
	        
	  }


	private Integer ratingUtilisateurname = 0;
	private Integer ratingPassword = 0;

	public Integer getRatingUtilisateurname() {
		return ratingUtilisateurname;
	}

	public void setRatingUtilisateurname(Integer ratingUtilisateurname) {
		this.ratingUtilisateurname = ratingUtilisateurname;
	}

	public Integer getRatingPassword() {
		return ratingPassword;
	}

	public void setRatingPassword(Integer ratingPassword) {
		this.ratingPassword = ratingPassword;
	}

	public void utilisateurnamePrecision() {

		ratingUtilisateurname = utilisateur.getLogin().length() / 4;

	}

	public void passwordPrecision() {

		ratingPassword = utilisateur.getPassword().length() / 4;
	}

	
	public long getMatricule() {
		return matricule;
	}

	
	public String getUtilisateur1()
	{
		return utilisateur1;
	}
	
	public String getPath() {
		switch(utilisateur.getGroupe().getCode()) {
			case 1 : return "ADMIN";
			case 2 : return "AGENT";
			case 3 : return "RESPCENTRAL";
			case 4 : return "RESPTERRITORIAL";
		}
		return "";
	}
	
}