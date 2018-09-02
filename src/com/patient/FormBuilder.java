package com.patient;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import org.jfree.util.StringUtils;

public class FormBuilder implements IForm {

	
	public JFrame create(String formName) {
		
		JFrame form = new JFrame();
		form.setBounds(100, 100, 450, 300);
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		form.getContentPane().setLayout(new BorderLayout(0, 0));
		form.setTitle(formName);
		JPanel btnPanel = new JPanel();
		JButton btnAdd = new JButton("Add");
		
		
		
		JButton btnCancel = new JButton("Cancel");
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				form.dispose();
				
			}
		});
		btnPanel.add(btnAdd);
		btnPanel.add(btnCancel);
		JPanel fieldContainer = new JPanel();
		fieldContainer.setLayout(new BoxLayout(fieldContainer,BoxLayout.Y_AXIS));
		fieldContainer.add(addTextField("Name"));
		fieldContainer.add(addTextField("Surname"));
		fieldContainer.add(addTextField("Identification"));
		fieldContainer.add(addTextField("Country"));
		fieldContainer.add(addTextField("PhoneNumber"));
		fieldContainer.add(addTextField("Email"));
		fieldContainer.add(addTextField("Age"));
		form.add(btnPanel,BorderLayout.SOUTH);
		form.add(fieldContainer,BorderLayout.NORTH);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Patient patient = new Patient();
				SQLiteDBImpl sqlite = new SQLiteDBImpl();
				patient.setName(getTextFieldByName("Name", fieldContainer).getText());
				patient.setSurname(getTextFieldByName("Surname", fieldContainer).getText());
				patient.setIdentification(getTextFieldByName("Identification", fieldContainer).getText());
				patient.setCountry(getTextFieldByName("Country", fieldContainer).getText());
				patient.setPhoneNumber(Double.parseDouble(getTextFieldByName("PhoneNumber", fieldContainer).getText()));
				patient.setEmail(getTextFieldByName("Email", fieldContainer).getText());
				patient.setAge(Double.parseDouble(getTextFieldByName("Age", fieldContainer).getText()));
				sqlite.insert(patient);
				TableBuilder tableImpl = new TableBuilder();
				tableImpl.loadDataFromDB();
				form.dispose();
			}
		});
		return form;
	}
	
	public JPanel addTextField(String fieldName) {
		JPanel textFieldPanel = new JPanel(new BorderLayout());
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMaximum(999999999);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
		formattedTextField.setName(fieldName);
		formattedTextField.setPreferredSize(new Dimension(300, 10));
		JTextField textField = new JTextField();
		textField.setName(fieldName);
		textField.setPreferredSize(new Dimension(300, 10));
		
		JLabel textFieldLabel = new JLabel(fieldName);
		switch (fieldName) {
		case "PhoneNumber":
			textFieldPanel.add(formattedTextField,BorderLayout.EAST);
		
			break;
		case "Age":
			textFieldPanel.add(formattedTextField,BorderLayout.EAST);
			break;

		default:
			textFieldPanel.add(textField,BorderLayout.EAST);
			break;
		}		
		textFieldPanel.add(textFieldLabel,BorderLayout.WEST);
		
		return textFieldPanel;
	}

	private Map<String,Component> createComponentMap(JPanel form) {
        Map <String,Component> componentMap = new HashMap<String,Component>();
        Component[] components = form.getComponents();
        for (int i=0; i < components.length; i++) {
        		Component[] component = ((JPanel) components[i]).getComponents();
        		for (int j=0; j < component.length; j++){
                componentMap.put(component[j].getName(), component[j]);
        		}
        }
		return componentMap;
}

	public JTextField getTextFieldByName(String name,JPanel form) {
		 Map <String,Component> componentMap = new HashMap<String,Component>();
		 componentMap = createComponentMap(form);
        if (componentMap.containsKey(name)) {
        	System.out.println(name);
                return (JTextField) componentMap.get(name);
        }
        else return null;
}

	/*@Override
	public JPanel addTextField(String fieldName, Patient patient) {
		JPanel textFieldPanel = new JPanel(new BorderLayout());
		JTextField textField = new JTextField();
		textField.setName(fieldName);
		textField.setPreferredSize(new Dimension(300, 10));
		switch (fieldName) {
		case "Name":
			textField.setText(patient.getName());
			break;
		case "Surname":
			textField.setText(patient.getSurname());
			break;
		case "Identification":
			textField.setText(patient.getIdentification());
			break;
		case "Country":
			textField.setText(patient.getCountry());
			break;
		case "PhoneNumber":
			textField.setText(Double.toString(patient.getPhoneNumber()));	
					break;		
		case "Email":
			textField.setText(patient.getEmail());
			break;		

		default:
			break;
		}
		JLabel textFieldLabel = new JLabel(fieldName);
		
		textFieldPanel.add(textFieldLabel,BorderLayout.WEST);
		textFieldPanel.add(textField,BorderLayout.EAST);
		return textFieldPanel;
	}

	@Override
	public JFrame create(String formName, Patient patient) {
		JFrame form = new JFrame();
		form.setBounds(100, 100, 450, 300);
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		form.getContentPane().setLayout(new BorderLayout(0, 0));
		form.setTitle(formName);
		JPanel btnPanel = new JPanel();
		JButton btnAdd = new JButton("Add");
		
		
		
		JButton btnCancel = new JButton("Cancel");
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				form.dispose();
				
			}
		});
		btnPanel.add(btnAdd);
		btnPanel.add(btnCancel);
		JPanel fieldContainer = new JPanel();
		fieldContainer.setLayout(new BoxLayout(fieldContainer,BoxLayout.Y_AXIS));
		fieldContainer.add(addTextField("Name"),patient);
		fieldContainer.add(addTextField("Surname"),patient);
		fieldContainer.add(addTextField("Identification"),patient);
		fieldContainer.add(addTextField("Country"),patient);
		fieldContainer.add(addTextField("PhoneNumber"),patient);
		fieldContainer.add(addTextField("Email"),patient);
		form.add(btnPanel,BorderLayout.SOUTH);
		form.add(fieldContainer,BorderLayout.NORTH);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Patient patient = new Patient();
				SQLiteDBImpl sqlite = new SQLiteDBImpl();
				patient.setName(getTextFieldByName("Name", fieldContainer).getText());
				patient.setSurname(getTextFieldByName("Surname", fieldContainer).getText());
				patient.setIdentification(getTextFieldByName("Identification", fieldContainer).getText());
				patient.setCountry(getTextFieldByName("Country", fieldContainer).getText());
				patient.setPhoneNumber(Double.parseDouble(getTextFieldByName("PhoneNumber", fieldContainer).getText()));
				patient.setEmail(getTextFieldByName("Email", fieldContainer).getText());
				sqlite.modifySelected(patient);
				TableImpl tableImpl = new TableImpl();
				tableImpl.loadDataFromDB();
				form.dispose();
			}
		});
		return form;
	}
*/
	

	


	
	


	

}
