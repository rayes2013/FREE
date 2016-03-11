package com.tn.sonede.service;
import java.util.List;

import com.tn.sonede.dao.UtilisateurHome;
import com.tn.sonede.persistance.Utilisateur;
public interface UtilisateurService {
	public List<Utilisateur> getListUtilisateur() ;
	public UtilisateurHome getUtilisateurDAO() ;
	public void setUtilisateurDAO(UtilisateurHome sec);
	public void supprimerUtilisateur(Utilisateur sec) ;
	public void mettreJourUtilisateur(Utilisateur sec) ;
	public void ajoutUtilisateur(Utilisateur secrage) ;
	public Utilisateur getUtilisateurByID(String id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;

}
