package com.tn.sonede.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.tn.sonede.persistance.Consommation;

@Repository("consommationHome")
@Scope("singleton")
public class ConsommationHomeImp implements ConsommationHome{

	private static final Log log = LogFactory.getLog(ConsommationHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Consommation transientInstance) {
		log.debug("persisting Consommation instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Consommation instance) {
		log.debug("attaching dirty Consommation instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Consommation instance) {
		log.debug("attaching clean Consommation instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Consommation persistentInstance) {
		log.debug("deleting Consommation instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Consommation merge(Consommation detachedInstance) {
		log.debug("merging Consommation instance");
		try {
			Consommation result = (Consommation) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Consommation findById(Integer id) {
		log.debug("getting Consommation instance with id: " + id);
		try {
			Consommation instance = (Consommation) sessionFactory.getCurrentSession().get(
					Consommation.class, id);
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

	public List<Consommation> findByExample(Consommation instance) {
		log.debug("finding Consommation instance by example");
		try {
			List<Consommation> results = (List<Consommation>) sessionFactory
					.getCurrentSession()
					.createCriteria(Consommation.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Consommation> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Consommation.class);
		return (List<Consommation>)crit.list();
	}

	public List<Consommation> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Consommation.class);
		return (List<Consommation>) crit.list();
	}

	public List<Consommation> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Consommation.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<Consommation>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from Consommation  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}
}
