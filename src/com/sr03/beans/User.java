package com.sr03.beans;

import com.sun.jmx.snmp.Timestamp;

public class User {

	private Long      id;
	private String    lastname;
	private String    mail;
	private String    password;
	private String 	  company;
	private String    phone;
    private java.sql.Timestamp  dateInscription;
    private int   state;
		
    
    public Long getId(){
    	return id;
    }
    public void setId(Long ID){
    	id = ID;
    }
    
    public String getLastname(){
    	return lastname;
    }
    
    public void setLastname(String name){
    	lastname = name;
    }
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public java.sql.Timestamp  getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(java.sql.Timestamp timestamp) {
		this.dateInscription = timestamp;
	}
    
	
}
