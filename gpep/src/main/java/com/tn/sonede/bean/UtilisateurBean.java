package com.tn.sonede.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tn.sonede.persistance.Agent;
import com.tn.sonede.persistance.Groupe;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.Utilisateur;
import com.tn.sonede.service.AgentService;
import com.tn.sonede.service.GroupeService;
import com.tn.sonede.service.UtilisateurService;


@ManagedBean(name = "utilisateurBean")
@SessionScoped
public class UtilisateurBean implements Serializable {

	@ManagedProperty(value = "#{utilisateurService}")
	private UtilisateurService utilisateurService;
	@ManagedProperty(value = "#{agentService}")
	private AgentService agentService;
	@ManagedProperty(value = "#{groupeService}")
	private GroupeService groupeService;
	private Utilisateur utilisateur;
	private List<Utilisateur> listUtilisateurs;
	private List<Agent> listAgents;
	private List<Groupe> listGroupes;

	public GroupeService getGroupeService() {
		return groupeService;
	}

	public void setGroupeService(GroupeService groupeService) {
		this.groupeService = groupeService;
	}
	
	public List<Groupe> getListGroupes() {
		listGroupes = groupeService.getListGroupe();
		return listGroupes;
	}

	public AgentService getAgentService() {
		return agentService;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}
	
	public UtilisateurService getUtilisateurService() {
		return utilisateurService;
	}

	public void setUtilisateurService(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
	
	public List<Agent> getListAgents() {
		listAgents = agentService.getListAgent();
		return listAgents;
	}

	public UtilisateurBean() {
		utilisateur = new Utilisateur();
	}
	
	public void ajoutEvent(ActionEvent actionEvent) {
		utilisateur = new Utilisateur();
	}
	
	public void onRowSelect(String id) {
		utilisateur = utilisateurService.getUtilisateurByID(id);
	}

	public void editEvent(String id) {
		utilisateur = utilisateurService.getUtilisateurByID(id);
	}

	public void update(ActionEvent actionEvent) {
		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(utilisateur.getLogin()==null||utilisateur.getLogin().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Login obligatoire"));
			testRG= false;
		}
		if(utilisateur.getPassword()==null||utilisateur.getPassword().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Password obligatoire"));
			testRG= false;
		}
		if(utilisateur.getAgent()==null||utilisateur.getAgent().getMatricule()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Agent obligatoire"));
			testRG= false;
		}
		if(utilisateur.getGroupe()==null||utilisateur.getGroupe().getCode()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Groupe obligatoire"));
			testRG= false;
		}
		if(testRG){
			utilisateurService.mettreJourUtilisateur(utilisateur);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Utilisateur modifié"));
		}
	}
	
	public void activer(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		utilisateur.setEtat(true);
		utilisateurService.mettreJourUtilisateur(utilisateur);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Utilisateur activé"));
	}
	
	public void desactiver(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		utilisateur.setEtat(false);
		utilisateurService.mettreJourUtilisateur(utilisateur);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Utilisateur désactivé"));
	}

	public void insert(ActionEvent actionEvent) {
		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(utilisateur.getLogin()==null||utilisateur.getLogin().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Login obligatoire"));
			testRG= false;
		}
		if(utilisateurService.nbreLignesWithParam("login", utilisateur.getLogin())!=0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Login doit etre unique"));
			testRG= false;
		}
		
		if(utilisateur.getPassword()==null||utilisateur.getPassword().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Password obligatoire"));
			testRG= false;
		}
		if(utilisateur.getAgent()==null||utilisateur.getAgent().getMatricule()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Agent obligatoire"));
			testRG= false;
		}
		if(utilisateur.getGroupe()==null||utilisateur.getGroupe().getCode()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Groupe obligatoire"));
			testRG= false;
		}
		if(testRG){
			utilisateurService.ajoutUtilisateur(utilisateur);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Utilisateur ajouté"));
			utilisateur = new Utilisateur();
		}
	}

	public void delete(Utilisateur Utilisateur) {
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			utilisateurService.supprimerUtilisateur(Utilisateur);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Utilisateur supprimé"));
		} catch(Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Erreur de suppression"));
		}
	}

	public List<Utilisateur> getListUtilisateurs() {
		listUtilisateurs = utilisateurService.getListUtilisateur();
		Collections.sort(listUtilisateurs, new Comparator<Utilisateur>() {
			public int compare(Utilisateur o1, Utilisateur o2) {
				return (o2.getLogin().compareTo(o1.getLogin()));
			}
		});
		return listUtilisateurs;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur Utilisateur) {
		this.utilisateur = Utilisateur;
	}

}