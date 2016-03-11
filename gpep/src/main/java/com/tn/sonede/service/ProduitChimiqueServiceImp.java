package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.ProduitChimiqueHome;
import com.tn.sonede.persistance.ProduitChimique;

@Transactional
@Service("produitChimiqueService")
@Scope("singleton")
public class ProduitChimiqueServiceImp implements ProduitChimiqueService{
	
	@Autowired
	private ProduitChimiqueHome produitChimiqueDAO;

	public List<ProduitChimique> getListProduitChimique() {
		return produitChimiqueDAO.findAllWithJoins();
	}

	public ProduitChimiqueHome getProduitChimiqueDAO() {
		return produitChimiqueDAO;
	}

	public void setProduitChimiqueDAO(
			ProduitChimiqueHome ProduitChimiqueDAO) {
		this.produitChimiqueDAO = ProduitChimiqueDAO;
	}

	public void supprimerProduitChimique(ProduitChimique sec) {
		
		produitChimiqueDAO.delete(sec);
		
	}

	public void mettreJourProduitChimique(ProduitChimique sec) {
		// insertion d'un ProduitChimique
		
		produitChimiqueDAO.attachDirty(sec);
		
	}

	public void ajoutProduitChimique(ProduitChimique secrage) {
		// insertion d'un ProduitChimique
		
		produitChimiqueDAO.persist(secrage);
		
	}

	public ProduitChimique getProduitChimiqueByID(Integer id) {
		// TODO Auto-generated method stub
		return produitChimiqueDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return produitChimiqueDAO.nbreLignesWithParam(attribut,value);
	}

}