package controller;

import java.io.IOException;
import java.util.ArrayList;

import Database.Patient_DB_controll;
import Model.Patient;
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

public class Patient_controll {

    @FXML
    private StackPane admin_content_container_stack_pan;

    @FXML
    private ImageView admin_left_head_img;

    @FXML
    private AnchorPane admin_left_menu_container;

    @FXML
    private ImageView admin_left_menu_icon_admin;

    @FXML
    private ImageView admin_left_menu_icon_dashboard;

    @FXML
    private ImageView admin_left_menu_icon_logout;

    @FXML
    private ImageView admin_left_menu_icon_profile;

    @FXML
    private AnchorPane admin_left_menu_items_container;

    @FXML
    private AnchorPane admin_left_menu_items_container3;

    @FXML
    private AnchorPane admin_left_menu_items_container31;

    @FXML
    private AnchorPane admin_left_menu_items_container32;

    @FXML
    private AnchorPane admin_main_container;

    @FXML
    private ImageView image;

    @FXML
    private Label sesssion_name_shower;

    @FXML
    private Label user_id_shower_label;

    @FXML
    private Label username_shower_label;


    @FXML
    void dashboardBtn(ActionEvent event) throws IOException {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("/GUI/Patient_dashboard.fxml"));
    	admin_content_container_stack_pan.getChildren().removeAll();
    	admin_content_container_stack_pan.getChildren().setAll(root);
    	
    }
    
    
    @FXML
    void profile_action_btn(ActionEvent event) throws IOException {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("/GUI/Patient_profile.fxml"));
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
    void initialize() throws IOException {
    	Image img = new Image(getClass().getResourceAsStream("/images/man-256x256.png"));
		Image apmt_icn = new Image(getClass().getResourceAsStream("/images/Properties_256x256.png"));
		Image lout_icn = new Image(getClass().getResourceAsStream("/images/Log Out_24x24.png"));
		Image profile = new Image(getClass().getResourceAsStream("/images/man-48x48.png"));
		
		
		
		admin_left_head_img.setImage(img);
		admin_left_menu_icon_dashboard.setImage(apmt_icn);
		admin_left_menu_icon_logout.setImage(lout_icn);
		admin_left_menu_icon_profile.setImage(profile);
		
    	
    	// Call the dashboardBtn function
        ActionEvent event = new ActionEvent();
        dashboardBtn(event);
        
        
        //show some data
        Patient_DB_controll DB = new Patient_DB_controll();
        String gmail = login_controll.getGmail();
        
        //not effective way
        //sesssion_name_shower.setText(DB.getNameByGmail(gmail));
        //username_shower_label.setText(gmail);
        //user_id_shower_label.setText(DB.getIdByGmail(gmail));
        
        //show some data .... effective way
        ArrayList<Patient> list = DB.getDataByGmail(gmail);
        if(!list.isEmpty()) {
        	sesssion_name_shower.setText(list.get(0).getName());
            username_shower_label.setText(gmail);
            user_id_shower_label.setText(list.get(0).getId());
        }
         
    }
    
   

}
