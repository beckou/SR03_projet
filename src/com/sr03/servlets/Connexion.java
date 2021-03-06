package com.sr03.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sr03.beans.User;
import com.sr03.dao.UtilisateurDao;
import com.sr03.forms.ConnexionForm;
import com.sr03.dao.DAOFactory;

public class Connexion extends HttpServlet {
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/connexion.jsp";
    public static final String VUE_2              = "/WEB-INF/first_page_admin.jsp";
    public static final String VUE_3              = "/WEB-INF/first_page_user.jsp";
    public static final String CONF_DAO_FACTORY = "daofactory";
    
    private UtilisateurDao     utilisateurDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }
    
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm(utilisateurDao);

        /* Traitement de la requête et récupération du bean en résultant */
        User utilisateur = form.connecterUtilisateur( request );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
        	
  		  session.setAttribute( ATT_SESSION_USER, utilisateur );

        	if(utilisateur.getIsAdmin()){
        		// user is an admin
                  this.getServletContext().getRequestDispatcher( VUE_2 ).forward( request, response );

        	}else{
        		// user is not an admin

                this.getServletContext().getRequestDispatcher( VUE_3 ).forward( request, response );

        	}
        	
          
            
            
            
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
            //this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

            request.setAttribute( ATT_FORM, form );
            request.setAttribute( ATT_USER, utilisateur );

            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
            
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

      // this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}