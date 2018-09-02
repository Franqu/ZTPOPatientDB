package com.patient;

import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public interface IForm {
	JFrame create (String formName);
	//JFrame create (String formName, Patient patient);
	//JPanel addTextField (String fieldName,Patient patient);
	JPanel addTextField(String fieldName);
	
}
