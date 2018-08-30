package com.patient;

import java.sql.Connection;

public interface SQLiteDBDao {
		void createNewDatabase(String fileName);
	    void createNewTable(String fileName);
	    Connection getConnection(String fileName);
	    
}
