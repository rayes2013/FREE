package com.tn.sonede.service;
import java.util.List;
import com.tn.sonede.dao.RessourceHome;
import com.tn.sonede.persistance.Ressource;
public interface RessourceService {
	public List<Ressource> getListRessource() ;
	public RessourceHome getRessourceDAO() ;
	public void setRessourceDAO(RessourceHome sec);
	public void supprimerRessource(Ressource sec) ;
	public void mettreJourRessource(Ressource sec) ;
	public void ajoutRessource(Ressource secrage) ;
	public Ressource getRessourceByID(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
	public Ressource getRessourceByIDWithJoinsAvecValeurs(Integer id);
	public Ressource getRessourceByIDWithJoinsAvecValeursValide(Integer id);
	public Ressource getRessourceByIDWithJoins(Integer id);
	public List<Ressource> getListRessourceAvecCP();
	public Ressource getRessourceByIDAvecValeursMoisAnnee(Integer id,Integer annee, Integer mois);
}
