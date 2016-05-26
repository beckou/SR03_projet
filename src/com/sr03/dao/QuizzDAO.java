package com.sr03.dao;

import java.util.List;

import com.sr03.beans.Quizz;

public interface QuizzDAO {


    void creer( Quizz quizz ) throws DAOException;

    Quizz trouver( String id ) throws DAOException;

	List<Quizz> viewAllQuizz(int offset, int noOfRecords);
	
	void modifier(int quizzID, String intitule) throws DAOException;
	
	int getNoOfRecords();

	List<Quizz> viewAllQuizz();


}
