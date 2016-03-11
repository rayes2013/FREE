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
import com.tn.sonede.persistance.Formule;
import com.tn.sonede.persistance.Structure;
import com.tn.sonede.persistance.Agent;
import com.tn.sonede.persistance.ValeurCaracterestique;
import com.tn.sonede.service.AgentService;
import com.tn.sonede.service.StructureService;


@ManagedBean(name = "agentBean")
@SessionScoped
public class AgentBean implements Serializable {

	@ManagedProperty(value = "#{agentService}")
	private AgentService agentService;
	@ManagedProperty(value = "#{structureService}")
	private StructureService structureService;
	private List<Structure> listStructures;
	private Agent agent;
	private List<Agent> listAgents;

	public AgentService getAgentService() {
		return agentService;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}
	
	public StructureService getStructureService() {
		return structureService;
	}

	public void setStructureService(StructureService structureService) {
		this.structureService = structureService;
	}

	public AgentBean() {
		agent = new Agent();
	}
	
	public List<Structure> getListStructures() {
		listStructures = structureService.getListStructure();
		return listStructures;
	}

	public void ajoutEvent(ActionEvent actionEvent) {
		agent = new Agent();
	}
	
	public void onRowSelect(int id) {
		agent = agentService.getAgentByID(id);
	}

	public void editEvent(int id) {
		agent = agentService.getAgentByID(id);
	}

	public void update(ActionEvent actionEvent) {
boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(agent==null||agent.getMatricule()==0) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Matricule obligatoire"));
			testRG= false;
		}
		if(agent.getNom()==null||agent.getNom().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Nom obligatoire"));
			testRG= false;
		}
		if(agent.getPrenom()==null||agent.getPrenom().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Prénom obligatoire"));
			testRG= false;
		}
		if(testRG){
			agentService.mettreJourAgent(agent);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Agent modifié"));
		}
	}

	public void insert(ActionEvent actionEvent) {
		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(agent==null||agent.getMatricule()==0) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Matricule obligatoire"));
			testRG= false;
		}
		if(agentService.nbreLignesWithParam("matricule", agent.getMatricule())!=0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Matricule doit etre unique"));
			testRG= false;
		}
		if(agent.getNom()==null||agent.getNom().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Nom obligatoire"));
			testRG= false;
		}
		if(agent.getPrenom()==null||agent.getPrenom().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Prénom obligatoire"));
			testRG= false;
		}
		if(testRG){
			agentService.ajoutAgent(agent);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Agent ajouté"));
			agent = new Agent();
		}
	}

	public void delete(Agent Agent) {
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			agentService.supprimerAgent(Agent);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Agent supprimé"));
		} catch(Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Erreur de suppression: agent associé à un compte"));
	
		}
	}

	public List<Agent> getListAgents() {
		listAgents = agentService.getListAgent();
		Collections.sort(listAgents, new Comparator<Agent>() {
			public int compare(Agent o1, Agent o2) {
				return (o2.getMatricule()-o1.getMatricule());
			}
		});
		return listAgents;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent Agent) {
		this.agent = Agent;
	}

}