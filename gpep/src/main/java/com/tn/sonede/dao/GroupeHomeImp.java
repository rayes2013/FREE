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

import com.tn.sonede.persistance.Groupe;

@Repository("groupeHome")
@Scope("singleton")
public class GroupeHomeImp implements GroupeHome{

	private static final Log log = LogFactory.getLog(GroupeHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Groupe transientInstance) {
		log.debug("persisting Groupe instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Groupe instance) {
		log.debug("attaching dirty Groupe instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Groupe instance) {
		log.debug("attaching clean Groupe instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Groupe persistentInstance) {
		log.debug("deleting Groupe instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Groupe merge(Groupe detachedInstance) {
		log.debug("merging Groupe instance");
		try {
			Groupe result = (Groupe) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Groupe findById(Integer id) {
		log.debug("getting Groupe instance with id: " + id);
		try {
			Groupe instance = (Groupe) sessionFactory.getCurrentSession().get(
					Groupe.class, id);
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

	public List<Groupe> findByExample(Groupe instance) {
		log.debug("finding Groupe instance by example");
		try {
			List<Groupe> results = (List<Groupe>) sessionFactory
					.getCurrentSession()
					.createCriteria(Groupe.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Groupe> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Groupe.class);
		return (List<Groupe>)crit.list();
	}

	public List<Groupe> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Groupe.class);
		return (List<Groupe>) crit.list();
	}

	public List<Groupe> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Groupe.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<Groupe>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from Groupe  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}
}
