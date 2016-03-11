package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Consommation;
public interface ConsommationHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Consommation transientInstance) ;
	public void attachDirty(Consommation instance) ;
	public void attachClean(Consommation instance) ;
	public void delete(Consommation persistentInstance) ;
	public Consommation merge(Consommation detachedInstance) ;
	public Consommation findById(Integer id) ;
	public List<Consommation> findByExample(Consommation instance) ;
	public List<Consommation> findAll() ;
	public List<Consommation> findAllWithJoins() ;
	public List<Consommation> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);
}
