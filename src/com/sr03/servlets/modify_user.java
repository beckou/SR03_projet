package com.sr03.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sr03.beans.User;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.UtilisateurDao;

/**
 * Servlet implementation class modify_user
 */
@WebServlet("/modify_user")
public class modify_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/modify_user.jsp";
    public static final String userID       = "id";
    public static final String CONF_DAO_FACTORY = "daofactory";


    
    private UtilisateurDao     utilisateurDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modify_user() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
        HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Long, User> users = (HashMap<Long, User>) session.getAttribute( "users" );
		String size = Integer.toString(users.size());
		request.getSession().getServletContext().log(size);
		
		Long id = Long.valueOf(request.getParameter("id")).longValue();
		request.setAttribute("user",(User) users.get( id));
		
		if(request.getParameter("id") != null){
			request.setAttribute("id", request.getParameter("id") );
		}
			
		
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
