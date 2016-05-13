package com.sr03.dao;

import com.sr03.beans.User;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private DAOFactory daoFactory;

	
    /* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
    @Override
    public User trouver( String email ) throws DAOException {
        return null;
    }

    /* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
    @Override
    public void creer( User utilisateur ) throws IllegalArgumentException, DAOException {
    }
    
    
    UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
}