package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.CaracterestiqueHome;
import com.tn.sonede.persistance.Caracterestique;

@Transactional
@Service("caracterestiqueService")
@Scope("singleton")
public class CaracterestiqueServiceImp implements CaracterestiqueService{
	
	@Autowired
	private CaracterestiqueHome caracterestiqueDAO;

	public List<Caracterestique> getListCaracterestique() {
		return caracterestiqueDAO.findAllWithJoins();
	}

	public CaracterestiqueHome getCaracterestiqueDAO() {
		return caracterestiqueDAO;
	}

	public void setCaracterestiqueDAO(
			CaracterestiqueHome CaracterestiqueDAO) {
		this.caracterestiqueDAO = CaracterestiqueDAO;
	}

	public void supprimerCaracterestique(Caracterestique sec) {
		
		caracterestiqueDAO.delete(sec);
		
	}

	public void mettreJourCaracterestique(Caracterestique sec) {
		// insertion d'un Caracterestique
		
		caracterestiqueDAO.attachDirty(sec);
		
	}

	public void ajoutCaracterestique(Caracterestique secrage) {
		// insertion d'un Caracterestique
		
		caracterestiqueDAO.persist(secrage);
		
	}

	public Caracterestique getCaracterestiqueByID(Integer id) {
		// TODO Auto-generated method stub
		return caracterestiqueDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return caracterestiqueDAO.nbreLignesWithParam(attribut,value);
	}
	
	public List<Caracterestique> getListCaracterestiqueASelectionner(Integer id) {
		return caracterestiqueDAO.findAllCaracterestiqueASelectionner(id);
	}
	
	public Caracterestique getCaracterestiqueByIDWithJoins(Integer id) {
		return caracterestiqueDAO.findCaracterestiqueByIdWithAllJoins(id);
	}

}