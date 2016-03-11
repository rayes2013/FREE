package com.tn.sonede.dao;

import static org.hibernate.criterion.Example.create;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.TypeRessource;

@Repository("typeRessourceHome")
@Scope("singleton")
public class TypeRessourceHomeImp implements TypeRessourceHome{

	private static final Log log = LogFactory.getLog(TypeRessourceHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(TypeRessource transientInstance) {
		log.debug("persisting TypeRessource instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TypeRessource instance) {
		log.debug("attaching dirty TypeRessource instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TypeRessource instance) {
		log.debug("attaching clean TypeRessource instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TypeRessource persistentInstance) {
		log.debug("deleting TypeRessource instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TypeRessource merge(TypeRessource detachedInstance) {
		log.debug("merging TypeRessource instance");
		try {
			TypeRessource result = (TypeRessource) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TypeRessource findById(Integer id) {
		log.debug("getting TypeRessource instance with id: " + id);
		try {
			TypeRessource instance = (TypeRessource) sessionFactory.getCurrentSession().get(
					TypeRessource.class, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public TypeRessource findByIdWithJoins(Integer id) {
		log.debug("getting TypeRessource instance with id: " + id);
		try {
			Criteria crit = sessionFactory.getCurrentSession()
					.createCriteria(TypeRessource.class).add(Restrictions.eq("id", id))
					.setFetchMode("formules", FetchMode.JOIN)
					.setFetchMode("caracterestiques", FetchMode.JOIN);
			return (TypeRessource) crit.uniqueResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TypeRessource> findByExample(TypeRessource instance) {
		log.debug("finding TypeRessource instance by example");
		try {
			List<TypeRessource> results = (List<TypeRessource>) sessionFactory
					.getCurrentSession()
					.createCriteria(TypeRessource.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<TypeRessource> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				TypeRessource.class);
		return (List<TypeRessource>)crit.list();
	}

	public List<TypeRessource> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				TypeRessource.class);
		return (List<TypeRessource>) crit.list();
	}

	public List<TypeRessource> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(TypeRessource.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<TypeRessource>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from TypeRessource  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}

	public TypeRessource getCaracterestiquesduTypeRessourceByID(Integer id,
			int i) {
		TypeRessource res= (TypeRessource)sessionFactory.getCurrentSession()
				.createQuery( " from TypeRessource t left outer join fetch t.caracterestiques c  left outer join fetch t.formules f" +
						" where t.id=?  " )
						.setInteger(0, id)
						.uniqueResult();
		return res;
	}
	
	public TypeRessource getTypeRessourceByIDWithAllJoinsAvecValeurValideDate(
			Integer id, Integer annee, Integer mois) {
		TypeRessource res= (TypeRessource)sessionFactory.getCurrentSession()
				.createQuery( " from TypeRessource t full join fetch t.ressources r  full join fetch r.valeurs v full join fetch v.caracterestique c " +
						" where t.id=? and ( ((month(v.dtModif)=? and year(v.dtModif)=?) or v is null ) or c.nature=0)" )
						.setInteger(0, id)
						.setInteger(1, mois)
						.setInteger(2, annee)
						.uniqueResult();
		return res;
		
	}
}
