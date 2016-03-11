package com.tn.sonede.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tn.sonede.persistance.Groupe;

@FacesConverter(value="groupeConverter")
public class GroupeConverter implements Converter {
 
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		Groupe d = new Groupe();
		d.setCode(Integer.parseInt(value));
		return d;
	}
 
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		Groupe d = (Groupe) value;
		return String.valueOf(d.getCode());
	}

	
 
}