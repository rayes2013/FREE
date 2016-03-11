package com.tn.sonede.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tn.sonede.dao.StructureHome;
import com.tn.sonede.persistance.Structure;

@Transactional
@Service("structureService")
@Scope("singleton")
public class StructureServiceImp implements StructureService{
	
	@Autowired
	private StructureHome structureDAO;

	public List<Structure> getListStructure() {
		return structureDAO.findAllWithJoins();
	}

	public StructureHome getStructureDAO() {
		return structureDAO;
	}

	public void setStructureDAO(
			StructureHome StructureDAO) {
		this.structureDAO = StructureDAO;
	}

	public void supprimerStructure(Structure sec) {
		
		structureDAO.delete(sec);
		
	}

	public void mettreJourStructure(Structure sec) {
		// insertion d'un Structure
		
		structureDAO.attachDirty(sec);
		
	}

	public void ajoutStructure(Structure secrage) {
		// insertion d'un Structure
		
		structureDAO.persist(secrage);
		
	}

	public Structure getStructureByID(Integer id) {
		// TODO Auto-generated method stub
		return structureDAO.findById(id);
	}

	public long  nbreLignesWithParam(String attribut,Object value) {
		return structureDAO.nbreLignesWithParam(attribut,value);
	}

}