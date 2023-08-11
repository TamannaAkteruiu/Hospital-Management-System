package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Database.Appoinment_DB_controll;
import Database.Doctor_DB_controll;
import Database.Mail_DB_controll;
import Database.Patient_DB_controll;
import Model.Appoinment;
import Model.Patient;
import Model.mail;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Patient_dashboard_controll implements Initializable{

    @FXML
    private TableColumn<Appoinment, String> appinment_id;

    @FXML
    private TableColumn<Appoinment, String> appoinment_date;

    @FXML
    private TableView<Appoinment> appoinment_table;

    @FXML
    private TableColumn<Appoinment, String> doctor_name;

    @FXML
    private TextField mail_subject_TF;

    @FXML
    private TextArea mail_text_TF;

    @FXML
    private TableColumn<Appoinment, String> name;

    @FXML
    private TableColumn<Appoinment, String> status;

    @FXML
    void Mail_sent(ActionEvent event) {
    	
    	Patient_DB_controll p_db = new Patient_DB_controll();
    	ArrayList<Patient> list;
    	
    	list = p_db.getDataByGmail(login_controll.getGmail());
    	
    	if(!list.isEmpty()) {
    		
    		String id ="";
        	String name = list.get(0).getName();
        	String number = list.get(0).getPhoneNumber();
        	String subject = mail_subject_TF.getText();
        	String msg = mail_text_TF.getText();
        	String sender_mail_id = login_controll.getGmail();
        	String read_status = "no";
    		
        	
        	//Now we have to work with server socket.
        	

        	mail mailObject = new mail(id, name, sender_mail_id, number, subject, msg, read_status);

            // Assuming the server address and port are known
            String serverAddress = "127.0.0.1";
            int serverPort = 8089; // Use the same port as in MailServer 

            Mail_DB_controll mailDbController = new Mail_DB_controll();
            mailDbController.sendMailToServer(mailObject, serverAddress, serverPort);
        }
    	
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Doctor_DB_controll Doctor_DB = new Doctor_DB_controll();
		Appoinment_DB_controll db = new Appoinment_DB_controll();
    	List<Appoinment> list;
    	
    	
		try {
			
			list = db.getDataBasePatient(login_controll.getGmail());
			
	    	// Set up the table columns
	    	appinment_id.setCellValueFactory(new PropertyValueFactory<Appoinment,String>("id"));
	    	name.setCellValueFactory(new PropertyValueFactory<Appoinment,String>("name"));
	    	appoinment_date.setCellValueFactory(new PropertyValueFactory<Appoinment,String>("appoinment_date"));
	    	status.setCellValueFactory(new PropertyValueFactory<Appoinment,String>("status"));
	    	
	    	doctor_name.setCellValueFactory(cellData -> {
	    		String id = cellData.getValue().getDoctor_id();
	    		String doctorName;
				
				try {
					//fetch doctor name instead of doctor id 
					doctorName = Doctor_DB.getDoctorName(id);
					return new SimpleStringProperty(doctorName);
					
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
	    		
	    		return new SimpleStringProperty(null);
	    	});

	    	
	    	ObservableList<Appoinment> data  = FXCollections.observableArrayList(list);
	    	appoinment_table.setItems(data);
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    		
		
	}

}
