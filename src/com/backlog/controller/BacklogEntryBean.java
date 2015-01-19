package com.backlog.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.backlog.dao.agency.BacklogEntryDaoLocal;
import com.backlog.model.BacklogEntry;


@SuppressWarnings("serial")
@ManagedBean(name = "backlogEntryBean", eager = true)
@SessionScoped
public class BacklogEntryBean implements Serializable {
	
	private String name;
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String creationDate;
	private int priority;
	private int estimation;
	private String description;
	private int agencyId ;
	public int getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(int agencyId) {
		this.agencyId = agencyId;
	}

	private List<BacklogEntry> backlogEntries = new ArrayList<BacklogEntry>();
	
	@EJB
	private BacklogEntryDaoLocal backlogEntryDao;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getEstimation() {
		return estimation;
	}

	public void setEstimation(int estimation) {
		this.estimation = estimation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BacklogEntry> getBacklogEntries() {
		this.backlogEntries =  backlogEntryDao.getAllBacklogEntries() ;
		return backlogEntries;
	}

	public void setBacklogEntries(List<BacklogEntry> backlogEntries) {
		this.backlogEntries = backlogEntries;
	}
	
	public String addBacklogEntry(){
		backlogEntryDao.addBacklogEntry(new BacklogEntry(com.backlog.vars.Vars.idAgency,this.getName(),new Date().getTime()+"",this.getPriority(),this.getEstimation(),this.getDescription()));
		return "BacklogEntryList?faces-redirect=true";
	}
	
	
	
	
	public void attrListener(ActionEvent event){
		 
		 com.backlog.vars.Vars.idEntry = (int )event.getComponent().getAttributes().get("backlogEntryId");
			 
		 
		  }
	
	
	public String loadComments() {
		
		
		return "CommentList?faces-redirect=true";

	}
	
	
	public String removeBacklogEntry(ActionEvent event ) {
		
		backlogEntryDao.deleteBacklogEntry((int )event.getComponent().getAttributes().get("backlogEntryId"));
	 	
		return "BacklogEntryList?faces-redirect=true";
		
		
	}
	
	
public String toEditBacklogEntry(ActionEvent event ) throws IOException {
		
	    System.out.println(this.id);
		setId((int )event.getComponent().getAttributes().get("backlogEntryId"));
		System.out.println(this.id);	
		
		System.out.println(this.creationDate);
		setCreationDate((String )event.getComponent().getAttributes().get("backlogEntryCreationDate"));
		System.out.println(this.creationDate ) ;
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("EditBacklogEntry.jsf");
		return "EditBacklogEntry?faces-redirect=true";
		
		
	}
	

	
	public String editBacklogEntry() {
		
		BacklogEntry be = new BacklogEntry(com.backlog.vars.Vars.idAgency,this.name, this.creationDate,this.priority, this.estimation, this.description) ;
        be.setId(this.id);
		backlogEntryDao.editBacklogEntry(be);
		return "BacklogEntryList?faces-redirect=true";
		
	}
	
	
	
}
