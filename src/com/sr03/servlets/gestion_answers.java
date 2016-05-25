package com.sr03.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sr03.beans.Answer;
import com.sr03.beans.Question;
import com.sr03.beans.Quizz;
import com.sr03.dao.AnswerDAO;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.QuestionDAO;
import com.sr03.dao.QuizzDAO;


@WebServlet("/gestion_answers")
public class gestion_answers extends HttpServlet{

	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/gestion_answers.jsp";
   
   public static final String CONF_DAO_FACTORY = "daofactory";
    private int idQuestion;
    private AnswerDAO     answerDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.answerDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getAnswerDao();
    }
	
    public gestion_answers() {
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
        if(request.getParameter("questID")!= null){
        	idQuestion = Integer.parseInt(request.getParameter("questID"));
            System.out.println("Servlet Quizz id :" + idQuestion);
        }else{
            System.out.println("Servlet Quizz id :" + request.getParameter("questID"));
        }
        List<Answer> list = answerDAO.viewAllAnswer((page-1)*recordsPerPage,
                                 recordsPerPage,idQuestion);
        
        request.getSession().getServletContext().log("answer lenghth is: "+ list.size());
        
        int noOfRecords = answerDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("questionID", idQuestion);
        request.setAttribute("answerList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/gestion_answers.jsp");
        view.forward(request, response);
	}
    

}
