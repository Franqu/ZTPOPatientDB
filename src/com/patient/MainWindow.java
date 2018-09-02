package com.patient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow {
	final static String fileName = "PatientDB";
	private static SQLiteDBImpl sqLiteDB = new SQLiteDBImpl();
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		TableBuilder tableImpl = new TableBuilder();
		FormBuilder formBuilder = new FormBuilder();
		JButton btnAddPatient = new JButton("Add Patient");
		
		JPanel patientTableContainer = new JPanel();
		JPanel btnPanel = new JPanel();
		patientTableContainer = tableImpl.create();
		sqLiteDB.createNewTable(fileName);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		btnPanel.add(btnAddPatient);
		
		
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		frame.getContentPane().add(patientTableContainer, BorderLayout.NORTH);
		
		btnAddPatient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame addPatientFrame = new JFrame();
				addPatientFrame = formBuilder.create("Add Patient");
				addPatientFrame.setVisible(true);
				
			}
		});
	}
	
	 private static boolean isMaximized(int state) {
		    return (state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;
	}
	 
	 

}
