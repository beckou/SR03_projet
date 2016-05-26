package com.sr03.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class first_page_admin
 */
@WebServlet("/first_page_user")
public class first_page_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/first_page_user.jsp";


    
    
    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public first_page_user() {
        super();
        // TODO Auto-generated constructor stub
    }

    

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
       // request.getSession().getServletContext().log("coucoucouc");
	}


}
