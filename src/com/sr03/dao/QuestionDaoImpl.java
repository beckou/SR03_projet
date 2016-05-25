package com.sr03.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sr03.beans.Question;
import com.sr03.beans.Quizz;
import com.sr03.beans.User;

public class QuestionDaoImpl implements QuestionDAO {

	private DAOFactory daoFactory;
    private int noOfRecords;

    @Override

	public List<Question> viewAllQuestion(int offset, int noOfRecords, int idQuizz) {
		Connection connexion = null;
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet = null;

 	   String SQL_SELECT_ALL = "SELECT SQL_CALC_FOUND_ROWS * from Questions limit" + offset + ", " + noOfRecords;
 	   List<Question> list = new ArrayList<Question>();
       Question question = null; 

       Statement stmt = null;
       
       System.out.println("Servlet Question");

 	 
       String query = "select SQL_CALC_FOUND_ROWS * from Questions where idQuestionnary ="+idQuizz+" limit "
               + offset + ", " + noOfRecords;
      try {
    	  connexion = (Connection) daoFactory.getConnection();
           stmt = (Statement) connexion.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
	    	  question = map( rs );
	          list.add(question);
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

    QuestionDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
	private static Question map( ResultSet resultSet ) throws SQLException {
	    Question question = new Question();
	    question.setId( resultSet.getInt( "idQuestion" ) );
	    question.setIdQuizz( resultSet.getInt( "idQuestionnary" ) );
	    question.setIntitule( resultSet.getString( "intitule" ) );
	    return question;
	}

	@Override
	public void creer(Question question) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question trouver(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}
