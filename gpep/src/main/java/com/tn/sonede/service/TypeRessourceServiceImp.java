package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.TypeRessourceHome;
import com.tn.sonede.persistance.TypeRessource;

@Transactional
@Service("typeRessourceService")
@Scope("singleton")
public class TypeRessourceServiceImp implements TypeRessourceService{
	
	@Autowired
	private TypeRessourceHome typeRessourceDAO;

	public List<TypeRessource> getListTypeRessource() {
		return typeRessourceDAO.findAll();
	}

	public TypeRessourceHome getTypeRessourceDAO() {
		return typeRessourceDAO;
	}

	public void setTypeRessourceDAO(
			TypeRessourceHome TypeRessourceDAO) {
		this.typeRessourceDAO = TypeRessourceDAO;
	}

	public void supprimerTypeRessource(TypeRessource sec) {
		
		typeRessourceDAO.delete(sec);
		
	}

	public void mettreJourTypeRessource(TypeRessource sec) {
		// insertion d'un TypeRessource
		
		typeRessourceDAO.attachDirty(sec);
		
	}

	public void ajoutTypeRessource(TypeRessource secrage) {
		// insertion d'un TypeRessource
		
		typeRessourceDAO.persist(secrage);
		
	}

	public TypeRessource getTypeRessourceByID(Integer id) {
		// TODO Auto-generated method stub
		return typeRessourceDAO.findById(id);
	}
	
	public TypeRessource getTypeRessourceByIDWithAllJoins(Integer id) {
		// TODO Auto-generated method stub
		return typeRessourceDAO.findByIdWithJoins(id);
	}
	
	public long  nbreLignesWithParam(String attribut,Object value) {
		return typeRessourceDAO.nbreLignesWithParam(attribut,value);
	}

	public TypeRessource getCaracterestiquesduTypeRessourceByID(Integer id,
			int i) {
		// TODO Auto-generated method stub
		return typeRessourceDAO.getCaracterestiquesduTypeRessourceByID(id,i);
	}

	public TypeRessource getTypeRessourceByIDWithAllJoinsAvecValeurValideDate(Integer id, Integer annee, Integer mois){
		return typeRessourceDAO.getTypeRessourceByIDWithAllJoinsAvecValeurValideDate(id,annee,mois);
	}

}