package com.sr03.beans;

public class Answer {
	private Integer      id;
	private Integer		idQuestion;
	private Integer		status;
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
public Integer getIdQuestion() {
	return idQuestion;
}
public void setIdQuestion(Integer idQuestion) {
	this.idQuestion = idQuestion;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
}
