package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Database.Appoinment_DB_controll;
import Model.Appoinment;
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

public class Admin_appoinment_controll implements Initializable{

    @FXML
    private TableColumn<Appoinment, Void> action;

    @FXML
    private TableView<Appoinment> appoinment_table;

    @FXML
    private TableColumn<Appoinment, ?> contact_no;

    @FXML
    private TableColumn<Appoinment, ?> date;

    @FXML
    private TableColumn<Appoinment, ?> diagnosis;

    @FXML
    private TableColumn<Appoinment, ?> gender;

    @FXML
    private TableColumn<Appoinment, ?> id;

    @FXML
    private TableColumn<Appoinment, ?> name;

    @FXML
    private TableColumn<Appoinment, ?> treatment;

    
    private void tableView() {

		Appoinment_DB_controll DB = new Appoinment_DB_controll();
		ArrayList<Appoinment> list = DB.getData();
		
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
		treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
		contact_no.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
		date.setCellValueFactory(new PropertyValueFactory<>("appoinment_date"));
		
		ObservableList<Appoinment> data = FXCollections.observableArrayList(list);
		
		appoinment_table.setItems(data);
		
		//
		action.setCellFactory(param -> new TableCell<Appoinment, Void>(){
			private final Button cancel = new Button("Cancel");
			private final Button delete = new Button("Remove");
			
			{
				cancel.setOnAction(event -> {
					
					Appoinment data = getTableView().getItems().get(getIndex());
					String id = data.getId();
					
					boolean res = DB.cancelAppointmet(id);
					if(res) {
						Doctor_patient_controll.showUpdateAlert("Cancel Appoinment", "You successfully canceled a patient appointment. ID : "+id);;
					}else {
						Doctor_patient_controll.showUpdateAlert("Failed!", "Failed to canceled patient appointment. ID : "+id);	
					}
					
					tableView();
				});
				
				delete.setOnAction(event -> {
					
					Appoinment data = getTableView().getItems().get(getIndex());
					String id = data.getId();
					
					boolean res = DB.removeAppoinment(id);
					
					if(res) {
						Doctor_patient_controll.showUpdateAlert("Remove Appoinment", "You successfully removed a patient appointment. ID : "+id);;
					}else {
						Doctor_patient_controll.showUpdateAlert("Failed!", "Failed to remove patient appointment. ID : "+id);	
					}
					
					tableView();
				});
				
			}
			
			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				
				if(empty) {
					setGraphic(null);
				}else {
					HBox buttonContainer = new HBox(10);
					buttonContainer.getChildren().addAll(cancel,delete);
					setGraphic(buttonContainer);
				}	
			}
			
			
		});
		
    	
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 tableView();
		 
	}
    
    

}
