package com.sr03.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sr03.beans.Parcours;
import com.sr03.beans.Question;
import com.sr03.beans.User;
import com.sr03.dao.ParcoursDAO;
import com.sr03.dao.ParcoursDAO;
import com.sr03.dao.UtilisateurDao;

public class NewParcoursForm {
    private static final String CHAMP_INTITULE= "intitule";
    private static final String CHAMP_IDQUESTION  = "idQuizz";
    private User utilisateur;
    private ParcoursDAO   parcoursDao;
    private UtilisateurDao   userDao;
    private int quizzID;
    private Long idUser;
    public NewParcoursForm( ParcoursDAO ParcoursDao, UtilisateurDao userDao ) {
        this.parcoursDao = ParcoursDao;
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

    public Parcours creerParcours( HttpServletRequest request ) {
        int quizzID = Integer.parseInt(getValeurChamp( request, CHAMP_IDQUESTION ));
        List<Question> lList =null ;
        
    	utilisateur =  (User) request.getSession().getAttribute("sessionUtilisateur");
    	if(utilisateur != null){
    	idUser = userDao.trouver(utilisateur.getMail()).getId();
    	System.out.println(utilisateur.getMail());
    	}
        Parcours parcours = new Parcours();
        parcours.setId(null);
        parcours.setIdQuizz(quizzID);
        parcours.setScore(0);
        parcours.setUserID(idUser);
        parcours.setTime(0);
        
        
        if ( erreurs.isEmpty() ) {
        	parcoursDao.creer(parcours);
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

        return parcours;
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
    private static int getValeurChamp1( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null ) {
            return 0;
        } else {
            return 1;
        }

    }
    
}
