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
import javax.faces.event.ActionEvent;

import org.mvel2.MVEL;
import org.mvel2.ast.TypeCast;
import org.mvel2.compiler.ExecutableStatement;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.SelectEvent;

import com.tn.sonede.persistance.Caracterestique;
import com.tn.sonede.persistance.Formule;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.TypeRessource;
import com.tn.sonede.service.CaracterestiqueService;
import com.tn.sonede.service.FormuleService;
import com.tn.sonede.service.TypeRessourceService;


@ManagedBean(name = "typeRessourceBean")
@SessionScoped
public class TypeRessourceBean implements Serializable {

	
	@ManagedProperty(value = "#{typeRessourceService}")
	private TypeRessourceService typeRessourceService;
	
	@ManagedProperty(value = "#{caracterestiqueService}")
	private CaracterestiqueService caracterestiqueService;
	
	@ManagedProperty(value = "#{formuleService}")
	private FormuleService formuleService;
	
	private TypeRessource typeRessource;
	private List<TypeRessource> listTypeRessources;
	
	private Caracterestique caracterestique;
	private List<Caracterestique> listCaracterestiques;
	private List<Caracterestique> listCaracterestiquesASelectionner;
	private List<Formule> listFormules;
	private Formule formule;
	
	

	public TypeRessourceBean(){
		typeRessource=new TypeRessource();
		caracterestique=new Caracterestique();
		formule=new Formule();
		listCaracterestiques=new ArrayList<Caracterestique>();
		listFormules=new ArrayList<Formule>();
	}
	
	
	
	public TypeRessourceService getTypeRessourceService() {
		return typeRessourceService;
	}

	public void setTypeRessourceService(TypeRessourceService typeRessourceService) {
		this.typeRessourceService = typeRessourceService;
	}
	
	public FormuleService getFormuleService() {
		return formuleService;
	}

	public void setFormuleService(FormuleService formuleService) {
		this.formuleService = formuleService;
	}
	
	public CaracterestiqueService getCaracterestiqueService() {
		return caracterestiqueService;
	}

	public void setCaracterestiqueService(CaracterestiqueService caracterestiqueService) {
		this.caracterestiqueService = caracterestiqueService;
	}
	
	public Caracterestique getCaracterestique() {
		return caracterestique;
	}
	
