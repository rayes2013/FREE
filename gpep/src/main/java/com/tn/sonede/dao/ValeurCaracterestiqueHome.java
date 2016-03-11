package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.ValeurCaracterestique;
public interface ValeurCaracterestiqueHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(ValeurCaracterestique transientInstance) ;
	public void attachDirty(ValeurCaracterestique instance) ;
	public void attachClean(ValeurCaracterestique instance) ;
	public void delete(ValeurCaracterestique persistentInstance) ;
	public ValeurCaracterestique merge(ValeurCaracterestique detachedInstance) ;
	public ValeurCaracterestique findById(Integer id) ;
	public List<ValeurCaracterestique> findByExample(ValeurCaracterestique instance) ;
	public List<ValeurCaracterestique> findAll() ;
	public List<ValeurCaracterestique> findAllWithJoins() ;
	public List<ValeurCaracterestique> findAllWithJoins(String param) ;
	public ValeurCaracterestique findByIdWithJoins(Integer param);
	public long nbreLignesWithParam(String attribut, Object value);
}
