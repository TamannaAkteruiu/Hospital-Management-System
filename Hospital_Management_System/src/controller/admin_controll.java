package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Doctor_CM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class admin_controll implements Initializable {

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
    private ImageView admin_left_menu_icon_doctors;

    @FXML
    private ImageView admin_left_menu_icon_logout;

    @FXML
    private ImageView admin_left_menu_icon_patients;

    @FXML
    private AnchorPane admin_left_menu_items_container;

    @FXML
    private AnchorPane admin_left_menu_items_container1;

    @FXML
    private AnchorPane admin_left_menu_items_container2;

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
    
    
    /*doctor panel element's*/
    
    @FXML
    private TableView<Doctor_CM> admin_doctor_table;

    @FXML
    private TableColumn<Doctor_CM, ?> d_CN;

    @FXML
    private TableColumn<Doctor_CM, Void> d_action;

    @FXML
    private TableColumn<Doctor_CM, ?> d_address;

    @FXML
    private TableColumn<Doctor_CM, ?> d_email;

    @FXML
    private TableColumn<Doctor_CM, ?> d_gander;

    @FXML
    private TableColumn<Doctor_CM, ?> d_id;

    @FXML
    private TableColumn<Doctor_CM, ?> d_name;

    @FXML
    private TableColumn<Doctor_CM, ?> s_status;
    
    ActionEvent event;
    

    @FXML
    void apoinment_action_btn(ActionEvent event) throws IOException {
        //show appointment container
        AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Admin_apoinment.fxml"));
        admin_content_container_stack_pan.getChildren().removeAll();
        admin_content_container_stack_pan.getChildren().setAll(root);
        
        
        
    }

    @FXML
    void dashboardBtn(ActionEvent event) throws IOException {
        //show dash-board container
        AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Admin_dashboard.fxml"));
        admin_content_container_stack_pan.getChildren().removeAll();
        admin_content_container_stack_pan.getChildren().setAll(root);
        
        
    }

    @FXML
    void doctor_btn_action(ActionEvent event) throws IOException, ClassNotFoundException {
        // show doctor container
        AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Admin_doctor.fxml"));
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
    void patients_action_btn(ActionEvent event) throws IOException {
        //show patients container
        AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Admin_patients.fxml"));
        admin_content_container_stack_pan.getChildren().removeAll();
        admin_content_container_stack_pan.getChildren().setAll(root);
 

    }
    

    
    @FXML
    private Label mail_num_shower;
    
    @FXML
    void ShowPatientMailBtn(ActionEvent event) throws IOException {

    	//show dash-board container
        AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/GUI/Admin_mail.fxml"));
        admin_content_container_stack_pan.getChildren().removeAll();
        admin_content_container_stack_pan.getChildren().setAll(root);
    	
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Image img = new Image(getClass().getResourceAsStream("/images/man-256x256.png"));
        Image doc_icn = new Image(getClass().getResourceAsStream("/images/Add_48x48.png"));
        Image patients_icn = new Image(getClass().getResourceAsStream("/images/icons8-username-48.png"));
        Image apmt_icn = new Image(getClass().getResourceAsStream("/images/Properties_256x256.png"));
        Image lout_icn = new Image(getClass().getResourceAsStream("/images/Properties_256x256.png"));
        
        
        
        admin_left_menu_icon_admin.setImage(img);
        admin_left_head_img.setImage(img);
        admin_left_menu_icon_doctors.setImage(doc_icn);
        admin_left_menu_icon_patients.setImage(patients_icn);
        admin_left_menu_icon_apointment.setImage(apmt_icn);
        admin_left_menu_icon_logout.setImage(lout_icn);
        
        // Call the dashboardBtn function
        ActionEvent event = new ActionEvent();
        try {
            dashboardBtn(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        //establish security...
        try {
			Sequrity(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //fetch and display some data
        username_shower_label.setText(login_controll.getGmail());
        user_id_shower_label.setText("**********");
        
        
        //mail number shower.
        mail_num_shower.setText("00");
        
    }
    
    @SuppressWarnings("static-access")
	private void Sequrity(ActionEvent event) throws IOException {
    	login_controll lc = new login_controll();
    	
    	//System.out.println("Gmail : "+lc.getGmail());

    	if(lc.getGmail() == null) {
    		//sceneChange(event, "/GUI/Authentication");
    		System.out.println("Not Authorized!");
    	}
    }
    
    @SuppressWarnings("unused")
	private void sceneChange(ActionEvent event, String url) throws IOException {
    	
    	//catch current stage
    	//Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Stage currentStage = (Stage) admin_content_container_stack_pan.getScene().getWindow();
    	
    	//create new stage
    	Parent root = FXMLLoader.load(getClass().getResource(url));
    	Scene scene = new Scene(root);
    	Stage newStage = new Stage();
    	newStage.setScene(scene);
    	
    	//close the current stage
    	currentStage.close();
    	
    	//show the now stage
    	newStage.show();

    }
    
    
    

}
