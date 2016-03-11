package com.tn.sonede.service;
import java.util.List;

import com.tn.sonede.dao.StructureHome;
import com.tn.sonede.persistance.Structure;
public interface StructureService {
	public List<Structure> getListStructure() ;
	public StructureHome getStructureDAO() ;
	public void setStructureDAO(StructureHome sec);
	public void supprimerStructure(Structure sec) ;
	public void mettreJourStructure(Structure sec) ;
	public void ajoutStructure(Structure secrage) ;
	public Structure getStructureByID(Integer id) ;
	public long nbreLignesWithParam(String attribut,Object value) ;
}
