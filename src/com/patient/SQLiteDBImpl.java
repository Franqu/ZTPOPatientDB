package com.patient;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDBImpl implements SQLiteDBDao {
	
	
	final static String fileName = "PatientDB";
	
	 public void createNewDatabase(String fileName) {
		 
	        String url = "jdbc:sqlite:" + fileName;
	 
	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }
	 
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 public void createNewTable(String fileName) {
	        // SQLite connection string
	        String url = "jdbc:sqlite:" + fileName;
	        
	        // SQL statement for creating a new table
	        String sql = "CREATE TABLE IF NOT EXISTS patient (\n"
	                + "	id integer PRIMARY KEY,\n"
	                + "	name text,\n"
	                + "	surname text,\n"
	                + "	identification text,\n"
	                + "	country text,\n"
	                + " phone_number real,\n"
	                + " age real,\n"
	                + " email text \n"
	                + ");";
	        
	        try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	 }
	 
	  public Connection getConnection(String fileName){
		      	
		      	Connection conn = null;
		          try {
		            
		              String url = "jdbc:sqlite:" + fileName;
		             
		              conn = DriverManager.getConnection(url);
		              
		              System.out.println("Connection to SQLite has been established.");
		              
		          } catch (SQLException e) {
		              System.out.println(e.getMessage());
		          } 
		      	return conn;
		      }

	@Override
	public void insert(Patient patient) {
		 try {
			 	Connection connection = getConnection(fileName);
	    		connection.setAutoCommit(false);
	    		PreparedStatement stmt=connection.prepareStatement("INSERT INTO patient (name, surname, identification, country, phone_number,email,age) VALUES (?,?,?,?,?,?,?);");
	    		stmt.setString(1, patient.getName());
	    		stmt.setString(2, patient.getSurname());
	    		stmt.setString(3, patient.getIdentification());
	    		stmt.setString(4, patient.getCountry());
	    		stmt.setDouble(5, patient.getPhoneNumber());
	    		stmt.setString(6, patient.getEmail());
	    		stmt.setDouble(7, patient.getAge());
	
	    		stmt.executeUpdate(); 
	    		stmt.close();
        		connection.commit();
	    		closeConnection();
             
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	public List<Patient> selectAll() {
		
		List<Patient> patientList = new ArrayList<Patient>();
         try {
        	 
        	 	Connection connection = getConnection(fileName);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM patient;");
                 
               
                while(resultSet.next()){
                	Patient patient = new Patient();
                    patient.setId(Integer.parseInt(resultSet.getString("id")));
                    patient.setName(resultSet.getString("name"));
                    patient.setSurname(resultSet.getString("surname"));
                    patient.setIdentification(resultSet.getString("identification"));
                    patient.setCountry(resultSet.getString("country"));
                    patient.setPhoneNumber(resultSet.getDouble("phone_number"));
                    patient.setEmail(resultSet.getString("email"));
                    patient.setAge(resultSet.getDouble("age"));
                    patientList.add(patient);
                }
                resultSet.close();
                statement.close();
                closeConnection();
                 
            } catch (SQLException e) {
                e.printStackTrace();
                
            }
		return patientList;
		
    }
		
	
	  
	public void closeConnection(){
	    try {
	    	Connection connection = getConnection(fileName);
	          if (connection != null) {
	              connection.close();
	          }
	        }
	    
	    catch (Exception e) { 
	        	 e.printStackTrace();
	        }
	    
	}


	public void deleteSelected(int id) {
		try{
			Connection connection = getConnection(fileName);
			Statement statement = connection.createStatement();
			 statement.executeUpdate("DELETE FROM patient WHERE id = " + id + ";");
			 statement.close();
			 closeConnection();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/*@Override
	public void modifySelected(Patient patient) {
		try {
		 	Connection connection = getConnection(fileName);
    		connection.setAutoCommit(false);
    		PreparedStatement stmt=connection.
    				prepareStatement("UPDATE patient SET "
    						+ "	name = ?,"
    						+ " surname = ?,"
    						+ " identification = ?,"
    						+ " country = ?,"
    						+ " phone_number = ?,"
    						+ "	email = ?"
    						+ " WHERE id = ?)"
    						+ " VALUES (?,?,?,?,?,?,?);");
    		stmt.setString(1, patient.getName());
    		stmt.setString(2, patient.getSurname());
    		stmt.setString(3, patient.getIdentification());
    		stmt.setString(4, patient.getCountry());
    		stmt.setDouble(5, patient.getPhoneNumber());
    		stmt.setString(6, patient.getEmail());
    		stmt.setInt(7, patient.getId());

    		stmt.executeUpdate(); 
    		stmt.close();
    		connection.commit();
    		closeConnection();
         
    } catch (SQLException e) {
        e.printStackTrace();
    }
		
	}*/

	@Override
	public Patient select(int id) {
	 	Patient patient = new Patient();
        try {
       	 	Connection connection = getConnection(fileName);
               PreparedStatement stmt=connection.
       				prepareStatement("SELECT * FROM patient where id = ?");
               stmt.setInt(1, id);
               ResultSet resultSet = stmt.executeQuery();
                   patient.setId(Integer.parseInt(resultSet.getString("id")));
                   patient.setName(resultSet.getString("name"));
                   patient.setSurname(resultSet.getString("surname"));
                   patient.setIdentification(resultSet.getString("identification"));
                   patient.setCountry(resultSet.getString("country"));
                   patient.setPhoneNumber(resultSet.getDouble("phone_number"));
                   patient.setEmail(resultSet.getString("email"));
                   patient.setAge(resultSet.getDouble("age"));
               resultSet.close();
               stmt.close();
               closeConnection();
                
           } catch (SQLException e) {
               e.printStackTrace();
               
           }
		return patient;
	}
		     
	 
}
