package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Database.Appoinment_DB_controll;
import Database.Doctor_DB_controll;
import Model.Doctor_CM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class Admin_doctor_controll implements Initializable{

	
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
	
	
	
    
    private void tableView() {
    	
    	Appoinment_DB_controll appoinment_db = new Appoinment_DB_controll();
	    Doctor_DB_controll obj = new Doctor_DB_controll();
	    List<Doctor_CM> doctorList;
	    
		try {
			
			
			doctorList = obj.getDoctorData();
			// Set up the table columns
		    d_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		    d_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		    d_gander.setCellValueFactory(new PropertyValueFactory<>("gender"));
		    d_email.setCellValueFactory(new PropertyValueFactory<>("email"));
		    d_address.setCellValueFactory(new PropertyValueFactory<>("address"));
		    s_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		    d_CN.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));
		
		    // Convert the doctorList to an observable list
		    ObservableList<Doctor_CM> doctorData = FXCollections.observableArrayList(doctorList);
		
		    // Set the table data
		    admin_doctor_table.setItems(doctorData);
		
		    // Set up the action column
		    d_action.setCellFactory(param -> new TableCell<Doctor_CM, Void>() {
		    	
		        private final Button editButton = new Button("Active");
		        private final Button deleteButton = new Button("Delete");
		
		        {
		            editButton.setOnAction(event -> {
		                Doctor_CM doctor = getTableView().getItems().get(getIndex());
		                Doctor_DB_controll doctor_db = new Doctor_DB_controll();
		                
		                
		                String doctorId = doctor.getId(); // Retrieve the doctor ID
		                
		                try {
							doctor_db.updateStatus(doctorId);
							Doctor_patient_controll.showUpdateAlert("Update Data", "You successfully activated a doctor account. ID : "+doctorId);
							tableView();
							
						} catch (IOException e) {
							e.printStackTrace();
						}  
		                
		            });
		
		            
		            deleteButton.setOnAction(event -> {
		                Doctor_CM doctor = getTableView().getItems().get(getIndex());
		                String doctorId = doctor.getId(); // Retrieve the doctor ID
		                
		                //now we have to delete doctor data also its id related appointment data
		                ButtonType result  = alert();
		                
		                if(result == ButtonType.OK) {
		                	boolean res1 = appoinment_db.removeAppoinments(doctorId);
		                	Boolean res2  = obj.removeDoctor(doctorId);
		                	
		                	if(res1 && res2) {
		                		Doctor_patient_controll.showUpdateAlert("Remove Data", "You successfully removed a doctor account. ID : "+doctorId);
		                	}else {
		                		Doctor_patient_controll.showUpdateAlert("Failed!", "Doctor removeing operation failed. Doctor ID : "+doctorId);
		                	}
		                	
		                	tableView();
		                	
		                }else {                	
		                	tableView();
		                }

		            });
		        }
		
		        
		        //add this two button inside table column
		        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty) {
		                setGraphic(null);
		            } else {
		                HBox buttonsContainer = new HBox(10);
		                buttonsContainer.getChildren().addAll(editButton, deleteButton);
		                setGraphic(buttonsContainer);
		            }
		        }
		        	
		
		    });
			
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    
    private ButtonType alert() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Delete record's!");
    	alert.setHeaderText("If you delete doctor data it will be delete appoinment's data.");
    	alert.setContentText("Click 'Yes' to delete or 'No' to cancel.");
    	
    	ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
    	return result;
    }
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//table data load
		tableView();
		
	}
	
	
	
}
