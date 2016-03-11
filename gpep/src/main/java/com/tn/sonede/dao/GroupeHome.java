package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Groupe;
public interface GroupeHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Groupe transientInstance) ;
	public void attachDirty(Groupe instance) ;
	public void attachClean(Groupe instance) ;
	public void delete(Groupe persistentInstance) ;
	public Groupe merge(Groupe detachedInstance) ;
	public Groupe findById(Integer id) ;
	public List<Groupe> findByExample(Groupe instance) ;
	public List<Groupe> findAll() ;
	public List<Groupe> findAllWithJoins() ;
	public List<Groupe> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);
}