	public List<Formule> getListFormules() {
		TypeRessource temp=typeRessource;
		if(typeRessource!=null&&typeRessource.getId()!=null) {
			temp=typeRessourceService.getTypeRessourceByIDWithAllJoins(typeRessource.getId());
			listFormules = new ArrayList(temp.getFormules());
		}
		Collections.sort(listFormules, new Comparator<Formule>() {
			public int compare(Formule o1, Formule o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listFormules;
	}

	public Formule getFormule() {
		return formule;
	}
	
	public void setFormule(Formule formule) {
		this.formule = formule;
	}

	public void setCaracterestique(Caracterestique caracterestique) {
		this.caracterestique = caracterestique;
	}

	public void ajoutCaracterestiqueEvent(ActionEvent actionEvent) {
		caracterestique = new Caracterestique();
	}

	
	public void ajoutEvent(ActionEvent actionEvent) {
		typeRessource = new TypeRessource();
	}

	public void editEvent(Integer id) {
		typeRessource = typeRessourceService.getTypeRessourceByIDWithAllJoins(id);
	}
	
	public void onRowSelect(Integer id) { 
		typeRessource = typeRessourceService.getTypeRessourceByIDWithAllJoins(id);
		listFormules=new ArrayList<Formule>(typeRessource.getFormules());
		listCaracterestiques=new ArrayList<Caracterestique>(typeRessource.getCaracterestiques());
	}
	
	public void update(ActionEvent actionEvent) {
		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(typeRessource==null||typeRessource.getCode()==null||typeRessource.getCode().isEmpty()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(typeRessource.getDesignation()==null||typeRessource.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			typeRessourceService.mettreJourTypeRessource(typeRessource);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Type de ressource modifié"));

		}
		
	}
	
	public void insert(ActionEvent actionEvent) {
		boolean testRG= true;
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(typeRessource==null||typeRessource.getCode()==null||typeRessource.getCode().isEmpty()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		if(typeRessourceService.nbreLignesWithParam("code", typeRessource.getCode())!=0){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code doit etre unique"));
			testRG= false;
		}
		if(typeRessource.getDesignation()==null||typeRessource.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(testRG){
			typeRessourceService.ajoutTypeRessource(typeRessource);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Type de ressource ajouté"));
			typeRessource = new TypeRessource();
			formule=new Formule();
		}
	}

	public void delete(TypeRessource TypeRessource) {
		FacesContext context = FacesContext.getCurrentInstance();
		try{
			typeRessourceService.supprimerTypeRessource(TypeRessource);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Type de ressource supprimé"));
			listCaracterestiques=new ArrayList<Caracterestique>();
			formule=new Formule();
			typeRessource=new TypeRessource();
		} catch(Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Impossible de supprimer : Type de ressource affecté à des ressources"));
		}
	}

	public void insertCaracterestique(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		if(caracterestique==null) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","A caractérestiques à choisir"));
		} else {
			typeRessource.getCaracterestiques().add(caracterestique);
			typeRessourceService.mettreJourTypeRessource(typeRessource);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Caractérestique ajoutée"));
			caracterestique = new Caracterestique();
		}
		
	}

	public void deleteCaracterestique(Caracterestique caracterestique) {
		FacesContext context = FacesContext.getCurrentInstance();
		if(caracterestique.getType()==0) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Avertissemnet","Vérifier la formule, la caractérestique numérique est utilisé dans la formule"));
		}
		typeRessource.getCaracterestiques().remove(caracterestique);
		typeRessourceService.mettreJourTypeRessource(typeRessource);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Caractérestique supprimée"));
		caracterestique=new Caracterestique();
	}

	
	public void insertFormule(ActionEvent actionEvent) {
		boolean testRG= true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(typeRessource==null||typeRessource.getCode()==null||typeRessource.getCode().isEmpty()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Sélectionner un type "));
			return;
		}
		
		if(formule.getCode()==null||formule.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		
		long nb=formuleService.nbreLignesWithParam("code", formule.getCode());
		if(nb!=0) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code doit etre unique"));
			testRG= false;
		} 
		if(formule.getDesignation()==null||formule.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(!formuleValide()) {
	    	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Erreur de compilation de la formule"));
	    	testRG= false;
		}
		if(testRG){
			formule.setTypeRessource(typeRessource);
			formuleService.ajoutFormule(formule);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Formule ajoutée"));
			if(formule.getExpression()==null||formule.getExpression().isEmpty()){
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Avertissement","Expression mathématique vide, cette expression sera utiliser dans les rapports"));
			}
		}
	}
	
	public void updateFormule(ActionEvent actionEvent) {
		boolean testRG= true;
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(typeRessource==null||typeRessource.getCode()==null||typeRessource.getCode().isEmpty()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Sélectionner un type "));
			return;
		}
		
		if(formule.getCode()==null||formule.getCode().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code obligatoire"));
			testRG= false;
		}
		
		long nb=formuleService.nbreLignesWithParam("code", formule.getCode());
		if(nb>1) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Code doit etre unique"));
			testRG= false;
		}
		
		if(formule.getDesignation()==null||formule.getDesignation().isEmpty()){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Désignation obligatoire"));
			testRG= false;
		}
		if(!formuleValide()) {
	    	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Erreur de compilation de la formule"));
	    	testRG= false;
		}
		if(testRG){
			formuleService.mettreJourFormule(formule);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Formule modifiée"));
			if(formule.getExpression()==null||formule.getExpression().isEmpty()){
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Avertissement","Expression mathématique vide, cette expression sera utiliser dans les rapports"));
			}
		}
	}
	
	public void deleteFormule(Formule formule) {
		FacesContext context = FacesContext.getCurrentInstance();
		typeRessource.getFormules().remove(formule);
		typeRessourceService.mettreJourTypeRessource(typeRessource);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Formule supprimée"));
		formule=new Formule();
	}
	
	public void ajoutFormuleEvent(ActionEvent actionEvent) {
		formule = new Formule();
	}

	public void editFormuleEvent(Integer id) {
		formule = formuleService.getFormuleByID(id);
	}
	
	public void onRowFormuleSelect(Integer id) { 
		formule = formuleService.getFormuleByID(id);		
	}
	
	
	public void verifFormule(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		if(formuleValide())
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Formule valide"));
		else
	    	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Erreur de compilation de la formule"));

			
	}
	
	private boolean formuleValide(){
		String expressionStr = formule.getExpression();
	    Map<String, Object> vars = new HashMap<String, Object>();
	    for(Caracterestique c:typeRessource.getCaracterestiques()){
	    	if(c.getType()==0) //numérique
	    		vars.put(c.getDesignation(), 1);
	    }
	    try{
	    	ExecutableStatement statement = (ExecutableStatement) MVEL.compileExpression(expressionStr);
	    	Object result = MVEL.executeExpression(statement, vars);
	    } catch(Exception e){
	    	return false;
	    }
	    return true;
	}
	
	public void ajouterCarFormule(String car) {
		formule.setExpression(formule.getExpression()+car);
	}

	public List<TypeRessource> getListTypeRessources() {
		listTypeRessources = typeRessourceService.getListTypeRessource();
		Collections.sort(listTypeRessources, new Comparator<TypeRessource>() {
			public int compare(TypeRessource o1, TypeRessource o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listTypeRessources;
	}
	
	public void setListTypeRessources(List<TypeRessource> listTypeRessources) {
		this.listTypeRessources = listTypeRessources;
	}
	
	public List<Caracterestique> getListCaracterestiques() {
		TypeRessource temp=typeRessource;
		if(typeRessource!=null&&typeRessource.getId()!=null)
			temp=typeRessourceService.getTypeRessourceByIDWithAllJoins(typeRessource.getId());
		listCaracterestiques = new ArrayList(temp.getCaracterestiques());
		Collections.sort(listCaracterestiques, new Comparator<Caracterestique>() {
			public int compare(Caracterestique o1, Caracterestique o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listCaracterestiques;
	}
	
	

	public List<Caracterestique> getListCaracterestiquesASelectionner() {
		listCaracterestiquesASelectionner = caracterestiqueService.getListCaracterestiqueASelectionner(typeRessource.getId());
		return listCaracterestiquesASelectionner;
	}


	public TypeRessource getTypeRessource() {
		return typeRessource;
	}

	public void setTypeRessource(TypeRessource TypeRessource) {
		this.typeRessource = TypeRessource;
	}

}