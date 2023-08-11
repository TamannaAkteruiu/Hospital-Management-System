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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;



public class login_controll implements Initializable {
	private static String setGmail = null;
	
	@FXML
    private ImageView image;

    @FXML
    private Label exchangeable_header_text;

    @FXML
    private TextField log_email;

    @FXML
    private TextField log_user_pass;

    @FXML
    private Button login_btn;

    @FXML
    private Hyperlink signup_a_tag;

    @FXML
    private ChoiceBox<String> user_role_DD_list;

    @FXML
    void gotoSignupPageBtn(ActionEvent event) throws IOException {
    	sceneChange(event,"/GUI/Authentication_reg.fxml");
    }


    @FXML
    void login_controll_btn_action(ActionEvent event) throws IOException, ClassNotFoundException {
    	//System.out.println("check : ");
    	
    	String email = log_email.getText();    	
    	String pass = log_user_pass.getText();
    	String user_type = user_role_DD_list.getValue();
    	
    	if(user_type == "Admin") {
    		handleAdminlogin(event,email,pass);
    		
    	}else if(user_type == "Doctor") {
    		handleDoctorlogin(event,email,pass);

    	}else if(user_type == "Patient") {
    		patientalogin(event,email,pass);
    	}else {
    		System.out.print("Please select user role type!");
    	}
    
    }
    
    
    private String patientalogin(ActionEvent event,String email, String pass) throws ClassNotFoundException, IOException {
    	Patient_DB_controll db = new Patient_DB_controll();
    	String check = db.checkPatientAuth(email,pass);
		System.out.println(check);
    	
    	if(check.compareTo("match")==0) {
    		//use as session data
    		setGmail = email;
    		sceneChange(event,"/GUI/Patient.fxml");
    	}
    	
    	if(check.compareTo("pass_not_match")==0) {
    		showAlert("Authentication","Password and email not match!");
    	}
    	
    	if(check.compareTo("email_not_found")==0) {
    		showAlert("Authentication","Email not found!");
    	}
		
    	
    	return "";
    }
    
    private String handleDoctorlogin(ActionEvent event,String email,String pass) throws ClassNotFoundException, IOException {
    	Doctor_DB_controll db = new Doctor_DB_controll();
		String check = db.checkDoctorAuth(email,pass);
		System.out.println(check);
    	
    	if(check.compareTo("match")==0) {
    		//use as session data
    		setGmail = email;
    		sceneChange(event,"/GUI/Doctor.fxml");
    	}
    	
    	if(check.compareTo("pass_not_match")==0) {
    		showAlert("Authentication","Password and email not match!");
    	}
    	
    	if(check.compareTo("email_not_found")==0) {
    		showAlert("Authentication","Email not found!");
    	}
		
    	
    	return "";
    }

   
    private void handleAdminlogin(ActionEvent event,String email, String password) throws IOException{
    	//there we do not use any database....
    	String defEmail = "admin@gmail.com";
    	String defPass = "admin";
    	
    	if(email.compareTo(defEmail)==0 && password.compareTo(defPass)==0) {
    		setGmail = email; 
    		sceneChange(event,"/GUI/Admin.fxml");
    	    
    	}else {
    		showAlert("Authentication","Admin email and Password are not matched!");
    	}
    }
    
    
    public static void showAlert(String title, String message) {
        // Create a modal alert with an OK button
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        // Set the alert as modal and ensure it can be closed
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initStyle(StageStyle.UNDECORATED);
        
        // Add an OK button
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
        
        // Show the alert and wait for user interaction
        alert.showAndWait(); 
        
    }


	private void sceneChange(ActionEvent event,String url) throws IOException {
    	Stage currentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
	    Parent root = FXMLLoader.load(getClass().getResource(url));
	    Scene scene = new Scene(root);
	    Stage newStage = new Stage();
	    newStage.setScene(scene);
	    currentStage.close();
	    newStage.show();
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Image img = new Image(getClass().getResourceAsStream("/images/man-256x256.png"));
        image.setImage(img);
        
        List<String> u_r = new ArrayList<>();
        u_r.add("Admin");
        u_r.add("Doctor");
        u_r.add("Patient");
       
        user_role_DD_list.getItems().addAll(u_r);

    
		
	}

	public static String getGmail() {
		return setGmail;
	}

}

