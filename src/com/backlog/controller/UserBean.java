package com.backlog.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.backlog.dao.agency.UserDaoLocal;
import com.backlog.model.User;

@SuppressWarnings("serial")
@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean implements Serializable {
	
	private User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Inject
	UserDaoLocal userDao;
	
	 public void AddUser() throws Exception {
	        try {
	        	System.out.println("Register......");
	        	userDao.addUser(user);
////	            facesContext.addMessage(null,
////	                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
//	            initMember();
	        } catch (Exception e) {
//	            String errorMessage = getRootErrorMessage(e);
//	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration Unsuccessful");
//	            facesContext.addMessage(null, m);
	        }
	    }
	 
	 @PostConstruct
	    public void initMember() {
	       user = new User();
	    }
}
