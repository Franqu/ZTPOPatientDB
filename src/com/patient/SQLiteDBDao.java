package com.patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface SQLiteDBDao {
		void createNewDatabase(String fileName);
	    void createNewTable(String fileName);
	    Connection getConnection(String fileName);
	    void insert(Patient patient);
	    List<Patient> selectAll();
	    Patient select(int id);
	    void deleteSelected (int id);
	    void closeConnection();
	  //  void modifySelected(Patient patient);  
}
