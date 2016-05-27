package com.sr03.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sr03.beans.CoupeQuestionReponse;
import com.sr03.beans.CoupleReponseParcours;
import com.sr03.beans.Parcours;
import com.sr03.beans.User;
import com.sr03.dao.AnswerDAO;
import com.sr03.dao.ParcoursDAO;
import com.sr03.dao.QuestionDAO;
import com.sr03.dao.ParcoursDAO;
import com.sr03.dao.UtilisateurDao;

public class CoupleAnswerForm {
    private User utilisateur;
    private int quizzID;
    private Long idUser;
    private ParcoursDAO parcoursDao;
    private QuestionDAO questionDao;

    public CoupleAnswerForm(ParcoursDAO parcoursDao, QuestionDAO questionDAO ) {
    	this.questionDao = questionDAO;
    	this.parcoursDao = parcoursDao;
    }
    

    private String              resultat;
    private Map<String, String> erreurs         = new HashMap<String, String>();

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }

    public CoupleReponseParcours creerCouple( HttpServletRequest request, int idParcours,int idAnswer ) {
         		int idAnswer1 = getValeurChamp2( request, Integer.toUnsignedString(idAnswer));
                CoupleReponseParcours couple = new CoupleReponseParcours();
                System.out.println("valeur de ID ANSWER:"+idAnswer1+" et id quest"+idAnswer);
                couple.setIdAnswer(idAnswer1);
                couple.setIdParcours(idParcours);
                
            if ( erreurs.isEmpty() ) {
        	parcoursDao.creerCouple(couple);
            resultat = "Succès de la création du client.";
        } else {
            resultat = "Échec de la création du client.";
        }

        return couple;
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
    private static int getValeurChamp2( HttpServletRequest request, String nomChamp ) {
        int valeur = Integer.parseInt(request.getParameter( nomChamp ));

            return valeur;
    }
}
