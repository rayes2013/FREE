package com.tn.sonede.service;
import java.util.List;
import com.tn.sonede.dao.FormuleHome;
import com.tn.sonede.persistance.Formule;
public interface FormuleService {
	public List<Formule> getListFormule() ;
	public FormuleHome getFormuleDAO() ;
	public void setFormuleDAO(FormuleHome sec);
	public void supprimerFormule(Formule sec) ;
	public void mettreJourFormule(Formule sec) ;
	public void ajoutFormule(Formule secrage) ;
	public Formule getFormuleByID(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
}
