package com.example.demo.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
   
	@Id
	private int id;
	private String message;
	private String profName;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(int id, String message, String profName) {
		super();
		this.id = id;
		this.message = message;
		this.profName = profName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}
	
}
