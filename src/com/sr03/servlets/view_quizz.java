package com.sr03.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sr03.beans.Parcours;
import com.sr03.beans.Question;
import com.sr03.beans.Quizz;
import com.sr03.beans.User;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.ParcoursDAO;
import com.sr03.dao.QuizzDAO;
import com.sr03.forms.NewParcoursForm;
import com.sr03.forms.NewQuestionForm;

/**
 * Servlet implementation class first_page_admin
 */
@WebServlet("/view_quizz")
public class view_quizz extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/view_quizz.jsp";
    private User utilisateur;
    private QuizzDAO quizzDAO;
    private ParcoursDAO parcoursDAO;
    public static final String CONF_DAO_FACTORY = "daofactory";

	public static final String ATT_Parcours = "parcours";
	public static final String ATT_FORM = "form";

	public static final String VUE_SUCCES = "/WEB-INF/view_quizz.jsp";
	public static final String VUE_FORM = "/WEB-INF/view_quizz_form.jsp";
    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public view_quizz() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.quizzDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getQuizzDao();
        this.parcoursDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getParcoursDao();

    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    	int page = 1;
        int recordsPerPage = 3;
        Long idUser = null;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
	        	utilisateur =  (User) request.getSession().getAttribute("utilisateur");
	        	if(utilisateur != null)
	        	idUser = utilisateur.getId();
	        	else idUser = (long) 7;
	        List<Quizz> list = quizzDAO.viewAllQuizz();
	        
	        int noOfRecords = quizzDAO.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

	        List<Parcours> list1 = parcoursDAO.viewAllParcours(idUser);
	        int i = 0;
	        for(i = 0; i < list1.size(); i++){
	        	
	        	if(list.contains(list1.get(i).getIdQuizz()))
	        	{
	        		list.remove(list1.get(i).getIdQuizz());
	        	}
	        }
	        
	        request.setAttribute("userID", idUser);
	        request.setAttribute("quizzList", list);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        RequestDispatcher view = request.getRequestDispatcher(VUE);
	        view.forward(request, response);
	       
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    
		// TODO Auto-generated method stub
		NewParcoursForm form = new NewParcoursForm(parcoursDAO);

		/* Traitement de la requête et récupération du bean en résultant */
		Parcours parcours = form.creerParcours(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_Parcours, parcours);
		request.setAttribute(ATT_FORM, form);

		if (form.getErreurs().isEmpty()) {
			/* Si aucune erreur, alors affichage de la fiche récapitulative */

			request.getSession().getServletContext().log("La création de la question s'est bien déroulée");

			this.getServletContext().getRequestDispatcher(VUE_SUCCES).forward(request, response);
		} else {
			/* Sinon, ré-affichage du formulaire de création avec les erreurs */
			this.getServletContext().getRequestDispatcher(VUE_FORM).forward(request, response);
		}
		

	        RequestDispatcher view = request.getRequestDispatcher(VUE);
	        view.forward(request, response);
	       
	}
}
