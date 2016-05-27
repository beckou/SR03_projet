package com.sr03.dao;

import java.util.List;

import com.sr03.beans.Question;
import com.sr03.beans.Quizz;

public interface QuestionDAO {


    void creer( Question question ) throws DAOException;

    Question trouver( String id ) throws DAOException;

	List<Question> viewAllQuestion(int offset, int noOfRecords, int id_quizz);
	
	int getNoOfRecords();
	
	public void modifier(int questionID, String intitule, int ordre);

	List<Question> viewAllQuestion(int parseInt);


}
