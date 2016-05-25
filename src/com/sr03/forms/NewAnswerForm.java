package com.sr03.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sr03.beans.Answer;
import com.sr03.beans.User;
import com.sr03.dao.AnswerDAO;
import com.sr03.dao.UtilisateurDao;

public class NewAnswerForm {
    private static final String CHAMP_INTITULE= "intitule";
    private static final String CHAMP_IDQUESTION  = "idQuestion";
    private static final String CHAMP_STATUT  = "BonneReponse";

    private AnswerDAO   AnswerDao;

    public NewAnswerForm( AnswerDAO AnswerDao ) {
        this.AnswerDao = AnswerDao;
    }
    

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public Answer creerAnswer( HttpServletRequest request ) {
        String intitule = getValeurChamp( request, CHAMP_INTITULE );
        int quizzID = Integer.parseInt(getValeurChamp( request, CHAMP_IDQUESTION ));
        int statut = getValeurChamp1( request, CHAMP_STATUT );

        Answer Answer = new Answer();
        Answer.setId(null);
        Answer.setIdQuestion(quizzID);
        Answer.setIntitule(intitule);
        Answer.setStatus(statut);

        
        
        if ( erreurs.isEmpty() ) {
        	AnswerDao.creer(Answer);
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

        return Answer;
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
