package com.sr03.beans;

public class Question {
	private Integer      id;
	private Integer		idQuizz;
	private String    intitule;

	public Integer getId() {
		return id;
	}

public String getIntitule() {
	return intitule;
}

public void setId(Integer id) {
	this.id = id;
}

public void setIntitule(String intitule) {
	this.intitule = intitule;
}
public Integer getIdQuizz() {
	return idQuizz;
}
public void setIdQuizz(int i) {
	this.idQuizz = i;
}
}
