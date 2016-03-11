package com.tn.sonede.service;
import java.util.List;
import com.tn.sonede.dao.ValeurCaracterestiqueHome;
import com.tn.sonede.persistance.ValeurCaracterestique;
public interface ValeurCaracterestiqueService {
	public List<ValeurCaracterestique> getListValeurCaracterestique() ;
	public ValeurCaracterestiqueHome getValeurCaracterestiqueDAO() ;
	public void setValeurCaracterestiqueDAO(ValeurCaracterestiqueHome sec);
	public void supprimerValeurCaracterestique(ValeurCaracterestique sec) ;
	public void mettreJourValeurCaracterestique(ValeurCaracterestique sec) ;
	public void ajoutValeurCaracterestique(ValeurCaracterestique secrage) ;
	public ValeurCaracterestique getValeurCaracterestiqueByID(Integer id) ;
	public ValeurCaracterestique getValeurCaracterestiqueByIDWithAllJoins(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
}
