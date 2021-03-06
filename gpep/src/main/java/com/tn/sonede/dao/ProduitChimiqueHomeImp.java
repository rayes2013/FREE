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

import com.tn.sonede.persistance.ProduitChimique;

@Repository("produitChimiqueHome")
@Scope("singleton")
public class ProduitChimiqueHomeImp implements ProduitChimiqueHome{

	private static final Log log = LogFactory.getLog(ProduitChimiqueHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(ProduitChimique transientInstance) {
		log.debug("persisting ProduitChimique instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ProduitChimique instance) {
		log.debug("attaching dirty ProduitChimique instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProduitChimique instance) {
		log.debug("attaching clean ProduitChimique instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ProduitChimique persistentInstance) {
		log.debug("deleting ProduitChimique instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProduitChimique merge(ProduitChimique detachedInstance) {
		log.debug("merging ProduitChimique instance");
		try {
			ProduitChimique result = (ProduitChimique) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProduitChimique findById(Integer id) {
		log.debug("getting ProduitChimique instance with id: " + id);
		try {
			ProduitChimique instance = (ProduitChimique) sessionFactory.getCurrentSession().get(
					ProduitChimique.class, id);
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

	public List<ProduitChimique> findByExample(ProduitChimique instance) {
		log.debug("finding ProduitChimique instance by example");
		try {
			List<ProduitChimique> results = (List<ProduitChimique>) sessionFactory
					.getCurrentSession()
					.createCriteria(ProduitChimique.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<ProduitChimique> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				ProduitChimique.class);
		return (List<ProduitChimique>)crit.list();
	}

	public List<ProduitChimique> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				ProduitChimique.class);
		return (List<ProduitChimique>) crit.list();
	}

	public List<ProduitChimique> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(ProduitChimique.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<ProduitChimique>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from ProduitChimique  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}
}
