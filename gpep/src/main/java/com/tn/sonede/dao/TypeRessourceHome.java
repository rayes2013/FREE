package com.tn.sonede.dao;
import java.util.List;

import org.hibernate.SessionFactory;

import com.tn.sonede.persistance.TypeRessource;
public interface TypeRessourceHome {
	public SessionFactory getSessionFactory() ;
	public void setSessionFactory(SessionFactory sessionFactory) ;
	public void persist(TypeRessource transientInstance) ;
	public void attachDirty(TypeRessource instance) ;
	public void attachClean(TypeRessource instance) ;
	public void delete(TypeRessource persistentInstance) ;
	public TypeRessource merge(TypeRessource detachedInstance) ;
	public TypeRessource findById(Integer id) ;
	public List<TypeRessource> findByExample(TypeRessource instance) ;
	public List<TypeRessource> findAll() ;
	public List<TypeRessource> findAllWithJoins() ;
	public List<TypeRessource> findAllWithJoins(String param) ;
	public TypeRessource findByIdWithJoins(Integer param);
	public long nbreLignesWithParam(String attribut, Object value);
	public TypeRessource getCaracterestiquesduTypeRessourceByID(Integer id,
			int i);
	public TypeRessource getTypeRessourceByIDWithAllJoinsAvecValeurValideDate(
			Integer id, Integer annee, Integer mois);
}
