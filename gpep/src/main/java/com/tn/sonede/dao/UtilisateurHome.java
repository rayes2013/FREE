package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.Utilisateur;
public interface UtilisateurHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(Utilisateur transientInstance) ;
	public void attachDirty(Utilisateur instance) ;
	public void attachClean(Utilisateur instance) ;
	public void delete(Utilisateur persistentInstance) ;
	public Utilisateur merge(Utilisateur detachedInstance) ;
	public Utilisateur findById(String id) ;
	public List<Utilisateur> findByExample(Utilisateur instance) ;
	public List<Utilisateur> findAll() ;
	public List<Utilisateur> findAllWithJoins() ;
	public List<Utilisateur> findAllWithJoins(String param) ;
	public long nbreLignesWithParam(String attribut, Object value);

}
