package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Caracterestique;
public interface CaracterestiqueHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Caracterestique transientInstance) ;
	public void attachDirty(Caracterestique instance) ;
	public void attachClean(Caracterestique instance) ;
	public void delete(Caracterestique persistentInstance) ;
	public Caracterestique merge(Caracterestique detachedInstance) ;
	public Caracterestique findById(Integer id) ;
	public List<Caracterestique> findByExample(Caracterestique instance) ;
	public List<Caracterestique> findAll() ;
	public List<Caracterestique> findAllWithJoins() ;
	public List<Caracterestique> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);
	public List<Caracterestique> findAllCaracterestiqueASelectionner(Integer id);
	public Caracterestique findCaracterestiqueByIdWithAllJoins(Integer id);
}
