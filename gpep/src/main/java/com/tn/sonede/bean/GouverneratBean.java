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

import com.tn.sonede.persistance.Caracterestique;
import com.tn.sonede.persistance.Gouvernerat;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.service.GouverneratService;


@ManagedBean(name = "gouverneratBean")
@SessionScoped
public class GouverneratBean implements Serializable {

	@ManagedProperty(value = "#{gouverneratService}")
	private GouverneratService gouverneratService;
	private Gouvernerat gouvernerat;
	private List<Gouvernerat> listGouvernerats;
	
	public GouverneratService getGouverneratService() {
		return gouverneratService;
	}

	public void setGouverneratService(GouverneratService gouverneratService) {
		this.gouverneratService = gouverneratService;
	}

	public GouverneratBean() {
		gouvernerat = new Gouvernerat();
	}

	public void ajoutEvent(ActionEvent actionEvent) {
		gouvernerat = new Gouvernerat();
	}

	public void editEvent(int id) {
		gouvernerat = gouverneratService.getGouverneratByID(id);
	}
	
	public void onRowSelect(Integer id) { 
		gouvernerat = gouverneratService.getGouverneratByID(id);
	}

	public void update(ActionEvent actionEvent) {

		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(gouvernerat.getCode()==null||gouvernerat.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(gouvernerat.getDesignation()==null||gouvernerat.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			gouverneratService.mettreJourGouvernerat(gouvernerat);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Gouvernerat modifiée"));
		}

	}

	public void insert(ActionEvent actionEvent) {

		boolean testRG= true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(gouvernerat.getCode()==null||gouvernerat.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(gouverneratService.nbreLignesWithParam("code", gouvernerat.getCode())!=0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code doit etre unique"));
			testRG= false;
		}
		if(gouvernerat.getDesignation()==null||gouvernerat.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			gouverneratService.ajoutGouvernerat(gouvernerat);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Gouvernerat ajoutée"));
			gouvernerat = new Gouvernerat();
		}
	}

	public void delete(Gouvernerat Gouvernerat) {
		gouvernerat= gouverneratService.getGouverneratByID(Gouvernerat.getId());
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			gouverneratService.supprimerGouvernerat(gouvernerat);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Gouvernerat supprimée"));
			gouvernerat=new Gouvernerat();
		} catch(Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Impossible de supprimer le gouvernerat, affecté à des ressources"));
		}
		
		
	}
	

	public List<Gouvernerat> getListGouvernerats() {

		listGouvernerats = gouverneratService.getListGouvernerat();
		Collections.sort(listGouvernerats, new Comparator<Gouvernerat>() {
			public int compare(Gouvernerat o1, Gouvernerat o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listGouvernerats;
	}
	
	public Gouvernerat getGouvernerat() {
		return gouvernerat;
	}

	public void setGouvernerat(Gouvernerat Gouvernerat) {
		this.gouvernerat = Gouvernerat;
	}

}