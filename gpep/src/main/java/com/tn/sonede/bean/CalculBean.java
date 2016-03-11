package com.tn.sonede.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableStatement;

import com.tn.sonede.persistance.Agent;
import com.tn.sonede.persistance.Caracterestique;
import com.tn.sonede.persistance.Formule;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.Structure;
import com.tn.sonede.persistance.TypeRessource;
import com.tn.sonede.persistance.ValeurCaracterestique;
import com.tn.sonede.service.RessourceService;
import com.tn.sonede.service.StructureService;
import com.tn.sonede.service.TypeRessourceService;
import com.tn.sonede.service.ValeurCaracterestiqueService;

@ManagedBean(name = "calculBean")
@SessionScoped
public class CalculBean implements Serializable {

	@ManagedProperty(value = "#{ressourceService}")
	private RessourceService ressourceService;
	@ManagedProperty(value = "#{structureService}")
	private StructureService structureService;
	@ManagedProperty(value = "#{typeRessourceService}")
	private TypeRessourceService typeRessourceService;
	private Ressource ressource;
	private List<Formule> listFormules;
	private List<Ressource> listRessources;
	private List<TypeRessource> listTypeRessources;
	private List<Structure> listStructures;
	private List<Integer> listAnnees;
	private List<Caracterestique> listCaracterestiques;
	
	

	public RessourceService getRessourceService() {
		return ressourceService;
	}

	public void setRessourceService(RessourceService ressourceService) {
		this.ressourceService = ressourceService;
	}

	public StructureService getStructureService() {
		return structureService;
	}

	public void setStructureService(StructureService structureService) {
		this.structureService = structureService;
	}

	public TypeRessourceService getTypeRessourceService() {
		return typeRessourceService;
	}

	public void setTypeRessourceService(TypeRessourceService typeRessourceService) {
		this.typeRessourceService = typeRessourceService;
	}


	public List<Caracterestique> getListCaracterestiques() {
		return listCaracterestiques;
	}

	
	public List<TypeRessource> getListTypeRessources() {
		listTypeRessources=typeRessourceService.getListTypeRessource();
		return listTypeRessources;
	}

	public List<Structure> getListStructures() {
		listStructures=structureService.getListStructure();
		return listStructures;
	}

	public List<Integer> getListAnnees() {
		listAnnees=new ArrayList<Integer>();
		for(int i=1950;i<2050;i++)
			listAnnees.add(i);
		return listAnnees;
	}
	
	public CalculBean() {
		ressource = new Ressource();
		listCaracterestiques=new ArrayList<Caracterestique>();
		listFormules = new ArrayList<Formule>();
	}

	
	
	public List<Formule> getListFormules() {
		return listFormules;
	}

	public void onRowSelect(Integer id) { 
		FacesContext context = FacesContext.getCurrentInstance();
		
		ressource = ressourceService.getRessourceByIDWithJoinsAvecValeursValide(id);
		if(ressource==null)
			ressource = ressourceService.getRessourceByIDWithJoins(id);
		listFormules=new ArrayList<Formule>(ressource.getTypeRessource().getFormules());
		listCaracterestiques=new ArrayList<Caracterestique>(ressource.getTypeRessource().getCaracterestiques());
		filtreListeCaracterestiques();
		for(int i=0;i<listCaracterestiques.size();i++) {
			Caracterestique e = listCaracterestiques.get(i);
			e.init();
		}
		for(int i=0;i<listFormules.size();i++) {
			Formule e = listFormules.get(i);
			e.eval(listCaracterestiques);
		}
	}
	
	private void filtreListeCaracterestiques() {
		for(Caracterestique c:listCaracterestiques) {
			List<ValeurCaracterestique> listValeurs=new ArrayList<ValeurCaracterestique>(c.getValeurs());
			for(ValeurCaracterestique e:listValeurs){
				if(e.getRessource().getId()!=ressource.getId()) {
					c.getValeurs().remove(e);
				}
			}
		}
	}

	public List<Ressource> getListRessources() {
		listRessources = ressourceService.getListRessource();
		Collections.sort(listRessources, new Comparator<Ressource>() {
			public int compare(Ressource o1, Ressource o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listRessources;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource Ressource) {
		this.ressource = Ressource;
	}
	
}