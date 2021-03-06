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
import com.sr03.beans.CoupleReponseParcours;
import com.sr03.beans.Parcours;


public class ParcoursDaoImpl implements ParcoursDAO {

	private DAOFactory daoFactory;
    private int noOfRecords;
    private static final String SQL_INSERT = "INSERT INTO Parcours (idParcours,  Associated_user, idQuestionnary, score, time) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT1 = "INSERT INTO assoc_Answers_Parcours (idParcour,  idAnswer) VALUES (?, ?)";

    

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
		parcours.setUserID(Integer.toUnsignedLong(resultSet.getInt( "Associated_user" )));
		parcours.setScore(resultSet.getInt( "score" ));
		parcours.setTime(resultSet.getInt( "time" ));
	    return parcours;
	}

	@Override
	public void creer(Parcours parcours) throws DAOException {
    	Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, parcours.getId(), parcours.getUserID(), parcours.getIdQuizz(),parcours.getScore(),parcours.getTime() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */   
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du parcours, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                parcours.setId( (int) valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création du parcours en base, aucun ID auto-généré retourné." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }		
	}

	@Override
	public Parcours trouver(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parcours> viewAllParcours(int offset, int noOfRecords, Long id_user) {
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

	@Override
	public List<Parcours> viewAllParcours(Long idUser) {
		Connection connexion = null;
 	    PreparedStatement preparedStatement = null;
 	    ResultSet resultSet = null;

 	   String SQL_SELECT_ALL = "SELECT SQL_CALC_FOUND_ROWS * from Parcours limit";
 	   List<Parcours> list = new ArrayList<Parcours>();
       Parcours parcours = null; 

       Statement stmt = null;
       
       System.out.println("Servlet Parcours");

       String query = "select SQL_CALC_FOUND_ROWS * from Parcours where Associated_user ="+idUser;
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
	public void creerCouple(CoupleReponseParcours couple) {
		// TODO Auto-generated method stub
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = (Connection) daoFactory.getConnection();
            
            System.out.println(" parcours :"+couple.getIdParcours()+" Answer = "+couple.getIdAnswer());
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT1, true, couple.getIdParcours(),  couple.getIdAnswer());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */   
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création du parcours, aucune ligne ajoutée dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }		
	}

	@Override
	public List<CoupleReponseParcours> viewAllCouple(Integer ParcoursID) {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	   String SQL_SELECT_ALL = "SELECT SQL_CALC_FOUND_ROWS * from assoc_Answers_Parcours where idParcour ="+ParcoursID;
	   List<CoupleReponseParcours> list = new ArrayList<CoupleReponseParcours>();
	   CoupleReponseParcours parcours = null; 

   Statement stmt = null;
   
   System.out.println("Servlet Parcours");

   String query = "select SQL_CALC_FOUND_ROWS * from assoc_Answers_Parcours where idParcour ="+ParcoursID;
  try {
	  connexion = (Connection) daoFactory.getConnection();
       stmt = (Statement) connexion.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
    	  parcours = map1( rs );
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
	
	private static CoupleReponseParcours map1( ResultSet resultSet ) throws SQLException {
		CoupleReponseParcours couple = new CoupleReponseParcours();
			couple.setIdParcours(resultSet.getInt("idParcour"));
			couple.setIdAnswer(resultSet.getInt("idAnswer"));
	    return couple;
	}
	
	
}
