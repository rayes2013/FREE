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

import com.tn.sonede.persistance.ProduitChimique;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.service.ProduitChimiqueService;


@ManagedBean(name = "produitChimiqueBean")
@SessionScoped
public class ProduitChimiqueBean implements Serializable {

	@ManagedProperty(value = "#{produitChimiqueService}")
	private ProduitChimiqueService produitChimiqueService;
	private ProduitChimique produitChimique;
	private List<ProduitChimique> listProduitChimiques;
	
	public ProduitChimiqueService getProduitChimiqueService() {
		return produitChimiqueService;
	}

	public void setProduitChimiqueService(ProduitChimiqueService produitChimiqueService) {
		this.produitChimiqueService = produitChimiqueService;
	}

	public ProduitChimiqueBean() {
		produitChimique = new ProduitChimique();
	}

	public void ajoutEvent(ActionEvent actionEvent) {
		produitChimique = new ProduitChimique();
	}

	public void editEvent(int id) {
		produitChimique = produitChimiqueService.getProduitChimiqueByID(id);
	}
	
	public void onRowSelect(Integer id) { 
		produitChimique = produitChimiqueService.getProduitChimiqueByID(id);
	}

	public void update(ActionEvent actionEvent) {

		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(produitChimique.getCode()==null||produitChimique.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(produitChimique.getDesignation()==null||produitChimique.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			produitChimiqueService.mettreJourProduitChimique(produitChimique);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","ProduitChimique modifiée"));
		}

	}

	public void insert(ActionEvent actionEvent) {

		boolean testRG= true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(produitChimique.getCode()==null||produitChimique.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(produitChimiqueService.nbreLignesWithParam("code", produitChimique.getCode())!=0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code doit etre unique"));
			testRG= false;
		}
		if(produitChimique.getDesignation()==null||produitChimique.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			produitChimiqueService.ajoutProduitChimique(produitChimique);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","ProduitChimique ajoutée"));
			produitChimique = new ProduitChimique();
		}
	}

	public void delete(ProduitChimique ProduitChimique) {
		produitChimique= produitChimiqueService.getProduitChimiqueByID(ProduitChimique.getId());
		FacesContext context = FacesContext.getCurrentInstance();
		
		produitChimiqueService.supprimerProduitChimique(produitChimique);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","ProduitChimique supprimée"));
		produitChimique=new ProduitChimique();

	}
	

	public List<ProduitChimique> getListProduitChimiques() {

		listProduitChimiques = produitChimiqueService.getListProduitChimique();
		Collections.sort(listProduitChimiques, new Comparator<ProduitChimique>() {
			public int compare(ProduitChimique o1, ProduitChimique o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listProduitChimiques;
	}
	
	public ProduitChimique getProduitChimique() {
		return produitChimique;
	}

	public void setProduitChimique(ProduitChimique ProduitChimique) {
		this.produitChimique = ProduitChimique;
	}

}