package com.tn.sonede.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tn.sonede.persistance.TypeRessource;

@FacesConverter(value="typeRessourceConverter")
public class TypeRessourceConverter implements Converter {
 
	
 
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		TypeRessource d = new TypeRessource();
		d.setId(Integer.parseInt(value));
		return d;
	}
 
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if(value==null) return null;
		TypeRessource d = (TypeRessource) value;
		return String.valueOf(d.getId());
	}

	
 
}