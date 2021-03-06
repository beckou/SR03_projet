package com.sr03.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sr03.beans.Question;
import com.sr03.beans.User;
import com.sr03.dao.QuestionDAO;
import com.sr03.dao.UtilisateurDao;

public class NewQuestionForm {
    private static final String CHAMP_INTITULE= "intitule";
    private static final String CHAMP_IDQUIZZ  = "idQuizz";
    private static final String CHAMP_IDQUESTION  = "idQuestion";
    private static final String CHAMP_ORDRE  = "ordre";

    private QuestionDAO   questionDao;

    public NewQuestionForm( QuestionDAO questionDao ) {
        this.questionDao = questionDao;
    }
    

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }
    public void Modifyquestion( HttpServletRequest request ) {
    	System.out.println("entreedansmodifyquestion");
        String intitule = getValeurChamp( request, CHAMP_INTITULE );
        int questionID = Integer.parseInt(getValeurChamp( request, CHAMP_IDQUESTION ));
        int ordre = Integer.parseInt(getValeurChamp1( request, CHAMP_ORDRE));
    	System.out.println("apres");

        
        if ( erreurs.isEmpty() ) {
        	questionDao.modifier(questionID,intitule,ordre);
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

    }
    
    public Question creerQuestion( HttpServletRequest request ) {
        String intitule = getValeurChamp( request, CHAMP_INTITULE );
        int quizzID = Integer.parseInt(getValeurChamp( request, CHAMP_IDQUIZZ ));

        Question question = new Question();
        question.setId(null);
        question.setIdQuizz(quizzID);
        question.setIntitule(intitule);
        
        
        
        if ( erreurs.isEmpty() ) {
        	questionDao.creer(question);
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

        return question;
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
    private static String getValeurChamp1( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null ) {
            return null;
        } else {
            return valeur;
        }
    }
}
