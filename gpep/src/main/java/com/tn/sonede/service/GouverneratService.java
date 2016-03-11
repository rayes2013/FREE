package com.tn.sonede.service;
import java.util.List;

import com.tn.sonede.dao.GouverneratHome;
import com.tn.sonede.persistance.Gouvernerat;
public interface GouverneratService {
	public List<Gouvernerat> getListGouvernerat() ;
	public GouverneratHome getGouverneratDAO() ;
	public void setGouverneratDAO(GouverneratHome sec);
	public void supprimerGouvernerat(Gouvernerat sec) ;
	public void mettreJourGouvernerat(Gouvernerat sec) ;
	public void ajoutGouvernerat(Gouvernerat secrage) ;
	public Gouvernerat getGouverneratByID(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
}
