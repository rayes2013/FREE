package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Gouvernerat;
public interface GouverneratHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Gouvernerat transientInstance) ;
	public void attachDirty(Gouvernerat instance) ;
	public void attachClean(Gouvernerat instance) ;
	public void delete(Gouvernerat persistentInstance) ;
	public Gouvernerat merge(Gouvernerat detachedInstance) ;
	public Gouvernerat findById(Integer id) ;
	public List<Gouvernerat> findByExample(Gouvernerat instance) ;
	public List<Gouvernerat> findAll() ;
	public List<Gouvernerat> findAllWithJoins() ;
	public List<Gouvernerat> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);
}
