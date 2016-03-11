package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.ConsommationHome;
import com.tn.sonede.persistance.Consommation;

@Transactional
@Service("consommationService")
@Scope("singleton")
public class ConsommationServiceImp implements ConsommationService{
	
	@Autowired
	private ConsommationHome consommationDAO;

	public List<Consommation> getListConsommation() {
		return consommationDAO.findAllWithJoins();
	}

	public ConsommationHome getConsommationDAO() {
		return consommationDAO;
	}

	public void setConsommationDAO(
			ConsommationHome ConsommationDAO) {
		this.consommationDAO = ConsommationDAO;
	}

	public void supprimerConsommation(Consommation sec) {
		
		consommationDAO.delete(sec);
		
	}

	public void mettreJourConsommation(Consommation sec) {
		// insertion d'un Consommation
		
		consommationDAO.attachDirty(sec);
		
	}

	public void ajoutConsommation(Consommation secrage) {
		// insertion d'un Consommation
		
		consommationDAO.persist(secrage);
		
	}

	public Consommation getConsommationByID(Integer id) {
		// TODO Auto-generated method stub
		return consommationDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return consommationDAO.nbreLignesWithParam(attribut,value);
	}

}