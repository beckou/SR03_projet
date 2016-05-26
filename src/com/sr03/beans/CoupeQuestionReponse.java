package com.sr03.beans;

import java.util.List;

public class CoupeQuestionReponse {
	private Question question;
	private List<Answer> listeA;
	
	public CoupeQuestionReponse (Question q, List<Answer> l){
		
		this.question = q;
		this.listeA = l;
		
	}
	
	public List<Answer> getListeA() {
		return listeA;
	}
	public Question getQuestion() {
		return question;
	}
	public void setListeA(List<Answer> listeA) {
		this.listeA = listeA;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
}
