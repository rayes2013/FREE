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

import com.tn.sonede.persistance.Structure;

@Repository("structureHome")
@Scope("singleton")
public class StructureHomeImp implements StructureHome{

	private static final Log log = LogFactory.getLog(StructureHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Structure transientInstance) {
		log.debug("persisting Structure instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Structure instance) {
		log.debug("attaching dirty Structure instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Structure instance) {
		log.debug("attaching clean Structure instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Structure persistentInstance) {
		log.debug("deleting Structure instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Structure merge(Structure detachedInstance) {
		log.debug("merging Structure instance");
		try {
			Structure result = (Structure) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Structure findById(Integer id) {
		log.debug("getting Structure instance with id: " + id);
		try {
			Structure instance = (Structure) sessionFactory.getCurrentSession().get(
					Structure.class, id);
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

	public List<Structure> findByExample(Structure instance) {
		log.debug("finding Structure instance by example");
		try {
			List<Structure> results = (List<Structure>) sessionFactory
					.getCurrentSession()
					.createCriteria(Structure.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Structure> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Structure.class);
		return (List<Structure>)crit.list();
	}

	public List<Structure> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Structure.class);
		return (List<Structure>) crit.list();
	}

	public List<Structure> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Structure.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<Structure>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from Structure  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}
}
