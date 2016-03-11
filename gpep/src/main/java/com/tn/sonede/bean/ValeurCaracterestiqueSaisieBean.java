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
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.Structure;
import com.tn.sonede.persistance.TypeRessource;
import com.tn.sonede.persistance.ValeurCaracterestique;
import com.tn.sonede.service.RessourceService;
import com.tn.sonede.service.StructureService;
import com.tn.sonede.service.TypeRessourceService;
import com.tn.sonede.service.ValeurCaracterestiqueService;

@ManagedBean(name = "valeurCaracterestiqueSaisieBean")
@SessionScoped
public class ValeurCaracterestiqueSaisieBean implements Serializable {

	@ManagedProperty(value = "#{ressourceService}")
	private RessourceService ressourceService;
	@ManagedProperty(value = "#{structureService}")
	private StructureService structureService;
	@ManagedProperty(value = "#{typeRessourceService}")
	private TypeRessourceService typeRessourceService;
	@ManagedProperty(value = "#{valeurCaracterestiqueService}")
	private ValeurCaracterestiqueService valeurCaracterestiqueService;
	private Ressource ressource;
	private List<Ressource> listRessources;
	private List<TypeRessource> listTypeRessources;
	private List<Structure> listStructures;
	private List<Integer> listAnnees;
	
	private List<ValeurCaracterestique> listValeursCaracterstiques;
	private List<Caracterestique> listCaracterestiques;
	private List<Caracterestique> listCaracterestiquesStatiques;
	private List<Caracterestique> listCaracterestiquesJournalières;
	private List<Caracterestique> listCaracterestiquesMensuelles;
	
	private Caracterestique caracterestique;
	
	private List<ValeurCaracterestique> listHistoriqueValeurs;

	public RessourceService getRessourceService() {
		return ressourceService;
	}

	public void setRessourceService(RessourceService ressourceService) {
		this.ressourceService = ressourceService;
	}
	
	
	public Caracterestique getCaracterestique() {
		return caracterestique;
	}

	public void setCaracterestique(Caracterestique caracterestique) {
		this.caracterestique = caracterestique;
	}

