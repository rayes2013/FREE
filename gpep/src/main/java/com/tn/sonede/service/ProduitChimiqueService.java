package com.tn.sonede.service;
import java.util.List;

import com.tn.sonede.dao.ProduitChimiqueHome;
import com.tn.sonede.persistance.ProduitChimique;
public interface ProduitChimiqueService {
	public List<ProduitChimique> getListProduitChimique() ;
	public ProduitChimiqueHome getProduitChimiqueDAO() ;
	public void setProduitChimiqueDAO(ProduitChimiqueHome sec);
	public void supprimerProduitChimique(ProduitChimique sec) ;
	public void mettreJourProduitChimique(ProduitChimique sec) ;
	public void ajoutProduitChimique(ProduitChimique secrage) ;
	public ProduitChimique getProduitChimiqueByID(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
}
