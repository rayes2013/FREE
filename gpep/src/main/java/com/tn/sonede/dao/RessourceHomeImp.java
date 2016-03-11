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

@Repository("ressourceHome")
@Scope("singleton")
public class RessourceHomeImp implements RessourceHome{

	private static final Log log = LogFactory.getLog(RessourceHomeImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public void persist(Ressource transientInstance) {
		log.debug("persisting Ressource instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Ressource instance) {
		log.debug("attaching dirty Ressource instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ressource instance) {
		log.debug("attaching clean Ressource instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Ressource persistentInstance) {
		log.debug("deleting Ressource instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ressource merge(Ressource detachedInstance) {
		log.debug("merging Ressource instance");
		try {
			Ressource result = (Ressource) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Ressource findById(Integer id) {
		log.debug("getting Ressource instance with id: " + id);
		try {
			Ressource instance = (Ressource) sessionFactory.getCurrentSession().get(
					Ressource.class, id);
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

	public List<Ressource> findByExample(Ressource instance) {
		log.debug("finding Ressource instance by example");
		try {
			List<Ressource> results = (List<Ressource>) sessionFactory
					.getCurrentSession()
					.createCriteria(Ressource.class)
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Ressource> findAll() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Ressource.class);
		return (List<Ressource>)crit.list();
	}

	public List<Ressource> findAllWithJoins() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				Ressource.class);
		return (List<Ressource>) crit.list();
	}

	public List<Ressource> findAllWithJoins(String param) {
		Criteria crit = sessionFactory.getCurrentSession()
				.createCriteria(Ressource.class)
				.setFetchMode(param, FetchMode.JOIN);
		return (List<Ressource>) crit.list();
	}
	
	public long nbreLignesWithParam(String attribut, Object value) {
		Long nb= (Long)sessionFactory.getCurrentSession()
					.createQuery("Select count(*) " +
							" from Ressource  " +
							" where "+attribut+"=?")
							.setString(0, (String) value)
							.uniqueResult();
			return nb;
	}
	
	public Ressource findByIdWithJoinsAvecValeurs(Integer id){
		Ressource res= (Ressource)sessionFactory.getCurrentSession()
				.createQuery( " from Ressource r full join fetch r.typeRessource t full join fetch t.formules full join fetch t.caracterestiques c full join fetch c.valeurs v full join fetch v.ressource r1 " +
						" where r.id=? " )
						.setInteger(0, id)
						.uniqueResult();
		return res;
	}
	
	public Ressource findByIdWithJoinsAvecValeursValide(Integer id){
		Ressource res= (Ressource)sessionFactory.getCurrentSession()
				.createQuery( " from Ressource r full join fetch r.typeRessource t full join fetch t.formules full join fetch t.caracterestiques c full join fetch c.valeurs v full join fetch v.ressource r1 " +
						" where r.id=? and v.valide=true " )
						.setInteger(0, id)
						.uniqueResult();
		return res;
	}
	
	public Ressource findByIdWithJoins(Integer id){
		log.debug("getting TypeRessource instance with id: " + id);
		try {
			Criteria crit = sessionFactory.getCurrentSession()
					.createCriteria(Ressource.class)
					.add(Restrictions.eq("id", id))
					.setFetchMode("structure", FetchMode.JOIN)
					.setFetchMode("gouvernerat", FetchMode.JOIN)
					.setFetchMode("typeRessource", FetchMode.JOIN)
					.setFetchMode("typeRessource.formules", FetchMode.JOIN)
					.setFetchMode("consommations", FetchMode.JOIN)
					.setFetchMode("typeRessource.caracterestiques", FetchMode.JOIN)
					.setFetchMode("typeRessource.caracterestiques.valeurs", FetchMode.JOIN)
					.setFetchMode("typeRessource.caracterestiques.valeurs.ressource", FetchMode.JOIN)
					;
			return (Ressource) crit.uniqueResult();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		
	}

	public List<Ressource> findAllWithJoinsAvecCP() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Ressource.class)
				.setFetchMode("typeRessource", FetchMode.JOIN)
				.createCriteria("typeRessource")
				.add(Restrictions.eq("consommationPC", true));
		return (List<Ressource>)crit.list();
	}
	
	public Ressource findByIDAvecValeursMoisAnnee(Integer id, Integer annee,Integer mois) {
		Ressource res= (Ressource)sessionFactory.getCurrentSession()
				.createQuery( " from Ressource r  full join fetch r.valeurs v full join fetch v.caracterestique c " +
						" where r.id=? and ( ((month(v.dtModif)=? and year(v.dtModif)=?) or v is null ) or c.nature=0)" )
						.setInteger(0, id)
						.setInteger(1, mois)
						.setInteger(2, annee)
						.uniqueResult();
		return res;
	}
	
}
