package com.tn.sonede.jasper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRDataSource;

import com.tn.sonede.persistance.Caracterestique;
import com.tn.sonede.persistance.Formule;
import com.tn.sonede.persistance.Ressource;
import com.tn.sonede.persistance.TypeRessource;
import com.tn.sonede.service.RessourceService;
import com.tn.sonede.service.TypeRessourceService;
 
@ManagedBean(name = "reportsBean")
@SessionScoped
 
public class ReportsBean extends AbstractReportBean {
 
	
	public ReportsBean() {
		Date now=new Date();
		type=2;
		annee=now.getYear()+1900;
		mois=now.getMonth()+1;
	}
	
	 @Override
	    protected Map<String, Object> getReportParameters() {
	        Map<String, Object> reportParameters = new HashMap<String, Object>();
	        if(type==2) { //mensuelle
		        TypeRessource temp=typeRessourceService.getTypeRessourceByID(typeRessource.getId());
		        reportParameters.put("reportTitle", "Rapport mensuel");
		        reportParameters.put("don1", temp.getDesignation());
		        reportParameters.put("don2", "mois");
		        reportParameters.put("don3", affMoisLettre(mois));
		        reportParameters.put("don4", "année");
		        reportParameters.put("don5", annee.toString());
	        } else if(type==3){
	        	Ressource temp=ressourceService.getRessourceByID(ressource.getId());
		        reportParameters.put("reportTitle", "Rapport annuel");
		        reportParameters.put("don1", "");
		        reportParameters.put("don2", temp.getTypeRessource().getDesignation());
		        reportParameters.put("don3", temp.getDesignation());
		        reportParameters.put("don4", "année");
		        reportParameters.put("don5", annee.toString());
	        }
	        return reportParameters;
	    }
	 
	 @Override
		protected JRDataSource getJRDataSource() {
			// return your custom datasource implementation
			MyJasperReportsDataSource dataSource = new MyJasperReportsDataSource();
			if(type==2) { //mensuelle
				TypeRessource temp=typeRessourceService.getCaracterestiquesduTypeRessourceByID(typeRessource.getId(),0);
		        typeRessource=typeRessourceService.getTypeRessourceByIDWithAllJoinsAvecValeurValideDate(typeRessource.getId(),annee,mois);
		        listCaracterestiques=new ArrayList<Caracterestique>(temp.getCaracterestiques());
		        listFormules=new ArrayList<Formule>(temp.getFormules());
			} else if(type==3){
				Ressource temp=ressourceService.getRessourceByIDWithJoins(ressource.getId());
				listCaracterestiques=new ArrayList<Caracterestique>(temp.getTypeRessource().getCaracterestiques());
		        listFormules=new ArrayList<Formule>(temp.getTypeRessource().getFormules());
			}
	        headerTable(dataSource);
	        contenuTable(dataSource);
			
			return dataSource;
		}
	 
	    private void initValeurs(Ressource r,Date d) {
			for(int i=0;i<listCaracterestiques.size();i++) {
				Caracterestique c = listCaracterestiques.get(i);
				c.initReport(r.getValeurs(),d);
			}
	    }

		private void contenuTable(MyJasperReportsDataSource dataSource) {
			if(type==2) { //mensuelle
		    	List<Ressource> ressources = new ArrayList<Ressource>(typeRessource.getRessources());
		    	dataSource.size=ressources.size()+1;
				for(int i=0;i<ressources.size();i++) {
					Ressource r=ressources.get(i);
					dataSource.data[i+1][0]=r.getDesignation();
					for(int j=0;j<5;j++) {
						if(j<listFormules.size())
							dataSource.data[i+1][j+1]=new String(eval(annee,mois,listFormules.get(j),ressources.get(i)));
						else 
							dataSource.data[i+1][j+1]="";
					}
				}
			} else if(type==3){
		    	dataSource.size=13;
				for(int i=0;i<12;i++) {
					Ressource r=ressourceService.getRessourceByIDAvecValeursMoisAnnee(ressource.getId(),annee,i+1);
					dataSource.data[i+1][0]=affMoisLettre(i+1);
					for(int j=0;j<5;j++) {
						if(j<listFormules.size())
							dataSource.data[i+1][j+1]=new String(eval(annee,i+1,listFormules.get(j),r));
						else 
							dataSource.data[i+1][j+1]="";
					}
				}
			}
	    }

