package com.sr03.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sr03.beans.User;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.UtilisateurDao;

/**
 * Servlet implementation class gestion_users
 */
@WebServlet("/gestion_users")
public class gestion_users extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/gestion_users.jsp";
   
   public static final String CONF_DAO_FACTORY = "daofactory";
    
    private UtilisateurDao     utilisateurDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestion_users() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page = 1;
        int recordsPerPage = 3;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<User> list = utilisateurDao.viewAllUsers((page-1)*recordsPerPage,
                                 recordsPerPage);
        
        request.getSession().getServletContext().log("userList lenghth is: "+ list.size());
        
        int noOfRecords = utilisateurDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
         
       // request.getSession().getServletContext().log("coucoucouc");
        
        request.setAttribute("userList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        //RequestDispatcher view = request.getRequestDispatcher("displayEmployee.jsp");
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/gestion_users.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
