package com.sr03.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sr03.beans.User;
import com.sr03.dao.DAOException;
import com.sr03.dao.UtilisateurDao;

public final class ConnexionForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();
    private UtilisateurDao      utilisateurDao;
    
    private User temp; 
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public ConnexionForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }

    public User connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        User utilisateur = new User();

        /* Validation du champ email. */
        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setMail( email );

        try{
        	isUserInBdd(email);
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        
          
        
        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse( motDePasse );
          
            
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setPassword( motDePasse );
        
        try {
            isPasswordCorrect( utilisateur, motDePasse );
                 
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }

        ///// Connexion avec la base de donnée pour vérifier que l'utilisateur existe
        ///// Redirection vers la  page personnelle de l'utilsateur
//        
//        try {
//        	
//                User temp = utilisateurDao.trouver( utilisateur.getMail() ); // On cherche le user avec son email
//                
//                resultat = "Utilisateur trouvé.";
//
//              
//                String Password_Recup = temp.getPassword();
//                resultat = String.valueOf(motDePasse.length());
//
//
//                
//                if(Password_Recup.equals(motDePasse)){
//                resultat = "Et c'est le bon mot de passe! ";
//
//                	
//                }else{
//                	
//                }
//            
//                
//        	
//        	// comparer le mot de passe avec le mot de passe de cet utilisateur
//        	
//        	
//        } catch ( DAOException e ) {
//            resultat = "Échec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
//            e.printStackTrace();
//        }
        
        
        
        
        
        
        
        return utilisateur;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
        
     
    }
    private void isUserInBdd( String email ) throws Exception {
    	
        temp = utilisateurDao.trouver( email ); // On cherche le user avec son email

       if(temp == null){
    	   throw new Exception( "Utilisateur non trouvé" );
       }
        
     
    }
    
    
    private void isPasswordCorrect( User utilisateur, String password ) throws Exception {
    	
 
       String Password_Recup = utilisateur.getPassword();

       String bddpass = temp.getPassword();
       if(!Password_Recup.equals(bddpass)){
    	   throw new Exception( "Mot de passe erroné" );
       }
              
    }
    

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
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