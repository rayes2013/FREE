package com.tn.sonede.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tn.sonede.persistance.Gouvernerat;

@FacesConverter(value="gouverneratConverter")
public class GouverneratConverter implements Converter {
 
	
 
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		Gouvernerat d = new Gouvernerat();
		d.setId(Integer.parseInt(value));
		return d;
	}
 
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		Gouvernerat d = (Gouvernerat) value;
		return String.valueOf(d.getId());
	}

	
 
}