package org.rest.project.messenger.model;

import java.util.Date;

public class Like {
	

	private long id;
    private Date created;
    private String author;
    
    public Like() {
    	
    }
    
    public Like(long id, String author) {
    	this.id = id;

    	this.author = author;
    	this.created = new Date();
    }
    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
    
    

}
