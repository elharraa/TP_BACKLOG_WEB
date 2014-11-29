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

import com.backlog.dao.agency.CommentDaoLocal;
import com.backlog.model.Comment;


@SuppressWarnings("serial")
@ManagedBean(name = "commentBean", eager = true)
@SessionScoped
public class CommentBean implements Serializable {
	
	private int id;
	private int userId;
	private int entryId;
	private String comment;
	private String creationDate;
	
	
	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public int getUserId() {
		return userId;
	}





	public void setUserId(int userId) {
		this.userId = userId;
	}





	public int getEntryId() {
		return entryId;
	}





	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}





	public String getComment() {
		return comment;
	}





	public void setComment(String comment) {
		this.comment = comment;
	}





	public String getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	

	public List<Comment> getComments() {
		this.comments =  commentDao.getAllComments() ;
		System.out.println(" nb comments =" +  commentDao.getAllComments().size());
		return comments;
	}


	

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	private List<Comment> comments = new ArrayList<Comment>();
	
	@EJB
	private CommentDaoLocal commentDao;


	public String addComment(){
		commentDao.addComment(new Comment(com.backlog.vars.Vars.idUser, /* com.backlog.vars.Vars.idEntry */ this.entryId, this.comment, new Date().getTime()+"") );
		return "CommentList?faces-redirect=true";
	}
		
	
	public String removeComment(ActionEvent event ) {
		
		commentDao.deleteComment((int )event.getComponent().getAttributes().get("commentId"));
	 	
		return "CommentList?faces-redirect=true";
		
		
	}
	
	
public String toEditComment(ActionEvent event ) throws IOException {
		
	    System.out.println(this.id);
		setId((int )event.getComponent().getAttributes().get("commentId"));
		System.out.println(this.id);	
		
		System.out.println(this.creationDate);
		setCreationDate((String )event.getComponent().getAttributes().get("commentCreationDate"));
		System.out.println(this.creationDate ) ;
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("EditComment.xhtml");
		return "EditComment?faces-redirect=true";
		
		
	}



public String toCommentList(ActionEvent event ) throws IOException {
	
    System.out.println(this.id);
	setEntryId(((int )event.getComponent().getAttributes().get("backlogEntryId")));
	System.out.println(this.id);	
	
	FacesContext.getCurrentInstance().getExternalContext().redirect("CommentList.xhtml");
	return "EditComment?faces-redirect=true";
	
	
}
	

	
	public String editComment() {
		
		Comment c = new Comment(this.userId, this.entryId, this.comment, this.creationDate) ;
        c.setId(this.id);
		commentDao.editComment(c);
		return "CommentList?faces-redirect=true";
		
	}
	
	
	
}
