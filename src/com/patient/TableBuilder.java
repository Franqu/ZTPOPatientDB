package com.patient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class TableBuilder implements ITable{

	final static String fileName = "PatientDB";
	static JTable patientTable= new JTable();
	public JPanel create() {
		
		
		loadDataFromDB();
		JScrollPane tableContainer = new JScrollPane(patientTable);
		JPopupMenu tableMenu = new JPopupMenu();
		JMenuItem btnDeleteSelected = new JMenuItem("Delete Selected");
		//JMenuItem btnModifySelected = new JMenuItem("Modify Selected");
		/*btnModifySelected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SQLiteDBImpl sqLiteDBImpl = new SQLiteDBImpl();
				FormBuilder formBuilder = new FormBuilder();
				
				int id =  (int) patientTable.getModel().getValueAt(patientTable.getSelectedRow(), 0);
				Patient patient = new Patient();
				patient = sqLiteDBImpl.select(id);
				formBuilder.create("Modify", patient);
				//sqLiteDBImpl.modifySelected(patient);
				loadDataFromDB();
				
			}
		});*/
		btnDeleteSelected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SQLiteDBImpl sqLiteDBImpl = new SQLiteDBImpl();
				int id =  (int) patientTable.getModel().getValueAt(patientTable.getSelectedRow(), 0);
				sqLiteDBImpl.deleteSelected(id);
				System.out.println(id);
				loadDataFromDB();
				
			}
		});
		tableMenu.add(btnDeleteSelected);
		patientTable.setComponentPopupMenu(tableMenu);
		JPanel tablePanel = new JPanel(new BorderLayout());
		
		tablePanel.add(tableContainer,BorderLayout.CENTER);
		
		return tablePanel;
	}


	public DefaultTableModel buildTableModel(List<Patient> patientList) {
		 Vector<String> columnNames = new Vector<>();
		    columnNames.add("ID");
		    columnNames.add("Name");
		    columnNames.add("Surname");
		    columnNames.add("Identification");
		    columnNames.add("Country");
		    columnNames.add("PhoneNumber");
		    columnNames.add("Email");
		    columnNames.add("Age");
		
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    for ( Patient patient : patientList) {
			   Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 0 ; columnIndex < columnNames.size(); columnIndex++) {
		        		
		        	  vector.add(patient.getId());
		        	  vector.add(patient.getName());
		        	  vector.add(patient.getSurname());
		        	  vector.add(patient.getIdentification());
		        	  vector.add(patient.getCountry());
		        	  vector.add(patient.getPhoneNumber().intValue());
		        	  vector.add(patient.getEmail());
		        	  vector.add(patient.getAge().intValue());
		        }
		        data.add(vector);
		    }
		    return new DefaultTableModel(data, columnNames){
		    	
				private static final long serialVersionUID = -6622905133391297170L;

				@Override
		    	    public boolean isCellEditable(int row, int column) {
		    	        return false;
		    	    }
		    };
	}
	
		public void loadDataFromDB() {
			SQLiteDBImpl sqliteDB = new SQLiteDBImpl();
			List<Patient> patientList = new ArrayList<>();
			patientList.addAll(sqliteDB.selectAll());
			patientTable.setModel(buildTableModel(patientList));
		    }
		    
		    
		   
		    

	
	

}
