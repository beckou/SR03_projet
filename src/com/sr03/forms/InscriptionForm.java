package com.sr03.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sr03.beans.User;
import com.sr03.dao.UtilisateurDao;

public class InscriptionForm {
	    private static final String CHAMP_EMAIL  = "email";
	    private static final String CHAMP_PASS   = "motdepasse";
	    private static final String CHAMP_NOM    = "nom";
	    private static final String CHAMP_ENTREPRISE   = "entreprise";
	    private static final String CHAMP_TEL  = "tel";
	    private static final String CHAMP_STATUT  = "statut";

	    private UtilisateurDao   userDao;
	    
	    public InscriptionForm( UtilisateurDao userDao ) {
	        this.userDao = userDao;
	    }

	    private String              resultat;
	    private Map<String, String> erreurs         = new HashMap<String, String>();

	    public Map<String, String> getErreurs() {
	        return erreurs;
	    }

	    public String getResultat() {
	        return resultat;
	    }

	    public User creerUser( HttpServletRequest request ) {
	        String nom = getValeurChamp( request, CHAMP_NOM );
	        String telephone = getValeurChamp( request, CHAMP_TEL );
	        String email = getValeurChamp( request, CHAMP_EMAIL );
	        String mdp = getValeurChamp( request, CHAMP_PASS );
	        String entreprise = getValeurChamp( request, CHAMP_ENTREPRISE );
	        int statut = Integer.parseInt(getValeurChamp( request, CHAMP_STATUT ));
	        

	        User user = new User();

	        try {
	            validationNom( nom );
	        } catch ( Exception e ) {
	            setErreur( CHAMP_NOM, e.getMessage() );
	        }
	        user.setLastname( nom );


	        try {
	            validationTelephone( telephone );
	        } catch ( Exception e ) {
	            setErreur( CHAMP_TEL, e.getMessage() );
	        }
	        user.setPhone( telephone );

	        try {
	            validationEmail( email );
	        } catch ( Exception e ) {
	            setErreur( CHAMP_EMAIL, e.getMessage() );
	        }
	        user.setMail( email );

	        user.setPassword(mdp);
	        user.setCompany(entreprise);
	        user.setState(statut);
	        
	        
	        
	        if ( erreurs.isEmpty() ) {
	        	userDao.creer(user);
	            resultat = "Succès de la création du client.";
	        } else {
	            resultat = "Échec de la création du client.";
	        }

	        return user;
	    }

	    private void validationNom( String nom ) throws Exception {
	        if ( nom != null ) {
	            if ( nom.length() < 2 ) {
	                throw new Exception( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
	            }
	        } else {
	            throw new Exception( "Merci d'entrer un nom d'utilisateur." );
	        }
	    }



	    private void validationTelephone( String telephone ) throws Exception {
	        if ( telephone != null ) {
	            if ( !telephone.matches( "^\\d+$" ) ) {
	                throw new Exception( "Le numéro de téléphone doit uniquement contenir des chiffres." );
	            } else if ( telephone.length() < 4 ) {
	                throw new Exception( "Le numéro de téléphone doit contenir au moins 4 chiffres." );
	            }
	        } else {
	            throw new Exception( "Merci d'entrer un numéro de téléphone." );
	        }
	    }

	    private void validationEmail( String email ) throws Exception {
	        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    }

	    /*
	     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	     */
	    private void setErreur( String champ, String message ) {
	        erreurs.put( champ, message );
	    }

	    /*
	     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	     * sinon.
	     */
	    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }

    
}    

