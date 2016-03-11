package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Agent;
public interface AgentHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Agent transientInstance) ;
	public void attachDirty(Agent instance) ;
	public void attachClean(Agent instance) ;
	public void delete(Agent persistentInstance) ;
	public Agent merge(Agent detachedInstance) ;
	public Agent findById(int id) ;
	public List<Agent> findByExample(Agent instance) ;
	public List<Agent> findAll() ;
	public List<Agent> findAllWithJoins() ;
	public List<Agent> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);

}
