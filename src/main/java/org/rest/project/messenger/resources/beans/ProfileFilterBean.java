package org.rest.project.messenger.resources.beans;

import javax.ws.rs.QueryParam;

public class ProfileFilterBean {

	 private @QueryParam("firstName") String firstName;
	 private @QueryParam("start") int start;
	 private @QueryParam("size") int size;
	
	 public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	 
	 
	 
	
	
}

