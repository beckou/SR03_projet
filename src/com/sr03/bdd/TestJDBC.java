package com.sr03.bdd;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class TestJDBC {
    /* La liste qui contiendra tous les résultats de nos essais */
    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request ) {

    
    /* Chargement du driver JDBC pour MySQL */
    try {
        messages.add( "Chargement du driver..." );
        Class.forName( "com.mysql.jdbc.Driver" );
        messages.add( "Driver chargé !" );
    } catch ( ClassNotFoundException e ) {
        messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                + e.getMessage() );
    }

    /* Connexion à la base de données */
	/* Connexion à la base de données */
	String url = "jdbc:mysql://tuxa.sme.utc:3306/sr03p038";
	String utilisateur = "sr03p038";
	String motDePasse = "zbvYF8RB";
    Connection connexion = null;
    Statement statement = null;
    ResultSet resultat = null;
    try {
        messages.add( "Connexion à la base de données..." );
        connexion = (Connection) DriverManager.getConnection( url, utilisateur, motDePasse );
        messages.add( "Connexion réussie !" );

        /* Création de l'objet gérant les requêtes */
        statement = (Statement) connexion.createStatement();
        messages.add( "Objet requête créé !" );

        /* Exécution d'une requête de lecture */
        resultat = statement.executeQuery( "SELECT * FROM User;" );
        messages.add( "Requête \"SELECT id, email, mot_de_passe, nom FROM Utilisateur;\" effectuée !" );
 
        /* Récupération des données du résultat de la requête de lecture */
        while ( resultat.next() ) {
        	int idUtilisateur = resultat.getInt( "userID" );
    	    String nom = resultat.getString( "nom" );
    	    String mail = resultat.getString( "mail" );
    	    String motDePasseUtilisateur = resultat.getString( "mdp" );
    	    String societe = resultat.getString( "societe" );

    	    /* Formatage des données pour affichage dans la JSP finale. */
            messages.add( "Données retournées par la requête : id = " + idUtilisateur + ", nom = " + nom
                    + ", mail = "
                    + mail + ", mdp = " + motDePasseUtilisateur+ ", societe = " + societe + "." );
        }
    } catch ( SQLException e ) {
        messages.add( "Erreur lors de la connexion : <br/>"
                + e.getMessage() );
    } finally {
        messages.add( "Fermeture de l'objet ResultSet." );
        if ( resultat != null ) {
            try {
                resultat.close();
            } catch ( SQLException ignore ) {
            }
        }
        messages.add( "Fermeture de l'objet Statement." );
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException ignore ) {
            }
        }
        messages.add( "Fermeture de l'objet Connection." );
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException ignore ) {
            }
        }
    }

    return messages;
    
}
    
    
    
    
}