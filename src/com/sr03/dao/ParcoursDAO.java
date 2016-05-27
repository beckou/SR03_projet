package com.sr03.dao;

import java.util.List;

import com.sr03.beans.Answer;
import com.sr03.beans.CoupleReponseParcours;
import com.sr03.beans.Parcours;
import com.sr03.beans.Question;
import com.sr03.beans.Quizz;

public interface ParcoursDAO {


    void creer( Parcours parcours ) throws DAOException;

    Parcours trouver( String id ) throws DAOException;

	List<Parcours> viewAllParcours(int offset, int noOfRecords, Long idUser);
	List<CoupleReponseParcours> viewAllCouple(Integer integer);

	int getNoOfRecords();
	
	List<Parcours> viewAllParcoursQ(int offset, int noOfRecords, int id_user);

	List<Parcours> viewAllParcours(Long idUser);

	void creerCouple(CoupleReponseParcours couple);



}
