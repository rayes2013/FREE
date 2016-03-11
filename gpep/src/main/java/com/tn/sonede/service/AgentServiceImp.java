package com.tn.sonede.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tn.sonede.dao.AgentHome;
import com.tn.sonede.persistance.Agent;

@Transactional
@Service("agentService")
@Scope("prototype")
public class AgentServiceImp implements AgentService{
	
	@Autowired
	private AgentHome agentDAO;

	public List<Agent> getListAgent() {
		return agentDAO.findAllWithJoins();
	}

	public AgentHome getAgentDAO() {
		return agentDAO;
	}

	public void setAgentDAO(
			AgentHome AgentDAO) {
		this.agentDAO = AgentDAO;
	}

	public void supprimerAgent(Agent sec) {
		
		agentDAO.delete(sec);
		
	}

	public void mettreJourAgent(Agent sec) {
		// insertion d'un Agent
		
		agentDAO.attachDirty(sec);
		
	}

	public void ajoutAgent(Agent secrage) {
		// insertion d'un Agent
		
		agentDAO.persist(secrage);
		
	}

	public Agent getAgentByID(int id) {
		// TODO Auto-generated method stub
		return agentDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return agentDAO.nbreLignesWithParam(attribut,value);
	}
	

}