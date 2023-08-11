package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Database.Appoinment_DB_controll;
import Database.Patient_DB_controll;
import Model.Appoinment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Doctor_dashboard_controll implements Initializable{

	@FXML
	private TableView<Appoinment> Appoinment_table;
	
    @FXML
    private TableColumn<Appoinment, String> appoinment_date;

    @FXML
    private TableColumn<Appoinment, String> appoinment_id;

    @FXML
    private TableColumn<Appoinment, String> contact_no;

    @FXML
    private TableColumn<Appoinment, String> diagnosis;

    @FXML
    private TableColumn<Appoinment, String> name;

    @FXML
    private TableColumn<Appoinment, String> status;

    @FXML
    private Label total_active_patient;

    @FXML
    private Label total_appoinment;

    @FXML
    private Label total_patient;

    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Doctor_appoinment_controll DAC = new Doctor_appoinment_controll();
		Patient_DB_controll patient_db = new Patient_DB_controll();
		Appoinment_DB_controll db = new Appoinment_DB_controll();
		String user_gmail = login_controll.getGmail();
		
		
		try {
			
			List<Appoinment> list;
			
			//Fetch only logged doctor appointment data
			list = db.getData(user_gmail);
			
			
			// Set up the table columns
	    	appoinment_id.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
	    	contact_no.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
	    	diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
	    	appoinment_date.setCellValueFactory(new PropertyValueFactory<>("appoinment_date"));
	    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
	    	
	    	//convert ArrayList data to ObservableList
	    	ObservableList<Appoinment> data = FXCollections.observableArrayList(list); 
	    	Appoinment_table.setItems(data);
			
	    	
	    	//fetch data from database
	    	String T_patient = Integer.toString(patient_db.getPatientDataByCreator(user_gmail).size());
	    	String T_active_patient = Integer.toString(patient_db.getAactivePatientDataByCreator(user_gmail).size());
	    	String T_appointment = Integer.toString(DAC.getTotalAppoinment());
	    	
	    	
	    	//set some additional info
	    	total_patient.setText(T_patient);
	    	total_appoinment.setText(T_appointment);
	    	total_active_patient.setText(T_active_patient);
	    	
	    	
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    	
		
	}
    
    
    

}
