package com.sr03.dao;

import java.util.List;

import com.sr03.beans.Answer;
import com.sr03.beans.Question;
import com.sr03.beans.Quizz;

public interface AnswerDAO {


    void creer( Answer answer ) throws DAOException;

    Answer trouver( String id ) throws DAOException;

	List<Answer> viewAllAnswer(int offset, int noOfRecords, int id_question);
	
	int getNoOfRecords();

	void modify (int idAnswer);

}
