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

import com.sr03.beans.CoupleReponseParcours;
import com.sr03.beans.Parcours;
import com.sr03.beans.Question;
import com.sr03.beans.Quizz;
import com.sr03.beans.User;
import com.sr03.dao.DAOFactory;
import com.sr03.dao.ParcoursDAO;
import com.sr03.dao.QuestionDAO;
import com.sr03.dao.QuizzDAO;
import com.sr03.dao.UtilisateurDao;
import com.sr03.forms.ModifyQuizzForm;
import com.sr03.forms.NewQuestionForm;


@WebServlet("/view_parcours")
public class view_parcours extends HttpServlet{

	private static final long serialVersionUID = 1L;
    public static final String VUE              = "/WEB-INF/view_parcours.jsp";
   public static final String CONF_DAO_FACTORY = "daofactory";
   private User utilisateur;
   private UtilisateurDao userDAO;

    private Long idUser;
    private ParcoursDAO     parcoursDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.parcoursDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getParcoursDao();
        this.userDAO = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();

    }
	
    public view_parcours() {
        super();
        // TODO Auto-generated constructor stub
    }
    
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println(request.getRequestURL());
		int page = 1;
        int recordsPerPage = 3;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
    	utilisateur =  (User) request.getSession().getAttribute("sessionUtilisateur");
    	if(utilisateur != null){
    	idUser = userDAO.trouver(utilisateur.getMail()).getId();

    	System.out.println(utilisateur.getMail());
    	}
        List<Parcours> list = parcoursDAO.viewAllParcours((page-1)*recordsPerPage,
                                 recordsPerPage,idUser);
        int y = 0;
        List<CoupleReponseParcours> liste = new ArrayList<CoupleReponseParcours>();

        while (y < list.size()){
        	
        	liste.addAll(parcoursDAO.viewAllCouple(list.get(y).getId()));
        	
        	y+=1;
        }

        int i = 0;
        int scoreMoyen = 0;
        int tempsMoyen = 0;
        for(i = 0; i < list.size(); i++){
        	
        	scoreMoyen += list.get(i).getScore();
        	tempsMoyen += list.get(i).getTime();
        }
        if(list.size() != 0) {
        scoreMoyen /= list.size();
        tempsMoyen /= list.size();
        }
        request.getSession().getServletContext().log("parcours lenghth is: "+ list.size());
        
        int noOfRecords = parcoursDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        request.setAttribute("tMoyen", tempsMoyen);
        request.setAttribute("sMoyen", scoreMoyen);
        request.setAttribute("userID", idUser);
        request.setAttribute("parcoursList", list);
        request.setAttribute("answerList", liste);

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/view_parcours.jsp");
        view.forward(request, response);
	}
    

    
    
}
