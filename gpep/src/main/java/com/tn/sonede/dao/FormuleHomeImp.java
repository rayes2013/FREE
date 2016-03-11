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
import com.tn.sonede.persistance.Formule;

@Repository("formuleHome")
@Scope("singleton")
public class FormuleHomeImp implements FormuleHome{

	private static final Log log = LogFactory.getLog(FormuleHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Formule transientInstance) {
		log.debug("persisting Formule instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Formule instance) {
		log.debug("attaching dirty Formule instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Formule instance) {
		log.debug("attaching clean Formule instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Formule persistentInstance) {
		log.debug("deleting Formule instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Formule merge(Formule detachedInstance) {
		log.debug("merging Formule instance");
		try {
			Formule result = (Formule) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Formule findById(Integer id) {
		log.debug("getting Formule instance with id: " + id);
		try {
			Formule instance = (Formule) sessionFactory.getCurrentSession().get(
					Formule.class, id);
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

	public List<Formule> findByExample(Formule instance) {
		log.debug("finding Formule instance by example");
		try {
			List<Formule> results = (List<Formule>) sessionFactory
					.getCurrentSession()
					.createCriteria(Formule.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Formule> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Formule.class);
		return (List<Formule>)crit.list();
	}

	public List<Formule> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Formule.class);
		return (List<Formule>) crit.list();
	}

	public List<Formule> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Formule.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<Formule>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from Formule  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}
}
