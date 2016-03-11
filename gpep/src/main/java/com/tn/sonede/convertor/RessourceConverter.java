package com.tn.sonede.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tn.sonede.persistance.Ressource;

@FacesConverter(value="ressourceConverter")
public class RessourceConverter implements Converter {
 
	
 
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		Ressource d = new Ressource();
		d.setId(Integer.parseInt(value));
		return d;
	}
 
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		Ressource d = (Ressource) value;
		return String.valueOf(d.getId());
	}

	
 
}