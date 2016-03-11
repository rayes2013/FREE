package com.tn.sonede.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.Structure;
import com.tn.sonede.service.StructureService;


@ManagedBean(name = "structureBean")
@SessionScoped
public class StructureBean implements Serializable {

	@ManagedProperty(value = "#{structureService}")
	private StructureService structureService;
	private Structure structure;
	private List<Structure> listStructures;
	private List<Structure> listStructuresChoix;
	
	public StructureService getStructureService() {
		return structureService;
	}

	public void setStructureService(StructureService structureService) {
		this.structureService = structureService;
	}

	public StructureBean() {
		structure = new Structure();
	}

	public void ajoutEvent(ActionEvent actionEvent) {
		structure = new Structure();
	}

	public void editEvent(int id) {
		structure = structureService.getStructureByID(id);
	}
	
	public void onRowSelect(Integer id) { 
		structure = structureService.getStructureByID(id);
	}

	public void update(ActionEvent actionEvent) {

		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(structure.getCode()==null||structure.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(structure.getDesignation()==null||structure.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			structureService.mettreJourStructure(structure);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Structure modifiée"));
		}

	}

	public void insert(ActionEvent actionEvent) {

		boolean testRG= true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(structure.getCode()==null||structure.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(structureService.nbreLignesWithParam("code", structure.getCode())!=0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code doit etre unique"));
			testRG= false;
		}
		if(structure.getDesignation()==null||structure.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			structureService.ajoutStructure(structure);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Structure ajoutée"));
			structure = new Structure();
		}
	}

	public void delete(Structure Structure) {
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			structure= structureService.getStructureByID(Structure.getId());
			structureService.supprimerStructure(structure);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Structure supprimée"));
			structure=new Structure();
		}catch(Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Structure affectée"));
		}
	}
	
	public List<Structure> getListStructuresChoix() {

		listStructuresChoix = structureService.getListStructure();
		if(structure!=null&&structure.getId()!=null){
			 listStructuresChoix.remove(new Structure(structure.getId()));
		}
		return listStructuresChoix;
	}

	public List<Structure> getListStructures() {

		listStructures = structureService.getListStructure();
		Collections.sort(listStructures, new Comparator<Structure>() {
			public int compare(Structure o1, Structure o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listStructures;
	}
	
	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure Structure) {
		this.structure = Structure;
	}

}