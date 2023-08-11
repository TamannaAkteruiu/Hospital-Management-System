package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Database.Doctor_DB_controll;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Doctor_controll {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane admin_content_container_stack_pan;

    @FXML
    private ImageView admin_left_head_img;

    @FXML
    private AnchorPane admin_left_menu_container;

    @FXML
    private ImageView admin_left_menu_icon_admin;

    @FXML
    private ImageView admin_left_menu_icon_apointment;

    @FXML
    private ImageView admin_left_menu_icon_patients;

    @FXML
    private ImageView admin_left_menu_icon_logout;
    
    @FXML
    private ImageView admin_left_menu_icon_profile;

    @FXML
    private AnchorPane admin_left_menu_items_container;

    @FXML
    private AnchorPane admin_left_menu_items_container1;

    @FXML
    private AnchorPane admin_left_menu_items_container3;

    @FXML
    private AnchorPane admin_left_menu_items_container31;

    @FXML
    private AnchorPane admin_main_container;

    @FXML
    private ImageView image;

    @FXML
    private Label user_id_shower_label;

    @FXML
    private Label username_shower_label;
    
    @FXML
    private Label doctor_name_lavbel;

    @FXML
    void apoinment_action_btn(ActionEvent event) throws IOException {
    	//show dash-board container
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Doctor_apoinment.fxml"));
    	admin_content_container_stack_pan.getChildren().removeAll();
    	admin_content_container_stack_pan.getChildren().setAll(root);	
    }

    @FXML
    void dashboardBtn(ActionEvent event) throws IOException {
    	//show dash-board container
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Doctor_dashboard.fxml"));
    	admin_content_container_stack_pan.getChildren().removeAll();
    	admin_content_container_stack_pan.getChildren().setAll(root);
    }

    @FXML
    void patient_btn_action(ActionEvent event) throws IOException {
    	//show patients container
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Doctor_patients.fxml"));
    	admin_content_container_stack_pan.getChildren().removeAll();
    	admin_content_container_stack_pan.getChildren().setAll(root);
    	
    }
    
    @FXML
    void profile_action_btn(ActionEvent event) throws IOException {
    	//show patients container
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Doctor_profile.fxml"));
    	admin_content_container_stack_pan.getChildren().removeAll();
    	admin_content_container_stack_pan.getChildren().setAll(root);
    }
   

    @FXML
    void logOut_action_btn(ActionEvent event) throws IOException {
        // Create an Alert dialog with Confirmation type
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("Click 'Yes' to logout or 'No' to cancel.");

        // Show the dialog and wait for the user's response
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.OK) {
            System.out.println("Logout successful!");
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Authentication.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) admin_content_container_stack_pan.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
        } else {
            System.out.println("Logout canceled.");
            
        }
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
    	Image img = new Image(getClass().getResourceAsStream("/images/man-256x256.png"));
		Image patients_icn = new Image(getClass().getResourceAsStream("/images/icons8-username-48.png"));
		Image apmt_icn = new Image(getClass().getResourceAsStream("/images/Properties_256x256.png"));
		Image lout_icn = new Image(getClass().getResourceAsStream("/images/Log Out_24x24.png"));
		Image profile = new Image(getClass().getResourceAsStream("/images/man-48x48.png"));
		
		
		
		admin_left_menu_icon_admin.setImage(img);
		admin_left_head_img.setImage(img);
		admin_left_menu_icon_patients.setImage(patients_icn);
		admin_left_menu_icon_apointment.setImage(apmt_icn);
		admin_left_menu_icon_logout.setImage(lout_icn);
		admin_left_menu_icon_profile.setImage(profile);
		
    	
    	// Call the dashboardBtn function
        ActionEvent event = new ActionEvent();
        dashboardBtn(event);
        
        
        //show some data
        Doctor_DB_controll DB = new Doctor_DB_controll();
        String d_gmail = login_controll.getGmail();
        String doctorID = DB.getDoctorId(d_gmail);

		user_id_shower_label.setText(doctorID);
		username_shower_label.setText(d_gmail);
		doctor_name_lavbel.setText(DB.getDoctorName(doctorID));
			
		//System.out.println(DB.getDoctorName(doctorID));

        
    }
    

}
