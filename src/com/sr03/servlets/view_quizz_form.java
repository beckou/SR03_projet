package com.sr03.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sr03.beans.Answer;
import com.sr03.beans.CoupeQuestionReponse;
import com.sr03.beans.Parcours;
import com.sr03.beans.Question;
import com.sr03.beans.Quizz;
import com.sr03.beans.User;
import com.sr03.dao.AnswerDAO;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.ParcoursDAO;
import com.sr03.dao.QuestionDAO;
import com.sr03.dao.QuizzDAO;

/**
 * Servlet implementation class first_page_admin
 */
@WebServlet("/view_quizz_form")
public class view_quizz_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/view_quizz_form.jsp";
    private User utilisateur;
    private QuestionDAO questionDAO;
    private ParcoursDAO parcoursDAO;
    private AnswerDAO answerDAO;
    private int idQuizz;
    public static final String CONF_DAO_FACTORY = "daofactory";
    private int idUser;
    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public view_quizz_form() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.questionDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getQuestionDao();
        this.parcoursDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getParcoursDao();
        this.answerDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getAnswerDao();

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		int page = 1;
        int recordsPerPage = 3;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        if(request.getParameter("userID")!= null)
        	idUser = Integer.parseInt(request.getParameter("userID"));
        if(request.getParameter("quizzID")!= null){
        	idQuizz = Integer.parseInt(request.getParameter("quizzID"));
            System.out.println("Servlet Quizz id :" + idQuizz);
        }else{
            System.out.println("Servlet Quizz id :pasbon");

        }
        List<Question> list = questionDAO.viewAllQuestion((page-1)*recordsPerPage,
                                 recordsPerPage,idQuizz);
        int i = 0;
        List<CoupeQuestionReponse> liste = new ArrayList<CoupeQuestionReponse>();

        while(i < list.size()){


            List<Answer> list_temp = answerDAO.viewAllAnswer((page-1)*recordsPerPage,
                    recordsPerPage,list.get(i).getId());
            if (list_temp != null && list != null)
            liste.add(new CoupeQuestionReponse(list.get(i),list_temp));
            i+=1;
        }
        
        
        request.getSession().getServletContext().log("question lenghth is: "+ list.size());
        
        int noOfRecords = questionDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

	        request.setAttribute("idQuizz", idQuizz);
	        request.setAttribute("userID", idUser);
	        request.setAttribute("questionList", liste);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        RequestDispatcher view = request.getRequestDispatcher(VUE);
	        view.forward(request, response);
	       
	}


}
