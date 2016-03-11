package com.tn.sonede.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.tn.sonede.persistance.Caracterestique;
import com.tn.sonede.persistance.Ressource;

@Repository("caracterestiqueHome")
@Scope("singleton")
public class CaracterestiqueHomeImp implements CaracterestiqueHome{

	private static final Log log = LogFactory.getLog(CaracterestiqueHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Caracterestique transientInstance) {
		log.debug("persisting Caracterestique instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Caracterestique instance) {
		log.debug("attaching dirty Caracterestique instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Caracterestique instance) {
		log.debug("attaching clean Caracterestique instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Caracterestique persistentInstance) {
		log.debug("deleting Caracterestique instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Caracterestique merge(Caracterestique detachedInstance) {
		log.debug("merging Caracterestique instance");
		try {
			Caracterestique result = (Caracterestique) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Caracterestique findById(Integer id) {
		log.debug("getting Caracterestique instance with id: " + id);
		try {
			Caracterestique instance = (Caracterestique) sessionFactory.getCurrentSession().get(
					Caracterestique.class, id);
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

	public List<Caracterestique> findByExample(Caracterestique instance) {
		log.debug("finding Caracterestique instance by example");
		try {
			List<Caracterestique> results = (List<Caracterestique>) sessionFactory
					.getCurrentSession()
					.createCriteria(Caracterestique.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Caracterestique> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Caracterestique.class);
		return (List<Caracterestique>)crit.list();
	}

	public List<Caracterestique> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Caracterestique.class);
		return (List<Caracterestique>) crit.list();
	}

	public List<Caracterestique> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Caracterestique.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<Caracterestique>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from Caracterestique  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}
	
	public List<Caracterestique> findAllCaracterestiqueASelectionner(Integer id) {
		
		return (List<Caracterestique>) sessionFactory.getCurrentSession()
				.createSQLQuery("Select id,code,designation from Caracterestique    " +
						"  Where id not in ( select id_car from caracterestiques_type" +
						" where id_type="+id+" and id_car=id)"		
				)
				.setResultTransformer(Transformers.aliasToBean(Caracterestique.class))
				.list();
	}
	
	
	public Caracterestique findCaracterestiqueByIdWithAllJoins(Integer id) {
		log.debug("getting TypeRessource instance with id: " + id);
		try {
			Criteria crit = sessionFactory.getCurrentSession()
					.createCriteria(Caracterestique.class)
					.add(Restrictions.eq("id", id))
					.setFetchMode("typeRessources", FetchMode.JOIN)
					;
			return (Caracterestique) crit.uniqueResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
}
