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
import com.sr03.dao.DAOFactory;
import com.sr03.dao.QuestionDAO;
import com.sr03.forms.NewQuestionForm;

@WebServlet("/add_question")
public class add_question extends HttpServlet{

	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/add_question.jsp";


	public static final String ATT_QUESTION = "question";
	public static final String ATT_FORM = "form";
	public static final String SESSION_USERS = "users";

	public static final String VUE_SUCCES = "/WEB-INF/gestion_of_quizz.jsp";
	public static final String VUE_FORM = "/WEB-INF/add_question.jsp";

   public static final String CONF_DAO_FACTORY = "daofactory";
    private int idQuizz;
    private QuestionDAO     questionDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.questionDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getQuestionDao();
    }
	
    public add_question() {
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

        
        
        request.setAttribute("quizzID", idQuizz);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/add_question.jsp");
        view.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NewQuestionForm form = new NewQuestionForm(questionDAO);

		/* Traitement de la requête et récupération du bean en résultant */
		Question question = form.creerQuestion(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_QUESTION, question);
		request.setAttribute(ATT_FORM, form);

		if (form.getErreurs().isEmpty()) {
			/* Si aucune erreur, alors affichage de la fiche récapitulative */

			request.getSession().getServletContext().log("La création de la question s'est bien déroulée");

			this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
	}
}


