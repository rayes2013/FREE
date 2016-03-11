package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.RessourceHome;
import com.tn.sonede.persistance.Ressource;

@Transactional
@Service("ressourceService")
@Scope("singleton")
public class RessourceServiceImp implements RessourceService{
	
	@Autowired
	private RessourceHome ressourceDAO;

	public List<Ressource> getListRessource() {
		return ressourceDAO.findAllWithJoins();
	}

	public RessourceHome getRessourceDAO() {
		return ressourceDAO;
	}

	public void setRessourceDAO(
			RessourceHome RessourceDAO) {
		this.ressourceDAO = RessourceDAO;
	}

	public void supprimerRessource(Ressource sec) {
		
		ressourceDAO.delete(sec);
		
	}

	public void mettreJourRessource(Ressource sec) {
		// insertion d'un Ressource
		
		ressourceDAO.attachDirty(sec);
		
	}

	public void ajoutRessource(Ressource secrage) {
		// insertion d'un Ressource
		
		ressourceDAO.persist(secrage);
		
	}

	public Ressource getRessourceByID(Integer id) {
		// TODO Auto-generated method stub
		return ressourceDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return ressourceDAO.nbreLignesWithParam(attribut,value);
	}

	public Ressource getRessourceByIDWithJoinsAvecValeurs(Integer id) {
		// TODO Auto-generated method stub
		return ressourceDAO.findByIdWithJoinsAvecValeurs(id);
	}
	
	public Ressource getRessourceByIDWithJoinsAvecValeursValide(Integer id) {
		// TODO Auto-generated method stub
		return ressourceDAO.findByIdWithJoinsAvecValeursValide(id);
	}
	
	public Ressource getRessourceByIDWithJoins(Integer id) {
		// TODO Auto-generated method stub
		return ressourceDAO.findByIdWithJoins(id);
	}

	public List<Ressource> getListRessourceAvecCP() {
		// TODO Auto-generated method stub
		return ressourceDAO.findAllWithJoinsAvecCP();
	}
	
	public Ressource getRessourceByIDAvecValeursMoisAnnee(Integer id,Integer annee, Integer mois){
		return ressourceDAO.findByIDAvecValeursMoisAnnee(id,annee,mois);
	}

}