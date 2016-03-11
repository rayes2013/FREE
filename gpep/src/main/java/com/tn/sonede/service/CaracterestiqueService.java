package com.tn.sonede.service;
import java.util.List;
import com.tn.sonede.dao.CaracterestiqueHome;
import com.tn.sonede.persistance.Caracterestique;
public interface CaracterestiqueService {
	public List<Caracterestique> getListCaracterestique() ;
	public CaracterestiqueHome getCaracterestiqueDAO() ;
	public void setCaracterestiqueDAO(CaracterestiqueHome sec);
	public void supprimerCaracterestique(Caracterestique sec) ;
	public void mettreJourCaracterestique(Caracterestique sec) ;
	public void ajoutCaracterestique(Caracterestique secrage) ;
	public Caracterestique getCaracterestiqueByID(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
	public List<Caracterestique> getListCaracterestiqueASelectionner(Integer id);
	public Caracterestique getCaracterestiqueByIDWithJoins(Integer id);
}
