package com.tn.sonede.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tn.sonede.persistance.ProduitChimique;

@FacesConverter(value="produitChimiqueConverter")
public class ProduitChimiqueConverter implements Converter {
 
	
 
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		ProduitChimique d = new ProduitChimique();
		d.setId(Integer.parseInt(value));
		return d;
	}
 
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		ProduitChimique d = (ProduitChimique) value;
		return String.valueOf(d.getId());
	}

	
 
}