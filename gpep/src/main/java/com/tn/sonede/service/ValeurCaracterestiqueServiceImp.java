package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.ValeurCaracterestiqueHome;
import com.tn.sonede.persistance.ValeurCaracterestique;

@Transactional
@Service("valeurCaracterestiqueService")
@Scope("singleton")
public class ValeurCaracterestiqueServiceImp implements ValeurCaracterestiqueService{
	
	@Autowired
	private ValeurCaracterestiqueHome valeurCaracterestiqueDAO;

	public List<ValeurCaracterestique> getListValeurCaracterestique() {
		return valeurCaracterestiqueDAO.findAll();
	}

	public ValeurCaracterestiqueHome getValeurCaracterestiqueDAO() {
		return valeurCaracterestiqueDAO;
	}

	public void setValeurCaracterestiqueDAO(
			ValeurCaracterestiqueHome ValeurCaracterestiqueDAO) {
		this.valeurCaracterestiqueDAO = ValeurCaracterestiqueDAO;
	}

	public void supprimerValeurCaracterestique(ValeurCaracterestique sec) {
		
		valeurCaracterestiqueDAO.delete(sec);
		
	}

	public void mettreJourValeurCaracterestique(ValeurCaracterestique sec) {
		// insertion d'un ValeurCaracterestique
		
		valeurCaracterestiqueDAO.attachDirty(sec);
		
	}

	public void ajoutValeurCaracterestique(ValeurCaracterestique secrage) {
		// insertion d'un ValeurCaracterestique
		
		valeurCaracterestiqueDAO.persist(secrage);
		
	}

	public ValeurCaracterestique getValeurCaracterestiqueByID(Integer id) {
		// TODO Auto-generated method stub
		return valeurCaracterestiqueDAO.findById(id);
	}
	
	public ValeurCaracterestique getValeurCaracterestiqueByIDWithAllJoins(Integer id) {
		// TODO Auto-generated method stub
		return valeurCaracterestiqueDAO.findByIdWithJoins(id);
	}
	
	public long  nbreLignesWithParam(String attribut,Object value) {
		return valeurCaracterestiqueDAO.nbreLignesWithParam(attribut,value);
	}

	

}