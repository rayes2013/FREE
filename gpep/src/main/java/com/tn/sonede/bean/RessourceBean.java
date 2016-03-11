package com.tn.sonede.bean;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tn.sonede.persistance.Caracterestique;
import com.tn.sonede.persistance.Gouvernerat;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.Structure;
import com.tn.sonede.persistance.TypeRessource;
import com.tn.sonede.persistance.ValeurCaracterestique;
import com.tn.sonede.service.GouverneratService;
import com.tn.sonede.service.RessourceService;
import com.tn.sonede.service.StructureService;
import com.tn.sonede.service.TypeRessourceService;
import com.tn.sonede.service.ValeurCaracterestiqueService;

@ManagedBean(name = "ressourceBean")
@SessionScoped
public class RessourceBean implements Serializable {

	@ManagedProperty(value = "#{ressourceService}")
	private RessourceService ressourceService;
	@ManagedProperty(value = "#{structureService}")
	private StructureService structureService;
	@ManagedProperty(value = "#{typeRessourceService}")
	private TypeRessourceService typeRessourceService;
	@ManagedProperty(value = "#{valeurCaracterestiqueService}")
	private ValeurCaracterestiqueService valeurCaracterestiqueService;
	@ManagedProperty(value = "#{gouverneratService}")
	private GouverneratService gouverneratService;
	
	private Ressource ressource;
	private List<Ressource> listRessources;
	private List<TypeRessource> listTypeRessources;
	private List<Gouvernerat> listGouvernerats;
	private List<Structure> listStructures;
	private List<Integer> listAnnees;
	private List<Caracterestique> listCaracterestiquesStatiques;

	public RessourceService getRessourceService() {
		return ressourceService;
	}

	public void setRessourceService(RessourceService ressourceService) {
		this.ressourceService = ressourceService;
	}
	
	
	
	public GouverneratService getGouverneratService() {
		return gouverneratService;
	}

	public void setGouverneratService(GouverneratService gouverneratService) {
		this.gouverneratService = gouverneratService;
	}

	public ValeurCaracterestiqueService getValeurCaracterestiqueService() {
		return valeurCaracterestiqueService;
	}

	public void setValeurCaracterestiqueService(
			ValeurCaracterestiqueService valeurCaracterestiqueService) {
		this.valeurCaracterestiqueService = valeurCaracterestiqueService;
	}

	public void onRowSelect(Integer id) { 
		ressource = ressourceService.getRessourceByIDWithJoins(id);
	}
	
	public void onchangeType() {
		try {
			Integer input=Integer.parseInt(ressource.getTypeRessource().toString());
			TypeRessource typeRessource=typeRessourceService.getCaracterestiquesduTypeRessourceByID(input,0);
			listCaracterestiquesStatiques=new ArrayList<Caracterestique>(typeRessource.getCaracterestiques());
			listCaracterestiquesStatiques=retourneListeCaracterestiquesByNature(0);
		} catch(Exception e) {
			listCaracterestiquesStatiques=new ArrayList<Caracterestique>();
		}
	}
	
	public List<Caracterestique> getListCaracterestiquesStatiques() {
		return listCaracterestiquesStatiques;
	}

	public void setListCaracterestiquesStatiques(
			List<Caracterestique> listCaracterestiquesStatiques) {
		this.listCaracterestiquesStatiques = listCaracterestiquesStatiques;
	}
	
	private List<Caracterestique> retourneListeCaracterestiquesByNature(int nat) {
		List<Caracterestique> lst = new ArrayList<Caracterestique>();
		for(int i=0;i<listCaracterestiquesStatiques.size();i++) {
			Caracterestique e = listCaracterestiquesStatiques.get(i);
			if(nat==e.getNature()){
				e.setSaisie(true);
				lst.add(e);
			}
		}
		return lst;
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
	
	public RessourceBean() {
		ressource = new Ressource();
	}

	public void ajoutEvent(ActionEvent actionEvent) {
		ressource = new Ressource();
		listCaracterestiquesStatiques=new ArrayList<Caracterestique>();
	}

	public void editEvent(Integer id) {
		ressource = ressourceService.getRessourceByID(id);
	}

	public void update(ActionEvent actionEvent) {
		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(ressource.getDesignation()==null||ressource.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		
		if(ressource.getStructure()==null||ressource.getStructure().getId()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Structure obligatoire"));
			testRG= false;
		}
		
		if(ressource.getGouvernerat()==null||ressource.getGouvernerat().getId()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Gouvernerat obligatoire"));
			testRG= false;
		}
		
		if(testRG){
			ressourceService.mettreJourRessource(ressource);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Ressource modifié"));

		}

	}

	public void insert(ActionEvent actionEvent) {

		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(ressource.getDesignation()==null||ressource.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		
		if(ressource.getTypeRessource()==null||ressource.getTypeRessource().getId()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Type de ressource obligatoire"));
			testRG= false;
		}
		
		if(ressource.getStructure()==null||ressource.getStructure().getId()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Structure obligatoire"));
			testRG= false;
		}
		
		if(ressource.getGouvernerat()==null||ressource.getGouvernerat().getId()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Gouvernerat obligatoire"));
			testRG= false;
		}
		
		if(!isValidCaracterestiquesStatiques()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Les caractérestiques statiques doivent toutes etre saisies dès l'ajout"));
			testRG= false;
		}
		if(testRG){
			ressourceService.ajoutRessource(ressource);
			validerCaracterestiquesStatiques();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Ressource ajoutée"));
			ressource = new Ressource();
		}
	}

	public void delete(Ressource Ressource) {

		FacesContext context = FacesContext.getCurrentInstance();
		ressourceService.supprimerRessource(Ressource);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Ressource supprimée"));
		ressource=new Ressource();

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
	
	public List<Gouvernerat> getListGouvernerats() {
		listGouvernerats = gouverneratService.getListGouvernerat();
		return listGouvernerats;
	}


	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource Ressource) {
		this.ressource = Ressource;
	}
	
	private void validerCaracterestiquesStatiques() {
		for(int i=0;i<listCaracterestiquesStatiques.size();i++) {
			Caracterestique e = listCaracterestiquesStatiques.get(i);
			if(e.getSaisie()&&e.getNewValeur()!=null&&!e.getNewValeur().isEmpty()) {
				ValeurCaracterestique vc = new ValeurCaracterestique();
				vc.setRessource(ressource);
				vc.setCaracterestique(e);
				vc.setValeur(e.getNewValeur());
				vc.setValide(false);
				vc.setDtAjout(new Date());
				valeurCaracterestiqueService.ajoutValeurCaracterestique(vc);
			}
		}
		
	}
	
	private boolean isValidCaracterestiquesStatiques() {
		int nb=0;
		for(int i=0;i<listCaracterestiquesStatiques.size();i++) {
			Caracterestique e = listCaracterestiquesStatiques.get(i);
			if(e.getSaisie()&&e.getNewValeur()!=null&&!e.getNewValeur().isEmpty()) {
				nb++;
			}
		}
		return (nb==listCaracterestiquesStatiques.size()?true:false);
		
	}

}