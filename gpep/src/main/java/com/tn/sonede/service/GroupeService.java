package com.tn.sonede.service;
import java.util.List;

import com.tn.sonede.dao.GroupeHome;
import com.tn.sonede.persistance.Groupe;
public interface GroupeService {
	public List<Groupe> getListGroupe() ;
	public GroupeHome getGroupeDAO() ;
	public void setGroupeDAO(GroupeHome sec);
	public void supprimerGroupe(Groupe sec) ;
	public void mettreJourGroupe(Groupe sec) ;
	public void ajoutGroupe(Groupe secrage) ;
	public Groupe getGroupeByID(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
}
