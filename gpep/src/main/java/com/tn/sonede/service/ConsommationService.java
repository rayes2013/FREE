package com.tn.sonede.service;
import java.util.List;

import com.tn.sonede.dao.ConsommationHome;
import com.tn.sonede.persistance.Consommation;
public interface ConsommationService {
	public List<Consommation> getListConsommation() ;
	public ConsommationHome getConsommationDAO() ;
	public void setConsommationDAO(ConsommationHome sec);
	public void supprimerConsommation(Consommation sec) ;
	public void mettreJourConsommation(Consommation sec) ;
	public void ajoutConsommation(Consommation secrage) ;
	public Consommation getConsommationByID(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
}
