package com.sr03.dao;

import java.util.List;

import com.sr03.beans.User;

public interface UtilisateurDao {

    void creer( User utilisateur ) throws DAOException;

    User trouver( String email ) throws DAOException;

	List<User> viewAllUsers(int offset, int noOfRecords);
	
	int getNoOfRecords();

}