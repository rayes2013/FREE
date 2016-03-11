package com.tn.sonede.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tn.sonede.persistance.Caracterestique;
import com.tn.sonede.persistance.Consommation;
import com.tn.sonede.persistance.ProduitChimique;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.Structure;
import com.tn.sonede.persistance.TypeRessource;
import com.tn.sonede.persistance.ValeurCaracterestique;
import com.tn.sonede.service.ConsommationService;
import com.tn.sonede.service.ProduitChimiqueService;
import com.tn.sonede.service.RessourceService;
import com.tn.sonede.service.StructureService;
import com.tn.sonede.service.TypeRessourceService;

@ManagedBean(name = "consommationBean")
@SessionScoped
public class ConsommationBean implements Serializable {

	@ManagedProperty(value = "#{consommationService}")
	private ConsommationService consommationService;
	@ManagedProperty(value = "#{ressourceService}")
	private RessourceService ressourceService;
	@ManagedProperty(value = "#{typeRessourceService}")
	private TypeRessourceService typeRessourceService;
	@ManagedProperty(value = "#{produitChimiqueService}")
	private ProduitChimiqueService produitChimiqueService;
	@ManagedProperty(value = "#{structureService}")
	private StructureService structureService;
	private List<ProduitChimique> listProduitChimiques;
	private Consommation consommation;
	private Ressource ressource;
	private List<Consommation> listConsommations;
	private List<TypeRessource> listTypeRessources;
	private List<Ressource> listRessources;
	private List<Structure> listStructures;
	private List<Integer> listAnnees;

	
	public StructureService getStructureService() {
		return structureService;
	}

	public void setStructureService(StructureService structureService) {
		this.structureService = structureService;
	}
	
	public ProduitChimiqueService getProduitChimiqueService() {
		return produitChimiqueService;
	}

	public void setProduitChimiqueService(ProduitChimiqueService produitChimiqueService) {
		this.produitChimiqueService = produitChimiqueService;
	}
	
	public ConsommationService getConsommationService() {
		return consommationService;
	}

	public void setConsommationService(ConsommationService consommationService) {
		this.consommationService = consommationService;
	}

	public void onRowSelect(Integer id) { 
		ressource = ressourceService.getRessourceByIDWithJoins(id);
		listConsommations=new ArrayList<Consommation>(ressource.getConsommations());
	}
	
	
	public void ajoutConsommationEvent(ActionEvent actionEvent) {
		consommation = new Consommation();
		consommation.setRessource(ressource);
	}

	public void editConsommationEvent(Integer id) {
		consommation = consommationService.getConsommationByID(id);
	}

	public void updateConsommation(ActionEvent actionEvent) {
		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(consommation.getRessource()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Vous devez sélectionner une ressource"));
			testRG= false;
		}
		if(consommation.getProduitChimique()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Produit Chimique obligatoire"));
			testRG= false;
		}
		
		if(consommation.getDtConst()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Date constatation obligatoire"));
			testRG= false;
		}
		if(consommation.getPrix()==null||consommation.getPrix()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Prix obligatoire"));
			testRG= false;
		}
		if(consommation.getQuantite()==null||consommation.getQuantite()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Quantité obligatoire"));
			testRG= false;
		}
		if(testRG){
			consommationService.mettreJourConsommation(consommation);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Consommation modifiée"));
		}

	}

	public void insertConsommation(ActionEvent actionEvent) {
		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(consommation.getRessource()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Vous devez sélectionner une ressource"));
			testRG= false;
		}
		if(consommation.getProduitChimique()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Produit Chimique obligatoire"));
			testRG= false;
		}
		
		if(consommation.getDtConst()==null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Date constatation obligatoire"));
			testRG= false;
		}
		if(consommation.getPrix()==null||consommation.getPrix()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Prix obligatoire"));
			testRG= false;
		}
		if(consommation.getQuantite()==null||consommation.getQuantite()==0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Quantité obligatoire"));
			testRG= false;
		}
		if(testRG){
			consommationService.ajoutConsommation(consommation);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Consommation ajoutée"));
			consommation=new Consommation();
			consommation.setRessource(ressource);
		}
	}

	public void deleteConsommation(Consommation Consommation) {
		FacesContext context = FacesContext.getCurrentInstance();
		consommationService.supprimerConsommation(Consommation);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Consommation supprimée"));

	}
	


	public RessourceService getRessourceService() {
		return ressourceService;
	}

	public void setRessourceService(RessourceService ressourceService) {
		this.ressourceService = ressourceService;
	}
	
	
	
	public TypeRessourceService getTypeRessourceService() {
		return typeRessourceService;
	}

	public void setTypeRessourceService(TypeRessourceService typeRessourceService) {
		this.typeRessourceService = typeRessourceService;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
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
	
	public List<ProduitChimique> getListProduitChimiques() {

		listProduitChimiques = produitChimiqueService.getListProduitChimique();
		return listProduitChimiques;
	}

	public List<Ressource> getListRessources() {
		listRessources=ressourceService.getListRessourceAvecCP();
		Collections.sort(listRessources, new Comparator<Ressource>() {
			public int compare(Ressource o1, Ressource o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listRessources;
	}

	public ConsommationBean() {
		consommation = new Consommation();
		listConsommations=new ArrayList<Consommation>();
	}

	
	
	public List<Consommation> getListConsommations() {
		
		Collections.sort(listConsommations, new Comparator<Consommation>() {
			public int compare(Consommation o1, Consommation o2) {
				return o2.getDtConst().compareTo(o1.getDtConst());
			}
		});
		return listConsommations;
	}
	


	public Consommation getConsommation() {
		return consommation;
	}

	public void setConsommation(Consommation Consommation) {
		this.consommation = Consommation;
	}
	
}