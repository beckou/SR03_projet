package com.sr03.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sr03.beans.Question;
import com.sr03.beans.Quizz;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.QuestionDAO;
import com.sr03.dao.QuizzDAO;


@WebServlet("/gestion_of_quizz")
public class gestion_of_quizz extends HttpServlet{

	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/gestion_of_quizz.jsp";
   
   public static final String CONF_DAO_FACTORY = "daofactory";
    private int idQuizz;
    private QuestionDAO     questionDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.questionDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getQuestionDao();
    }
	
    public gestion_of_quizz() {
        super();
        // TODO Auto-generated constructor stub
    }
    
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println(request.getRequestURL());
		int page = 1;
        int recordsPerPage = 3;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        if(request.getParameter("quizzID")!= null){
        	idQuizz = Integer.parseInt(request.getParameter("quizzID"));
            System.out.println("Servlet Quizz id :" + idQuizz);
        }else{
        	idQuizz = 3;
            System.out.println("Servlet Quizz id :" + request.getParameter("quizzID"));

        }
        List<Question> list = questionDAO.viewAllQuestion((page-1)*recordsPerPage,
                                 recordsPerPage,idQuizz);
        
        request.getSession().getServletContext().log("question lenghth is: "+ list.size());
        
        int noOfRecords = questionDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("quizzID", idQuizz);
        request.setAttribute("questionList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/gestion_of_quizz.jsp");
        view.forward(request, response);
	}
    

}
