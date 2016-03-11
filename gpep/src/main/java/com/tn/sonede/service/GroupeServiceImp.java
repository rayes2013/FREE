package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.GroupeHome;
import com.tn.sonede.persistance.Groupe;

@Transactional
@Service("groupeService")
@Scope("singleton")
public class GroupeServiceImp implements GroupeService{
	
	@Autowired
	private GroupeHome groupeDAO;

	public List<Groupe> getListGroupe() {
		return groupeDAO.findAllWithJoins();
	}

	public GroupeHome getGroupeDAO() {
		return groupeDAO;
	}

	public void setGroupeDAO(
			GroupeHome GroupeDAO) {
		this.groupeDAO = GroupeDAO;
	}

	public void supprimerGroupe(Groupe sec) {
		
		groupeDAO.delete(sec);
		
	}

	public void mettreJourGroupe(Groupe sec) {
		// insertion d'un Groupe
		
		groupeDAO.attachDirty(sec);
		
	}

	public void ajoutGroupe(Groupe secrage) {
		// insertion d'un Groupe
		
		groupeDAO.persist(secrage);
		
	}

	public Groupe getGroupeByID(Integer id) {
		// TODO Auto-generated method stub
		return groupeDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return groupeDAO.nbreLignesWithParam(attribut,value);
	}

}