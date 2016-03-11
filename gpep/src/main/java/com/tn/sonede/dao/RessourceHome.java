package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Ressource;
public interface RessourceHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Ressource transientInstance) ;
	public void attachDirty(Ressource instance) ;
	public void attachClean(Ressource instance) ;
	public void delete(Ressource persistentInstance) ;
	public Ressource merge(Ressource detachedInstance) ;
	public Ressource findById(Integer id) ;
	public List<Ressource> findByExample(Ressource instance) ;
	public List<Ressource> findAll() ;
	public List<Ressource> findAllWithJoins() ;
	public List<Ressource> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);
	public Ressource findByIdWithJoinsAvecValeurs(Integer id);
	public Ressource findByIdWithJoinsAvecValeursValide(Integer id);
	public Ressource findByIdWithJoins(Integer id);
	public List<Ressource> findAllWithJoinsAvecCP();
	public Ressource findByIDAvecValeursMoisAnnee(Integer id, Integer annee,Integer mois);
}
