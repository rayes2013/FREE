package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.ProduitChimique;
public interface ProduitChimiqueHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(ProduitChimique transientInstance) ;
	public void attachDirty(ProduitChimique instance) ;
	public void attachClean(ProduitChimique instance) ;
	public void delete(ProduitChimique persistentInstance) ;
	public ProduitChimique merge(ProduitChimique detachedInstance) ;
	public ProduitChimique findById(Integer id) ;
	public List<ProduitChimique> findByExample(ProduitChimique instance) ;
	public List<ProduitChimique> findAll() ;
	public List<ProduitChimique> findAllWithJoins() ;
	public List<ProduitChimique> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);
}
