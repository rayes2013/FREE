package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.GouverneratHome;
import com.tn.sonede.persistance.Gouvernerat;

@Transactional
@Service("gouverneratService")
@Scope("singleton")
public class GouverneratServiceImp implements GouverneratService{
	
	@Autowired
	private GouverneratHome gouverneratDAO;

	public List<Gouvernerat> getListGouvernerat() {
		return gouverneratDAO.findAllWithJoins();
	}

	public GouverneratHome getGouverneratDAO() {
		return gouverneratDAO;
	}

	public void setGouverneratDAO(
			GouverneratHome GouverneratDAO) {
		this.gouverneratDAO = GouverneratDAO;
	}

	public void supprimerGouvernerat(Gouvernerat sec) {
		
		gouverneratDAO.delete(sec);
		
	}

	public void mettreJourGouvernerat(Gouvernerat sec) {
		// insertion d'un Gouvernerat
		
		gouverneratDAO.attachDirty(sec);
		
	}

	public void ajoutGouvernerat(Gouvernerat secrage) {
		// insertion d'un Gouvernerat
		
		gouverneratDAO.persist(secrage);
		
	}

	public Gouvernerat getGouverneratByID(Integer id) {
		// TODO Auto-generated method stub
		return gouverneratDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return gouverneratDAO.nbreLignesWithParam(attribut,value);
	}

}