	public List<ValeurCaracterestique> getListHistoriqueValeurs() {
		listHistoriqueValeurs=new ArrayList<ValeurCaracterestique>(caracterestique.getValeurs());
		Collections.sort(listHistoriqueValeurs, new Comparator<ValeurCaracterestique>() {
			public int compare(ValeurCaracterestique o1, ValeurCaracterestique o2) {
				return o2.getDtAjout().compareTo(o1.getDtAjout());
			}
		});
		return listHistoriqueValeurs;
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

	public ValeurCaracterestiqueService getValeurCaracterestiqueService() {
		return valeurCaracterestiqueService;
	}

	public void setValeurCaracterestiqueService(
			ValeurCaracterestiqueService valeurCaracterestiqueService) {
		this.valeurCaracterestiqueService = valeurCaracterestiqueService;
	}

	public List<ValeurCaracterestique> getListValeursCaracterstiques() {
		return listValeursCaracterstiques;
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
	
	public ValeurCaracterestiqueSaisieBean() {
		ressource = new Ressource();
		caracterestique=new Caracterestique();
		listCaracterestiques=new ArrayList<Caracterestique>();
	}

	
	
	public List<Caracterestique> getListCaracterestiquesStatiques() {
		listCaracterestiquesStatiques=retourneListeCaracterestiquesByNature(0);
		return listCaracterestiquesStatiques;
	}

	public List<Caracterestique> getListCaracterestiquesJournalières() {
		listCaracterestiquesJournalières=retourneListeCaracterestiquesByNature(1);
		return listCaracterestiquesJournalières;
	}

	private List<Caracterestique> retourneListeCaracterestiquesByNature(int nat) {
		List<Caracterestique> lst = new ArrayList<Caracterestique>();
		for(int i=0;i<listCaracterestiques.size();i++) {
			Caracterestique e = listCaracterestiques.get(i);
			if(nat==e.getNature()){
				e.init();
				lst.add(e);
			}
		}
		return lst;
	}

	public List<Caracterestique> getListCaracterestiquesMensuelles() {
		listCaracterestiquesMensuelles=retourneListeCaracterestiquesByNature(2);
		return listCaracterestiquesMensuelles;
	}

	public void onRowSelect(Integer id) { 
		ressource = ressourceService.getRessourceByIDWithJoinsAvecValeurs(id);
		if(ressource==null)
			ressource = ressourceService.getRessourceByIDWithJoins(id);
		listCaracterestiques=new ArrayList<Caracterestique>(ressource.getTypeRessource().getCaracterestiques());
		filtreListeCaracterestiques();
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
	
	public void onRowSelectHis(Caracterestique c) { 
		caracterestique=c;
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
	
	
	public void validerCaracterestiquesStatiques(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		int nb=0;
		for(int i=0;i<listCaracterestiquesStatiques.size();i++) {
			Caracterestique e = listCaracterestiquesStatiques.get(i);
			if(e.getSaisie()&&e.getNewValeur()!=null&&!e.getNewValeur().isEmpty()) {
				ValeurCaracterestique vc = new ValeurCaracterestique();
				vc.setRessource(ressource);
				vc.setCaracterestique(e);
				vc.setValeur(e.getNewValeur());
				vc.setValide(false);
				vc.setDtAjout(new Date());
				valeurCaracterestiqueService.mettreJourValeurCaracterestique(vc);
				nb++;
			}
		}
		if(nb>0)
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success",nb + " valeurs ajoutées aujourd'hui"));
		else {
			if(listCaracterestiquesStatiques.isEmpty()) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Aucune caractérestique à ajouter"));
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Valeurs statiques s'ajoutent une seule fois"));
			}
		}
		onRowSelect(ressource.getId());
		
	}
	
	public void validerCaracterestiquesJournalieres(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		int nb=0;
		for(int i=0;i<listCaracterestiquesJournalières.size();i++) {
			Caracterestique e = listCaracterestiquesJournalières.get(i);
			if(e.getSaisie()&&e.getNewValeur()!=null&&!e.getNewValeur().isEmpty()) {
				ValeurCaracterestique vc=null;
				if(e.getIdValeur()!=null)
					vc =valeurCaracterestiqueService.getValeurCaracterestiqueByID(e.getIdValeur());
				if(vc==null) {
					vc = new ValeurCaracterestique();
					vc.setRessource(ressource);
					vc.setCaracterestique(e);
				}
				
				vc.setValeur(e.getNewValeur());
				vc.setValide(false);
				Date today1=new Date();
				Date today=new Date(today1.getYear(),today1.getMonth(),today1.getDate());
				if(vc.getDtAjout()!=null&&vc.getDtAjout().compareTo(today)==0) {
					valeurCaracterestiqueService.mettreJourValeurCaracterestique(vc);
				} else {
					vc = new ValeurCaracterestique();
					vc.setRessource(ressource);
					vc.setCaracterestique(e);
					vc.setDtAjout(new Date());
					vc.setValeur(e.getNewValeur());
					vc.setValide(false);
					valeurCaracterestiqueService.ajoutValeurCaracterestique(vc);
				}
				nb++;
			}
		}
		if(nb>0)
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success",nb + " valeurs ajoutées aujourd'hui"));
		else  {
			if(listCaracterestiquesJournalières.isEmpty()) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Aucune caractérestique à ajouter"));
			} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Valeurs déja ajoutées aujourd'hui"));
			}
		}
		onRowSelect(ressource.getId());
		
	}
	
	public void validerCaracterestiquesMensuelles(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		int nb=0;
		for(int i=0;i<listCaracterestiquesMensuelles.size();i++) {
			Caracterestique e = listCaracterestiquesMensuelles.get(i);
			if(e.getSaisie()&&e.getNewValeur()!=null&&!e.getNewValeur().isEmpty()) {
				ValeurCaracterestique vc=null;
				if(e.getIdValeur()!=null)
					vc =valeurCaracterestiqueService.getValeurCaracterestiqueByID(e.getIdValeur());
				if(vc==null) {
					vc = new ValeurCaracterestique();
					vc.setRessource(ressource);
					vc.setCaracterestique(e);
				}
				vc.setValeur(e.getNewValeur());
				vc.setValide(false);
				Date today1=new Date();
				Date today=new Date(today1.getYear(),today1.getMonth(),1);
				if(vc.getDtAjout()!=null&&vc.getDtAjout().compareTo(today)==0) {
					valeurCaracterestiqueService.mettreJourValeurCaracterestique(vc);
				} else {
					vc = new ValeurCaracterestique();
					vc.setRessource(ressource);
					vc.setCaracterestique(e);
					vc.setValeur(e.getNewValeur());
					vc.setValide(false);
					vc.setDtAjout(today);
					valeurCaracterestiqueService.ajoutValeurCaracterestique(vc);
				}
				
				nb++;
				
			}
		}
		if(nb>0)
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success",nb + " valeurs ajoutées pour ce mois"));
		else  {
			if(listCaracterestiquesMensuelles.isEmpty()) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Aucune caractérestique à ajouter"));
			} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Valeurs déja ajoutées pour ce mois"));
			}
		}
			onRowSelect(ressource.getId());
		
	}

}