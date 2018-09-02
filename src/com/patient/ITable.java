package com.patient;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface ITable {
	JPanel create();
	DefaultTableModel buildTableModel (List<Patient> patientList);
	void loadDataFromDB();
}
