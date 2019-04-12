package org.rest.project.messenger.model;

import java.util.Date;

public class Share {
	

	private long id;
    private Date created;
    private String author;
    
    public Share() {
    	
    }
    
    public Share(long id, String author) {
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
