package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Database.Patient_DB_controll;
import Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class Admin_patient_controll implements Initializable{

    @FXML
    private TableColumn<Patient, Void> d_action;

    @FXML
    private TableColumn<Patient, ?> address;

    @FXML
    private TableColumn<Patient, ?> gender;

    @FXML
    private TableColumn<Patient, ?> name;

    @FXML
    private TableColumn<Patient, ?> patient_id;

    @FXML
    private TableView<Patient> patient_view_table;

    @FXML
    private TableColumn<Patient, ?> phn_no;

    @FXML
    private TableColumn<Patient, ?> status;
    
    
    private void tableView() {
    	// show table view
	    Patient_DB_controll obj = new Patient_DB_controll();
	    ArrayList<Patient> list;
	    
		list = obj.getData();
		
		// Set up the table columns
		patient_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		phn_no.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));


		// Convert the doctorList to an observable list
		ObservableList<Patient> Data = FXCollections.observableArrayList(list);

		// Set the table data
		patient_view_table.setItems(Data);
		
		
		// Set up the action column
		d_action.setCellFactory(param -> new TableCell<Patient, Void>() {
		    private final Button Active = new Button("Active");
		    private final Button deleteButton = new Button("Delete");
		   // Patient patient = getTableView().getItems().get(getIndex());
		    
		    {
		    	Active.setOnAction(event -> {
		            Patient patient = getTableView().getItems().get(getIndex());
		            String Id = patient.getId(); // Retrieve the patient ID
		            
		            boolean res = obj.updatePatientStatus(Id);
		            
		            if(res) {
		            	Doctor_patient_controll.showUpdateAlert("Update Data", "You successfully activated a patient account. ID : "+Id);
		            }else {
		            	Doctor_patient_controll.showUpdateAlert("Failes!", "Failed to update patient data. ID : "+Id);     	
		            }
		            
		            tableView();
		        });

		        deleteButton.setOnAction(event -> {
		            Patient patient = getTableView().getItems().get(getIndex());
		            String Id = patient.getId(); // Retrieve the patient ID
		            
		            boolean res = obj.removePatient(Id);
		            
		            if(res) {
		            	Doctor_patient_controll.showUpdateAlert("Remove Data", "You successfully remove a patient account. ID : "+Id);
		            }else {
		            	Doctor_patient_controll.showUpdateAlert("Failes!", "Failed to remove patient data. ID : "+Id);     	
		            }
		            tableView();
		        });
		    }

		    @Override
		    protected void updateItem(Void item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty) {
		            setGraphic(null);
		        } else {
		            HBox buttonsContainer = new HBox(10);
		            buttonsContainer.getChildren().addAll(Active, deleteButton);
		            setGraphic(buttonsContainer);
		        }
		    }
		});
    	
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	 tableView();
	    
	}

}
