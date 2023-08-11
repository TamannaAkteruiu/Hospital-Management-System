package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Database.Doctor_DB_controll;
import Database.Patient_DB_controll;
import Model.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Patient_profile_controll {
	private ArrayList<Patient> list = new ArrayList<>();
	
    @FXML
    private TextArea dp_address;

    @FXML
    private Label dp_address_shower;

    @FXML
    private Label dp_email_shower;

    @FXML
    private ChoiceBox<String> dp_gander;

    @FXML
    private Label dp_gander_shower;

    @FXML
    private Label dp_id_shower;

    @FXML
    private TextField dp_mobile_number;

    @FXML
    private TextField dp_name;

    @FXML
    private Label dp_name_shower;

    @FXML
    private Label dp_pn_shower;

    @FXML
    private Label dp_status_shower;

    @FXML
    void updateBtn(ActionEvent event) throws ClassNotFoundException, IOException {
    	Patient_DB_controll db = new Patient_DB_controll();
    	   
    	String name = dp_name.getText();
    	String address = dp_address.getText();
    	String gander = dp_gander.getValue();
    	String phone_no = dp_mobile_number.getText();
    	String gmail = login_controll.getGmail();
    	
    	db.updateProfileData(gmail, name, address, gander, phone_no);
    	showUpdateAlert();
    	
    	//just reload data
    	dataLoad();
    	
    }
    
    private void showUpdateAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Update Successful");
        alert.setHeaderText(null);
        alert.setContentText("Data has been successfully updated!");

        alert.showAndWait();
    }
    
    
    void dataLoad() throws ClassNotFoundException, IOException {
    	//login_controll.getGmail();
    	Patient_DB_controll db = new Patient_DB_controll();
    	list = db.searchPatientData(login_controll.getGmail());
    	
    	for(int i=0; i<list.size(); i++) {
    		dp_id_shower.setText(list.get(i).getId());
        	dp_name_shower.setText(list.get(i).getName());
        	dp_gander_shower.setText(list.get(i).getGender());
        	dp_pn_shower.setText(list.get(i).getPhoneNumber());
        	dp_email_shower.setText(list.get(i).getEmail());
        	dp_address_shower.setText(list.get(i).getAddress());
        	dp_status_shower.setText(list.get(i).getStatus()); 
    	}
        
    	//just check the output on the console
    	db.showAllData(list);

    }
    
    
    @FXML
    void initialize() throws ClassNotFoundException, IOException {

    	List<String> gander = new ArrayList<>();
    	gander.add("Male");
    	gander.add("Female");
    	gander.add("Custom");
    	dp_gander.getItems().addAll(gander);
    	
    	dataLoad();
    	
    }

}
