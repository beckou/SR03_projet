package com.sr03.dao;

import static com.sr03.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sr03.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sr03.beans.Quizz;
import com.sr03.beans.User;

public class QuizzDaoImpl implements QuizzDAO {

	private DAOFactory daoFactory;
    private int noOfRecords;
    private static final String SQL_MODIFY = "UPDATE Quizz SET intitule = ? WHERE idQuestionnary = ?  ";

	@Override
	public void creer(Quizz quizz) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Quizz trouver(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Quizz> viewAllQuizz(int offset, int noOfRecords) {
		Connection connexion = null;
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet = null;

 	   String SQL_SELECT_ALL = "SELECT SQL_CALC_FOUND_ROWS * from Quizz limit " + offset + ", " + noOfRecords;
 	   List<Quizz> list = new ArrayList<Quizz>();
       Quizz quizz = null; 

       Statement stmt = null;
       
       System.out.println("Servlet Quizz");

 	 
       String query = "select SQL_CALC_FOUND_ROWS * from Quizz limit "
               + offset + ", " + noOfRecords;
      try {
    	  connexion = (Connection) daoFactory.getConnection();
           stmt = (Statement) connexion.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
	    	  quizz = map( rs );
	          list.add(quizz);
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

    QuizzDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
	private static Quizz map( ResultSet resultSet ) throws SQLException {
	    Quizz quizz = new Quizz();
	    quizz.setId( resultSet.getInt( "idQuestionnary" ) );
	    quizz.setIntitule( resultSet.getString( "intitule" ) );
	    return quizz;
	}

	@Override
	public void modifier(int quizzID, String intitule) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_MODIFY, false, intitule, quizzID);
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de la question, aucune ligne ajoutée dans la table." );
            }

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }		
	}

	@Override
	public List<Quizz> viewAllQuizz() {
		Connection connexion = null;
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet = null;

 	   String SQL_SELECT_ALL = "SELECT * from Quizz";
 	   List<Quizz> list = new ArrayList<Quizz>();
       Quizz quizz = null; 

       Statement stmt = null;
       
       System.out.println("Servlet Quizz");

 	 
       String query = "select * from Quizz";
      try {
    	  connexion = (Connection) daoFactory.getConnection();
           stmt = (Statement) connexion.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
	    	  quizz = map( rs );
	          list.add(quizz);
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
    
}
