package com.sr03.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sr03.dao.DAOFactory;
import com.sr03.dao.UtilisateurDao;

/**
 * Servlet implementation class delete_user
 */
@WebServlet("/delete_user")
public class delete_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String CHAMP_ID  = "id";
	public static final String VUE_SUCCES = "/WEB-INF/gestion_users.jsp";
	
    /**
     * @se
     * 
     * e HttpServlet#HttpServlet()
     */
	
	
	private UtilisateurDao userDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.userDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
	}
	
    public delete_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		  String idClient = getValeurChamp( request, CHAMP_ID );
	      Long id = Long.parseLong( idClient );
	      userDao.supprimerParId( id );
	      request.getSession().getServletContext().log("Execute la requête delete User");
	      
	      this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);

	      
	}
	
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
