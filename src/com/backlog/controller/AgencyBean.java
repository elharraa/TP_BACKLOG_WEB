package com.backlog.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.backlog.dao.agency.AgencyDaoLocal;
import com.backlog.model.Agency;

@SuppressWarnings("serial")
@ManagedBean(name = "agencyBean", eager = true)
@SessionScoped
public class AgencyBean implements Serializable {

	private String name;
	private String id;
	private List<Agency> agencies = new ArrayList<Agency>();

	@EJB
	private AgencyDaoLocal agencyDao;

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Agency> allAgencies() {

		return agencyDao.getAllAgencies();

	}

	public String addAgency() {
		agencyDao.addAgency(new Agency(this.getName()));
		return "AgencyList?faces-redirect=true";
	}

	public List<Agency> getAgencies() {
		this.agencies = agencyDao.getAllAgencies();
		return agencies;
	}

	public void setAgencies(List<Agency> agencies) {
		this.agencies = agencies;
	}
	

	 public void attrListener(ActionEvent event){
		 
		 com.backlog.vars.Vars.idAgency = (int )event.getComponent().getAttributes().get("agencyId");
			 
		 
		  }
	
	
	public String loadBacklog() {
		
		
		return "BacklogEntryList?faces-redirect=true";

	}

}
