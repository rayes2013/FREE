package com.tn.sonede.convertor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.tn.sonede.persistance.Agent;

@FacesConverter(value="agentConverter")
public class AgentConverter implements Converter {
 
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		Agent d = new Agent();
		d.setMatricule(Integer.parseInt(value));
		return d;
	}
 
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		Agent d = (Agent) value;
		return String.valueOf(d.getMatricule());
	}

	
 
}