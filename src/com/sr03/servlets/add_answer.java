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
import com.sr03.beans.User;
import com.sr03.dao.AnswerDAO;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.QuestionDAO;
import com.sr03.forms.InscriptionForm;
import com.sr03.forms.NewAnswerForm;
import com.sr03.forms.NewQuestionForm;

@WebServlet("/add_answer")
public class add_answer extends HttpServlet{

	private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/add_answer.jsp";

	public static final String ATT_ANSWER = "answer";
	public static final String ATT_FORM = "form";
	public static final String SESSION_USERS = "users";

	public static final String VUE_SUCCES = "/WEB-INF/gestion_of_quizz.jsp";
	public static final String VUE_FORM = "/WEB-INF/add_answer.jsp";

   public static final String CONF_DAO_FACTORY = "daofactory";
    private int idQuestion;
    private AnswerDAO     answerDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.answerDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getAnswerDao();
    }
	
    public add_answer() {
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
        if(request.getParameter("questionID")!= null){
        	idQuestion = Integer.parseInt(request.getParameter("questionID"));
            System.out.println("Servlet Quizz id :" + idQuestion);
        }else{
        	idQuestion = 3;
            System.out.println("Servlet Quizz id :" + request.getParameter("questionID"));
        }

        
        
        request.setAttribute("questionID", idQuestion);
        RequestDispatcher view = request.getRequestDispatcher(VUE);
        view.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NewAnswerForm form = new NewAnswerForm(answerDAO);

		/* Traitement de la requête et récupération du bean en résultant */
		Answer answer = form.creerAnswer(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_ANSWER, answer);
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


