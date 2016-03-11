package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Formule;
public interface FormuleHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Formule transientInstance) ;
	public void attachDirty(Formule instance) ;
	public void attachClean(Formule instance) ;
	public void delete(Formule persistentInstance) ;
	public Formule merge(Formule detachedInstance) ;
	public Formule findById(Integer id) ;
	public List<Formule> findByExample(Formule instance) ;
	public List<Formule> findAll() ;
	public List<Formule> findAllWithJoins() ;
	public List<Formule> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);
}
