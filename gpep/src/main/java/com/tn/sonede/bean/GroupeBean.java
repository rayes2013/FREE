package com.tn.sonede.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tn.sonede.persistance.Groupe;
import com.tn.sonede.service.GroupeService;


@ManagedBean(name = "groupeBean")
@SessionScoped
public class GroupeBean implements Serializable {

	@ManagedProperty(value = "#{groupeService}")
	private GroupeService groupeService;
	private List<Groupe> listGroupes;

	public GroupeService getGroupeService() {
		return groupeService;
	}

	public void setGroupeService(GroupeService groupeService) {
		this.groupeService = groupeService;
	}
	
	public List<Groupe> getListGroupes() {
		listGroupes = groupeService.getListGroupe();
		return listGroupes;
	}


}