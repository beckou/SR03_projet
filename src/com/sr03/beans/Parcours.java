package com.sr03.beans;

public class Parcours {
	private Integer      id;
	private Integer		idQuizz;
	private Integer		userID;
	private Integer    score;
	private Integer    time;

	public Integer getId() {
		return id;
	}
public Integer getIdQuizz() {
	return idQuizz;
}
	public Integer getScore() {
		return score;
	}
	public Integer getTime() {
		return time;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIdQuizz(Integer idQuizz) {
		this.idQuizz = idQuizz;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
}
