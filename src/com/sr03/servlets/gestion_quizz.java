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

import com.sr03.beans.Quizz;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.QuizzDAO;

@WebServlet("/gestion_quizz")
public class gestion_quizz extends HttpServlet {

	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/gestion_quizz.jsp";
   
   public static final String CONF_DAO_FACTORY = "daofactory";
    
    private QuizzDAO     quizzDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.quizzDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getQuizzDao();
    }
	
    public gestion_quizz() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int page = 1;
        int recordsPerPage = 3;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        List<Quizz> list = quizzDAO.viewAllQuizz((page-1)*recordsPerPage,
                                 recordsPerPage);
        
        request.getSession().getServletContext().log("quizzList lenghth is: "+ list.size());
        
        int noOfRecords = quizzDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                 
        request.setAttribute("quizzList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/gestion_quizz.jsp");
        view.forward(request, response);
	}
    
}
