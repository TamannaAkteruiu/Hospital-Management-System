package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Database.Appoinment_DB_controll;
import Database.Doctor_DB_controll;
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
import javafx.beans.property.SimpleStringProperty;


public class Admin_dashboard_controll implements Initializable {
	
	@FXML
    private TableView<Appoinment> appoinment_table;
	
    @FXML
    private TableColumn<Appoinment, String> Appoinment_date;

    @FXML
    private TableColumn<Appoinment, String> Doctor_name;

    @FXML
    private TableColumn<Appoinment, String> appoinment_id;

    @FXML
    private TableColumn<Appoinment, String> patient_name;

    @FXML
    private TableColumn<Appoinment, String> status;
    

    @FXML
    private Label total_appoinment_shower;

    @FXML
    private Label total_doctor_shower;

    @FXML
    private Label total_patient_shower;

    @FXML
    private Label total_payment_shower;

    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Patient_DB_controll patient_db = new Patient_DB_controll();
		Doctor_DB_controll Doctor_DB = new Doctor_DB_controll();
		Appoinment_DB_controll db = new Appoinment_DB_controll();
    	List<Appoinment> list = db.getData();
    	
    	
    	
    	// Set up the table columns
    	appoinment_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	patient_name.setCellValueFactory(new PropertyValueFactory<>("name"));	
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	Appoinment_date.setCellValueFactory(new PropertyValueFactory<>("appoinment_date"));
    	Doctor_name.setCellValueFactory(cellData -> {
            String id = cellData.getValue().getDoctor_id();
            String doctorName;
			try {
				doctorName = Doctor_DB.getDoctorName(id);
				 return new SimpleStringProperty(doctorName);
			} catch (ClassNotFoundException | IOException e) {
				
				e.printStackTrace();
			}
			
			return new SimpleStringProperty(null);
        });
    	
    	
    	
    	// Convert the doctorList to an observable list
	    ObservableList<Appoinment> Data = FXCollections.observableArrayList(list);
	
	    // Set the table data
	    appoinment_table.setItems(Data);
		
		
		//show some data
	    
	    try {
	    	
	    	total_appoinment_shower.setText(Integer.toString(list.size()));
			total_doctor_shower.setText(Integer.toString(Doctor_DB.getDoctorData().size()));
			total_patient_shower.setText(Integer.toString(patient_db.getData().size()));
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	    
	    
	}
    
    
    

}
