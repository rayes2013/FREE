package com.tn.sonede.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tn.sonede.persistance.Caracterestique;

@FacesConverter(value="caracterestiqueConverter")
public class CaracterestiqueConverter implements Converter {
	
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		Caracterestique d = new Caracterestique();
		d.setId(Integer.parseInt(value));
		return d;
	}
 
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		Caracterestique d = (Caracterestique) value;
		return String.valueOf(d.getId());
	}

	
 
}