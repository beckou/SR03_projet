package com.sr03.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.joda.time.DateTime;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sr03.beans.User;
import static com.sr03.dao.DAOUtilitaire.*;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private DAOFactory daoFactory;

    //private static final String SQL_SELECT_PAR_EMAIL = "SELECT userID, mail, nom, mdp, dateCrea, tel, status, societe FROM User WHERE mail = ?";
    private static final String SQL_SELECT_PAR_EMAIL = "SELECT * FROM User WHERE mail = ?";
   // private static final String SQL_SELECT_PAR_EMAIL = "SELECT mail FROM User WHERE mail = ?";

    private static final String SQL_INSERT = "INSERT INTO User (userID,  mail, nom, mdp, dateCrea, tel, status, societe) VALUES (?, ?, ?,?, NOW(),?,?,?)";
    
	/*
	 * Implémentation de la méthode trouver() définie dans l'interface
	 * UtilisateurDao
	 */
    @Override
	public User trouver( String email ) throws DAOException {
    	 Connection connexion = null;
    	    PreparedStatement preparedStatement = null;
    	    ResultSet resultSet = null;
    	    User utilisateur = null;

    	    try {
    	        /* Récupération d'une connexion depuis la Factory */
    	        connexion = (Connection) daoFactory.getConnection();
    	        preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_EMAIL, false, email );
    	        resultSet = preparedStatement.executeQuery();
    	        /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
    	        if ( resultSet.next() ) {
    	        	// ligne sûrement à décommenter plus tard
    	        	
    	            utilisateur = map( resultSet );
    	            
    	            
    	        }
    	    } catch ( SQLException e ) {
    	        throw new DAOException( e );
    	    } finally {
    	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
    	    }

    	    return utilisateur;
    }

    /* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
    @Override
    public void creer( User utilisateur ) throws IllegalArgumentException, DAOException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, utilisateur.getId(), utilisateur.getMail(), utilisateur.getLastname(),utilisateur.getPassword(), utilisateur.getDateInscription(), utilisateur.getPhone(), utilisateur.getState(), utilisateur.getCompany() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */   
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                utilisateur.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    	
    }
    
    
    UtilisateurDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    
    
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 */
	private static User map( ResultSet resultSet ) throws SQLException {
	    User utilisateur = new User();
	    utilisateur.setId( resultSet.getLong( "userID" ) );
	    utilisateur.setMail( resultSet.getString( "mail" ) );
	    utilisateur.setPassword( resultSet.getString( "mdp" ) );
	    utilisateur.setLastname( resultSet.getString( "nom" ) );
	    utilisateur.setPhone(resultSet.getString( "tel" ));
	    utilisateur.setState(resultSet.getInt( "status" ));
	    utilisateur.setCompany(resultSet.getString( "societe" ));
	    utilisateur.setDateInscription(new DateTime( resultSet.getTimestamp( "dateCrea" ) ));
	    
	    return utilisateur;
	}
}