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

import com.tn.sonede.persistance.Gouvernerat;

@Repository("gouverneratHome")
@Scope("singleton")
public class GouverneratHomeImp implements GouverneratHome{

	private static final Log log = LogFactory.getLog(GouverneratHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Gouvernerat transientInstance) {
		log.debug("persisting Gouvernerat instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Gouvernerat instance) {
		log.debug("attaching dirty Gouvernerat instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Gouvernerat instance) {
		log.debug("attaching clean Gouvernerat instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Gouvernerat persistentInstance) {
		log.debug("deleting Gouvernerat instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Gouvernerat merge(Gouvernerat detachedInstance) {
		log.debug("merging Gouvernerat instance");
		try {
			Gouvernerat result = (Gouvernerat) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Gouvernerat findById(Integer id) {
		log.debug("getting Gouvernerat instance with id: " + id);
		try {
			Gouvernerat instance = (Gouvernerat) sessionFactory.getCurrentSession().get(
					Gouvernerat.class, id);
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

	public List<Gouvernerat> findByExample(Gouvernerat instance) {
		log.debug("finding Gouvernerat instance by example");
		try {
			List<Gouvernerat> results = (List<Gouvernerat>) sessionFactory
					.getCurrentSession()
					.createCriteria(Gouvernerat.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Gouvernerat> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Gouvernerat.class);
		return (List<Gouvernerat>)crit.list();
	}

	public List<Gouvernerat> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Gouvernerat.class);
		return (List<Gouvernerat>) crit.list();
	}

	public List<Gouvernerat> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Gouvernerat.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<Gouvernerat>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from Gouvernerat  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}
}
