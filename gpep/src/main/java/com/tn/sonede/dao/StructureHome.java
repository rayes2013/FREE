package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Structure;
public interface StructureHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Structure transientInstance) ;
	public void attachDirty(Structure instance) ;
	public void attachClean(Structure instance) ;
	public void delete(Structure persistentInstance) ;
	public Structure merge(Structure detachedInstance) ;
	public Structure findById(Integer id) ;
	public List<Structure> findByExample(Structure instance) ;
	public List<Structure> findAll() ;
	public List<Structure> findAllWithJoins() ;
	public List<Structure> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);
}
