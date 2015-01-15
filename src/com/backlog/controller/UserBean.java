package com.backlog.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.backlog.dao.agency.UserDaoLocal;
import com.backlog.model.User;

//@Model
@SuppressWarnings("serial")
@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	@Inject
    private HttpSession httpSession;

	@Inject
	UserDaoLocal userDao;
	
	 public void AddUser() throws Exception {
	        try {
	        	System.out.println("Register OK!......");
	        	userDao.addUser(user);
	        } catch (Exception e) {
	        	System.out.println("Register ERROR ;( ......\n"+e.getMessage());
	        }
	    }
	 
	 public void Login() throws IOException{
		 System.out.println(">>>>>>>>>>>>>>>>>"+userDao.findByName(user));
		 if(userDao.findByName(user) != null){
//			 HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
             httpSession.setAttribute("connectedMember", user);
             FacesContext.getCurrentInstance().getExternalContext().redirect("AgencyList.jsf");
		 }else{
			 initMember();
		 }
	 }
	 
	 @PostConstruct
	    public void initMember() {
	       user = new User();
	    }
	 public User getUser() {
		 return user;
	 }
	 
	 public void setUser(User user) {
		 this.user = user;
	 }
}
