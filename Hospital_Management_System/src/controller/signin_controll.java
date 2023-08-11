package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Database.Doctor_DB_controll;
import Database.Patient_DB_controll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signin_controll implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label exchangeable_header_text;

    @FXML
    private TextField reg_user_con_pass;

    @FXML
    private TextField reg_user_gmail;

    @FXML
    private TextField reg_user_name;

    @FXML
    private TextField reg_user_pass;

    @FXML
    private Button signin_btn;

    @FXML
    private Hyperlink signup_a_tag;

    @FXML
    private ChoiceBox<String> user_role_DD_list;

    @FXML
    void gotoSignupPageBtn(ActionEvent event) throws IOException {
    	Stage currentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Authentication.fxml"));
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        currentStage.close();
        newStage.show();
    }

    @FXML
    void signin_controll_btn_action(ActionEvent event) throws IOException {
    	
    	String gmail = reg_user_gmail.getText();
    	String username = reg_user_name.getText();
    	String password = reg_user_pass.getText();
    	String con_password = reg_user_con_pass.getText();
    	String role =(String) user_role_DD_list.getValue();
    	
    	//create objects of database classes
    	Doctor_DB_controll DB_controll = new Doctor_DB_controll();
    	Patient_DB_controll P_DdB_controll = new Patient_DB_controll();
    	
    	
    	if(role.compareTo("Doctor")==0) {
    		String res = DB_controll.inser_doctor_reg_data(gmail,username,password,con_password);
    		
    		if(res.compareTo("pass_not_match")==0) {
    			 showPasswordMismatchAlert();
    		}
    		
    		if(res.compareTo("success")==0) {
    			gotoSignupPageBtn(event);
    		}
    		
    		
    	}else if(role.compareTo("Patient")==0){
    		
    		String res = P_DdB_controll.inser_patient_reg_data(gmail,username,password,con_password);
    		
    		if(res.compareTo("pass_not_match")==0) {
	   			 showPasswordMismatchAlert();
	   		}
	   		
	   		if(res.compareTo("success")==0) {
	   			gotoSignupPageBtn(event);
	   		}
    		
    	}
    	
    }
    
    public void showPasswordMismatchAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Password Mismatch");
        alert.setHeaderText(null);
        alert.setContentText("The two passwords do not match. Please try again.");
        alert.showAndWait();
    }
    
  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
        
        List<String> u = new ArrayList<>();
        u.add("Admin");
        u.add("Doctor");
        u.add("Patient");
        user_role_DD_list.getItems().addAll(u);
		
		
	}

}
