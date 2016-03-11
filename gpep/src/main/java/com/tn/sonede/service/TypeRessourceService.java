package com.tn.sonede.service;
import java.util.List;
import com.tn.sonede.dao.TypeRessourceHome;
import com.tn.sonede.persistance.TypeRessource;
public interface TypeRessourceService {
	public List<TypeRessource> getListTypeRessource() ;
	public TypeRessourceHome getTypeRessourceDAO() ;
	public void setTypeRessourceDAO(TypeRessourceHome sec);
	public void supprimerTypeRessource(TypeRessource sec) ;
	public void mettreJourTypeRessource(TypeRessource sec) ;
	public void ajoutTypeRessource(TypeRessource secrage) ;
	public TypeRessource getTypeRessourceByID(Integer id) ;
	public TypeRessource getTypeRessourceByIDWithAllJoins(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
	public TypeRessource getCaracterestiquesduTypeRessourceByID(Integer id,int i);
	public TypeRessource getTypeRessourceByIDWithAllJoinsAvecValeurValideDate(Integer id, Integer annee, Integer mois);
}