		private String eval(Integer annee,Integer mois,Formule formule, Ressource ressource) {
			Double tot = 0.;
			Date d=new Date(annee-1900,mois-1,1);
			for(int i=1;i<31;i++) {
				initValeurs(ressource,d);
				formule.eval(listCaracterestiques);
				tot+=(Double)formule.getResultat();
				d.setDate(d.getDate()+1);
				if(d.getMonth()!=mois-1) break;
			}
			return tot.toString();
		}

		private void headerTable(MyJasperReportsDataSource dataSource) {

			dataSource.data[0][0]="Désignation";
			for(int i=0;i<5;i++) {
				if(i<listFormules.size())
					dataSource.data[0][i+1]=new String(listFormules.get(i).getDesignation());
				else
					dataSource.data[0][i+1]="";
			}
	    }

		public String execute() {

	    	boolean testRG= true;
			
			FacesContext context = FacesContext.getCurrentInstance();
			if(annee==null||annee==0) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Année obligatoire"));
				testRG= false;
			}
			if(type==2) { //mensuelle
				if(mois==null||mois==0) {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Mois obligatoire"));
					testRG= false;
				}
			}
			if(type==2) { //mensuelle
				if(typeRessource==null||typeRessource.getId()==null||typeRessource.getId()==0) {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Type Ressource obligatoire"));
					testRG= false;
				}
			}
			if(type==3) { //annuelle
				if(ressource==null||ressource.getId()==null||ressource.getId()==0) {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Ressource obligatoire"));
					testRG= false;
				}
			}
			if(testRG){
		        try {
		            super.prepareReport();
		            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Rapport généré"));
		        } catch (Exception e) {
		            // make your own exception handling
		        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur","Erreur génération rapport"));
		            e.printStackTrace();
		        }
			}
	        return null;
	    }
	

	private final String COMPILE_FILE_NAME = "rapport";
 
    @Override
    protected String getCompileFileName() {
        return COMPILE_FILE_NAME;
    }
 
	private List<Ressource> listRessources;
	private List<TypeRessource> listTypeRessources;
	private List<Caracterestique> listCaracterestiques;
	private List<Formule> listFormules;
	private List<Integer> listAnnees;
	private Integer type;
	private Integer annee;
	private Integer mois;
	private Ressource ressource;
	private TypeRessource typeRessource;
	@ManagedProperty(value = "#{ressourceService}")
	private RessourceService ressourceService;
	@ManagedProperty(value = "#{typeRessourceService}")
	private TypeRessourceService typeRessourceService;
	
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}
	
	

	public TypeRessource getTypeRessource() {
		return typeRessource;
	}

	public void setTypeRessource(TypeRessource typeRessource) {
		this.typeRessource = typeRessource;
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
	
	public List<TypeRessource> getListTypeRessources() {
		listTypeRessources = typeRessourceService.getListTypeRessource();
		Collections.sort(listTypeRessources, new Comparator<TypeRessource>() {
			public int compare(TypeRessource o1, TypeRessource o2) {
				return (o2.getId()-o1.getId());
			}
		});
		return listTypeRessources;
	}

	public List<Integer> getListAnnees() {
		listAnnees=new ArrayList<Integer>();
		for(int i=1950;i<2050;i++)
			listAnnees.add(i);
		return listAnnees;
	}

	public String affMoisLettre(int i) {
		String m="";
		switch(i) {
			case 1 : m="Janvier";break;
			case 2 : m="Février";break;
			case 3 : m="Mars";break;
			case 4 : m="Avril";break;
			case 5 : m="Mai";break;
			case 6 : m="Juin";break;
			case 7 : m="Juillet";break;
			case 8 : m="Aout";break;
			case 9 : m="Septembre";break;
			case 10 : m="Octobre";break;
			case 11 : m="Novembre";break;
			case 12 : m="Décembre";break;
			default : m="inconnu";
		}
		return m;
	}
    
}
