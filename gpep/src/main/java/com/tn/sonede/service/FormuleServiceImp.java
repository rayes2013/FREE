package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.FormuleHome;
import com.tn.sonede.persistance.Formule;

@Transactional
@Service("formuleService")
@Scope("singleton")
public class FormuleServiceImp implements FormuleService{
	
	@Autowired
	private FormuleHome formuleDAO;

	public List<Formule> getListFormule() {
		return formuleDAO.findAllWithJoins();
	}

	public FormuleHome getFormuleDAO() {
		return formuleDAO;
	}

	public void setFormuleDAO(
			FormuleHome FormuleDAO) {
		this.formuleDAO = FormuleDAO;
	}

	public void supprimerFormule(Formule sec) {
		
		formuleDAO.delete(sec);
		
	}

	public void mettreJourFormule(Formule sec) {
		// insertion d'un Formule
		
		formuleDAO.attachDirty(sec);
		
	}

	public void ajoutFormule(Formule secrage) {
		// insertion d'un Formule
		
		formuleDAO.persist(secrage);
		
	}

	public Formule getFormuleByID(Integer id) {
		// TODO Auto-generated method stub
		return formuleDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return formuleDAO.nbreLignesWithParam(attribut,value);
	}

}