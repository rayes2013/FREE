package com.tn.sonede.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tn.sonede.persistance.Caracterestique;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.TypeRessource;
import com.tn.sonede.service.CaracterestiqueService;


@ManagedBean(name = "caracterestiqueBean")
@SessionScoped
public class CaracterestiqueBean implements Serializable {

	@ManagedProperty(value = "#{caracterestiqueService}")
	private CaracterestiqueService caracterestiqueService;
	private Caracterestique caracterestique;
	private List<Caracterestique> listCaracterestiques;
	private List<TypeRessource> listTypeRessources;
	
	public CaracterestiqueService getCaracterestiqueService() {
		return caracterestiqueService;
	}

	public void setCaracterestiqueService(CaracterestiqueService caracterestiqueService) {
		this.caracterestiqueService = caracterestiqueService;
	}

	public CaracterestiqueBean() {
		caracterestique = new Caracterestique();
	}

	public void ajoutEvent(ActionEvent actionEvent) {
		caracterestique = new Caracterestique();
	}

	public void editEvent(int id) {
		caracterestique = caracterestiqueService.getCaracterestiqueByID(id);
	}
	
	public void onRowSelect(Integer id) { 
		caracterestique = caracterestiqueService.getCaracterestiqueByIDWithJoins(id);
		listTypeRessources=new ArrayList(caracterestique.getTypeRessources());
	}

	public void update(ActionEvent actionEvent) {

		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(caracterestique.getCode()==null||caracterestique.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(caracterestique.getDesignation()==null||caracterestique.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			caracterestiqueService.mettreJourCaracterestique(caracterestique);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Caractérestique modifiée"));
		}

	}

	public void insert(ActionEvent actionEvent) {

		boolean testRG= true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(caracterestique.getCode()==null||caracterestique.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(caracterestiqueService.nbreLignesWithParam("code", caracterestique.getCode())!=0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code doit etre unique"));
			testRG= false;
		}
		if(caracterestique.getDesignation()==null||caracterestique.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			caracterestiqueService.ajoutCaracterestique(caracterestique);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Caractérestique ajoutée"));
			caracterestique = new Caracterestique();
		}
	}

	public void delete(Caracterestique Caracterestique) {
		caracterestique= caracterestiqueService.getCaracterestiqueByIDWithJoins(Caracterestique.getId());
		FacesContext context = FacesContext.getCurrentInstance();
		if(!caracterestique.getTypeRessources().isEmpty()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Impossible de supprimer cette caractérestique(affecté à "+caracterestique.getTypeRessources().size()+" type(s) de ressource )"));
		} else {
			caracterestiqueService.supprimerCaracterestique(caracterestique);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Caractérestique supprimée"));
			caracterestique=new Caracterestique();
		}
	}

	public List<Caracterestique> getListCaracterestiques() {

		listCaracterestiques = caracterestiqueService.getListCaracterestique();
		Collections.sort(listCaracterestiques, new Comparator<Caracterestique>() {
			public int compare(Caracterestique o1, Caracterestique o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listCaracterestiques;
	}
	
	
	
	public List<TypeRessource> getListTypeRessources() {
		return listTypeRessources;
	}

	public Caracterestique getCaracterestique() {
		return caracterestique;
	}

	public void setCaracterestique(Caracterestique Caracterestique) {
		this.caracterestique = Caracterestique;
	}

}