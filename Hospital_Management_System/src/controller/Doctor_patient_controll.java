package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Database.Patient_DB_controll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Doctor_patient_controll {

    @FXML
    private TextArea Address_TF;

    @FXML
    private TextField Patient_name_TF;

    @FXML
    private ChoiceBox<String> gender_TF;

    @FXML
    private TextField patientGmail_TF;

    @FXML
    private TextField phone_number_TF;

    @FXML
    void AddPatient_TF(ActionEvent event) throws IOException {
    	
    	String gmail = patientGmail_TF.getText();
    	String name = Patient_name_TF.getText();
    	String gender = gender_TF.getValue();
    	String phn = phone_number_TF.getText();
    	String address = Address_TF.getText();
    	
    	Patient_DB_controll db = new Patient_DB_controll();
    	
    	String res = db.inser_patient__data_By_Doctor(gmail, name, gender, phn, address,login_controll.getGmail());
    	
    	if(res.compareTo("success")==0) {
    		showUpdateAlert("Saved Successful","Data has been successfully Added!");
    		clearTextField();
    	}else {
    		showUpdateAlert("Insertion Failed!","Data has been failed to Added!");
    		clearTextField();
    	}
    	
    	
    }
    
    private void clearTextField() {
    	patientGmail_TF.setText("");
    	Patient_name_TF.setText("");
    	gender_TF.setValue("");
    	phone_number_TF.setText("");
    	Address_TF.setText("");
    }
    
	public static void showUpdateAlert(String title, String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    
    
    @FXML
    void initialize() throws ClassNotFoundException, IOException {

    	List<String> gander = new ArrayList<>();
    	gander.add("Male");
    	gander.add("Female");
    	gander.add("Custom");
    	gender_TF.getItems().addAll(gander);


    	
    }

}
