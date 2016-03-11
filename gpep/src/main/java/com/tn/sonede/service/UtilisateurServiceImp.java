package com.tn.sonede.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tn.sonede.dao.UtilisateurHome;
import com.tn.sonede.persistance.Utilisateur;

@Transactional
@Service("utilisateurService")
@Scope("prototype")
public class UtilisateurServiceImp implements UtilisateurService{
	
	@Autowired
	private UtilisateurHome utilisateurDAO;

	public List<Utilisateur> getListUtilisateur() {
		return utilisateurDAO.findAllWithJoins();
	}

	public UtilisateurHome getUtilisateurDAO() {
		return utilisateurDAO;
	}

	public void setUtilisateurDAO(
			UtilisateurHome UtilisateurDAO) {
		this.utilisateurDAO = UtilisateurDAO;
	}

	public void supprimerUtilisateur(Utilisateur sec) {
		
		utilisateurDAO.delete(sec);
		
	}

	public void mettreJourUtilisateur(Utilisateur sec) {
		// insertion d'un Utilisateur
		
		utilisateurDAO.attachDirty(sec);
		
	}

	public void ajoutUtilisateur(Utilisateur secrage) {
		// insertion d'un Utilisateur
		
		utilisateurDAO.persist(secrage);
		
	}

	public Utilisateur getUtilisateurByID(String id) {
		// TODO Auto-generated method stub
		return utilisateurDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return utilisateurDAO.nbreLignesWithParam(attribut,value);
	}
	

}