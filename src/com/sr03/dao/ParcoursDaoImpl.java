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
import com.sr03.beans.Parcours;


public class ParcoursDaoImpl implements ParcoursDAO {

	private DAOFactory daoFactory;
    private int noOfRecords;
    private static final String SQL_INSERT = "INSERT INTO Parcours (idAnswer,  idQuest, intitule, status) VALUES (?, ?, ?, ?)";

    

	@Override
	public int getNoOfRecords() {
		// TODO Auto-generated method stub
        return noOfRecords;
	}

    ParcoursDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	private static Parcours map( ResultSet resultSet ) throws SQLException {
		Parcours parcours = new Parcours();
		parcours.setId( resultSet.getInt( "idParcours" ) );
		parcours.setIdQuizz(resultSet.getInt( "idQuestionnary" ));
		parcours.setUserID(resultSet.getInt( "Associated_user" ));
		parcours.setScore(resultSet.getInt( "score" ));
		parcours.setTime(resultSet.getInt( "time" ));
	    return parcours;
	}

	@Override
	public void creer(Parcours parcours) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parcours trouver(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parcours> viewAllParcours(int offset, int noOfRecords, int id_user) {
		Connection connexion = null;
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet = null;

 	   String SQL_SELECT_ALL = "SELECT SQL_CALC_FOUND_ROWS * from Parcours limit" + offset + ", " + noOfRecords;
 	   List<Parcours> list = new ArrayList<Parcours>();
       Parcours parcours = null; 

       Statement stmt = null;
       
       System.out.println("Servlet Parcours");

       String query = "select SQL_CALC_FOUND_ROWS * from Parcours where Associated_user ="+id_user+" limit "
               + offset + ", " + noOfRecords;
      try {
    	  connexion = (Connection) daoFactory.getConnection();
           stmt = (Statement) connexion.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
	    	  parcours = map( rs );
	          list.add(parcours);
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
	public List<Parcours> viewAllParcoursQ(int offset, int noOfRecords, int id_quizz) {
		Connection connexion = null;
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet = null;

 	   String SQL_SELECT_ALL = "SELECT SQL_CALC_FOUND_ROWS * from Parcours limit" + offset + ", " + noOfRecords;
 	   List<Parcours> list = new ArrayList<Parcours>();
       Parcours parcours = null; 

       Statement stmt = null;
       
       System.out.println("Servlet Parcours");

       String query = "select SQL_CALC_FOUND_ROWS * from Parcours where idQuestionnary ="+id_quizz+" limit "
               + offset + ", " + noOfRecords;
      try {
    	  connexion = (Connection) daoFactory.getConnection();
           stmt = (Statement) connexion.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
	    	  parcours = map( rs );
	          list.add(parcours);
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
