package com.tn.sonede.dao;

// Generated 18 avr. 2014 11:37:50 by Hibernate Tools 3.4.0.CR1

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

import com.tn.sonede.persistance.Agent;




@Repository("agentHome")
@Scope("prototype")
public class AgentHomeImp implements AgentHome{

	private static final Log log = LogFactory.getLog(AgentHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Agent transientInstance) {
		log.debug("persisting Agent instance");
		transientInstance.getNom();
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Agent instance) {
		log.debug("attaching dirty Agent instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Agent instance) {
		log.debug("attaching clean Agent instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Agent persistentInstance) {
		log.debug("deleting Agent instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Agent merge(Agent detachedInstance) {
		log.debug("merging Agent instance");
		try {
			Agent result = (Agent) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Agent findById(int id) {
		log.debug("getting Agent instance with id: " + id);
		try {
			Agent instance = (Agent) sessionFactory.getCurrentSession().get(
					Agent.class, id);
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

	public List<Agent> findByExample(Agent instance) {
		log.debug("finding Agent instance by example");
		try {
			List<Agent> results = (List<Agent>) sessionFactory
					.getCurrentSession()
					.createCriteria(Agent.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Agent> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Agent.class);
		return (List<Agent>)crit.list();
	}

	public List<Agent> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Agent.class);
		return (List<Agent>) crit.list();
	}

	public List<Agent> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Agent.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<Agent>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from Agent  " +
							" where "+attribut+"=?")
							.setInteger(0, (Integer) value)
							.uniqueResult();
			return nb;
	}
	
}
