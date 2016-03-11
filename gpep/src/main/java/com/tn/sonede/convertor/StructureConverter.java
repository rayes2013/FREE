package com.tn.sonede.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tn.sonede.persistance.Structure;

@FacesConverter(value="structureConverter")
public class StructureConverter implements Converter {
 
	
 
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		Structure d = new Structure();
		d.setId(Integer.parseInt(value));
		return d;
	}
 
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		Structure d = (Structure) value;
		return String.valueOf(d.getId());
	}

	
 
}