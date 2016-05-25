package com.sr03.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sr03.mail.EmailSessionBean;
import com.sr03.beans.User;
import com.sr03.dao.UtilisateurDao;
import com.sr03.dao.DAOFactory;
import com.sr03.forms.InscriptionForm;

/**
 * Servlet implementation class inscription
 */
@WebServlet("/inscription")
public class inscription extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "user";
	public static final String ATT_FORM = "form";
	public static final String SESSION_USERS = "users";
	

	public static final String VUE_SUCCES = "/WEB-INF/gestion_users.jsp";
	public static final String VUE_FORM = "/WEB-INF/add_user.jsp";

	private UtilisateurDao userDao;
	
	@EJB
	private EmailSessionBean emailBean;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.userDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public inscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// this.getServletContext().getRequestDispatcher( VUE_FORM ).forward(
		// request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* Préparation de l'objet formulaire */
		InscriptionForm form = new InscriptionForm(userDao);

		/* Traitement de la requête et récupération du bean en résultant */
		User user = form.creerUser(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_USER, user);
		request.setAttribute(ATT_FORM, form);
		
		

		if (form.getErreurs().isEmpty()) {
			/* Si aucune erreur, alors affichage de la fiche récapitulative */
			
			// envoie mail à l'user ajouté
			String to = user.getMail();
			String subject = "Inscrition site d'évaluation";
			String body = "login: "+ user.getMail()+" Mdp: "+user.getPassword();
			
			emailBean = new EmailSessionBean();
			emailBean.sendEmail(to, subject, body);
			
			
			
			
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
			Map<Long, User> users = (HashMap<Long, User>) session.getAttribute( SESSION_USERS );
            /* Si aucune map n'existe, alors initialisation d'une nouvelle map */
            if ( users == null ) {
            	users = new HashMap<Long, User>();
            }
            /* Puis ajout du client courant dans la map */
            users.put( user.getId(), user );
            /* Et enfin (ré)enregistrement de la map en session */
            session.setAttribute( SESSION_USERS, users );
			
			

			request.getSession().getServletContext().log("La création du user s'est bien déroulée");

			this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}
	

	
//	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		String to = request.getParameter("to");
//		String subject = request.getParameter("subject");
//		String body = request.getParameter("body");
//		
//		emailBean.sendEmail(to, subject, body);
//	
//	}

}
