package com.sr03.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sr03.beans.Quizz;
import com.sr03.beans.User;
import com.sr03.dao.QuizzDAO;
import com.sr03.dao.UtilisateurDao;

public class ModifyQuizzForm {
    private static final String CHAMP_INTITULE= "intitule";
    private static final String CHAMP_IDQUIZZ  = "idQuizz";

    private QuizzDAO   quizzDao;

    public ModifyQuizzForm( QuizzDAO quizzDao ) {
        this.quizzDao = quizzDao;
    }
    

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public void Modifyquizz( HttpServletRequest request ) {
        String intitule = getValeurChamp( request, CHAMP_INTITULE );
        int quizzID = Integer.parseInt(getValeurChamp( request, CHAMP_IDQUIZZ ));

        
        
        
        if ( erreurs.isEmpty() ) {
        	quizzDao.modifier(quizzID,intitule);
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

    }
    public Quizz creerquizz( HttpServletRequest request ) {
        String intitule = getValeurChamp( request, CHAMP_INTITULE );

        Quizz quizz = new Quizz();
        quizz.setIntitule(intitule);
        
        
        
        if ( erreurs.isEmpty() ) {
        	//quizzDao.creer(quizzDao);
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

        return quizz;
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
