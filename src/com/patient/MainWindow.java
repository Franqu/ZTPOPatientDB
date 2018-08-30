package com.patient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MainWindow {
	final static String fileName = "PatientDB";
	private static SQLiteDB sqLiteDB = new SQLiteDB();
	private JFrame frame;
	private JTable patientTable= new JTable();
	private JScrollPane tableContainer;
	private JScrollPane scrollTable;
	private JButton btnAddPatient = new JButton("Add Patient");
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
		sqLiteDB.createNewTable(fileName);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		setScrollTable(new JScrollPane());
		frame.getContentPane().add(copyrightLabel, BorderLayout.PAGE_END);
		btnPanel = new JPanel();
		frame.getContentPane().add(btnPanel, BorderLayout.NORTH);
		
		frame.addWindowStateListener(new WindowStateListener() {
	        @Override
			public void windowStateChanged(WindowEvent event) {
	            boolean isMaximized = isMaximized(event.getNewState());
	            boolean wasMaximized = isMaximized(event.getOldState());

	            if (isMaximized && !wasMaximized) {
	            	SwingUtilities.invokeLater(new Runnable() {
	                    @Override
	                    public void run() {
	                    	defaultSize();
	                    }
	                });
	            } 
	            else if (wasMaximized && !isMaximized) {
	            	SwingUtilities.invokeLater(new Runnable() {
	                    @Override
	                    public void run() {
	                    	defaultSize();
	                    }
	                });
	            }
	        }
	    });
		tableContainer = new JScrollPane(patientTable);
		frame.getContentPane().add(tableContainer, BorderLayout.SOUTH);
	}
	 private static boolean isMaximized(int state) {
		    return (state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;
	}

}
