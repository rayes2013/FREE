package com.tn.sonede.service;
import java.util.List;

import com.tn.sonede.dao.AgentHome;
import com.tn.sonede.persistance.Agent;

public interface AgentService {
	public List<Agent> getListAgent() ;
	public AgentHome getAgentDAO() ;
	public void setAgentDAO(AgentHome sec);
	public void supprimerAgent(Agent sec) ;
	public void mettreJourAgent(Agent sec) ;
	public void ajoutAgent(Agent secrage) ;
	public Agent getAgentByID(int id) ;
	public long nbreLignesWithParam(String string, Object value);
}
