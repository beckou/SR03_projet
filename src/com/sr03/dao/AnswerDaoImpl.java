package com.sr03.dao;

import static com.sr03.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sr03.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sr03.beans.Answer;


public class AnswerDaoImpl implements AnswerDAO {

	private DAOFactory daoFactory;
    private int noOfRecords;
    private static final String SQL_INSERT = "INSERT INTO Answers (idAnswer,  idQuest, intitule, status) VALUES (?, ?, ?, ?)";

    @Override

	public List<Answer> viewAllAnswer(int offset, int noOfRecords, int idQuestion) {
		Connection connexion = null;
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet = null;

 	   String SQL_SELECT_ALL = "SELECT SQL_CALC_FOUND_ROWS * from Answers limit" + offset + ", " + noOfRecords;
 	   List<Answer> list = new ArrayList<Answer>();
 	  Answer answer = null; 

       Statement stmt = null;
       
       System.out.println("Servlet Answer");

 	 
       String query = "select SQL_CALC_FOUND_ROWS * from Answers where idQuest ="+idQuestion+" limit "
               + offset + ", " + noOfRecords;
      try {
    	  connexion = (Connection) daoFactory.getConnection();
           stmt = (Statement) connexion.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
	    	  answer = map( rs );
	          list.add(answer);
          }
          rs.close();
           
          rs = stmt.executeQuery("SELECT FOUND_ROWS()");
          if(rs.next())
              this.noOfRecords = rs.getInt(1);
      } catch (SQLException e) {
          e.printStackTrace();
      }finally
      {
          try {
              if(stmt != null)
                  stmt.close();
              if(connexion != null)
            	  connexion.close();
              } catch (SQLException e) {
              e.printStackTrace();
          }
      }
 	    
 	    
 	    return list;
	}

	@Override
	public int getNoOfRecords() {
		// TODO Auto-generated method stub
        return noOfRecords;
	}

    AnswerDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	private static Answer map( ResultSet resultSet ) throws SQLException {
		Answer answer = new Answer();
	    answer.setId( resultSet.getInt( "idAnswer" ) );
	    answer.setIdQuestion(resultSet.getInt( "idQuest" ));
	    answer.setIntitule( resultSet.getString( "intitule" ) );
	    answer.setStatus( resultSet.getInt( "status" ) );

	    return answer;
	}

	@Override
	public void creer(Answer answer) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, answer.getId(), answer.getIdQuestion(), answer.getIntitule(), answer.getStatus());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */   
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la reponse, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                answer.setId( (int) valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de la reponse en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    	
	}

	@Override
	public Answer trouver(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modify(int idAnswer) {
		// TODO Auto-generated method stub
		
	}
}
