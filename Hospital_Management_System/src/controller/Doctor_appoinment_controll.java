package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Database.Appoinment_DB_controll;
import Database.Doctor_DB_controll;
import Database.Patient_DB_controll;
import Model.Appoinment;
import Model.Doctor_CM;
import Model.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class Doctor_appoinment_controll {

    @FXML
    private DatePicker App_Date_TF;

    @FXML
    private TableColumn<Appoinment, ?> ap_id;

    @FXML
    private TextField ap_id_TF;

    @FXML
    private TableView<Appoinment> appoinment_view_table;

    @FXML
    private TableColumn<Appoinment, ?> appointment_date;

    @FXML
    private TextField diag_TF;

    @FXML
    private TableColumn<Appoinment, ?> diagnosis;

    @FXML
    private TableColumn<Appoinment, ?> gender;

    @FXML
    private ChoiceBox<String> gender_TF;

    @FXML
    private TableColumn<Appoinment, ?> mobile_number;

    @FXML
    private TableColumn<Appoinment, ?> name;

    @FXML
    private TextField name_TF;

    @FXML
    private TextField phn_num_TF;

    @FXML
    private TableColumn<Appoinment, ?> treatment;

    @FXML
    private TextField treatment_TF;
    

    
    @FXML
    void InsertDataBTN(ActionEvent event) throws ClassNotFoundException, IOException {
    	String appoin_id = ap_id_TF.getText();
    	String Name = name_TF.getText();
    	String diag = diag_TF.getText();
    	String tret = treatment_TF.getText();
    	String gend = gender_TF.getValue();
    	String phn = phn_num_TF.getText();
    	String appoin_date = App_Date_TF.getValue().toString();
    	String doctor_id = getDoctorId();
    	
    	
    	Appoinment_DB_controll db = new Appoinment_DB_controll();
    	
    	boolean check = db.insertData(appoin_id, Name, diag, tret, gend, phn, appoin_date, doctor_id);
    	
    	if(check) {
    		showUpdateAlert("Inserted Successful","Data has been successfully inserted!");
    		///loadTableData();
    	}else {
    		showUpdateAlert("Insertion failed!","Data has been faild to insert!");
    	}
    	
    	
    	//add extra code
    	ap_id_TF.setText(generateRandomId());
    	loadTableData();
    	clearDataBTN(event);
    }

    
 	private String getDoctorId() throws ClassNotFoundException, IOException {
    	Doctor_DB_controll db = new Doctor_DB_controll();
    	ArrayList<Doctor_CM> list = db.searchDoctorData(login_controll.getGmail());
 
    	for(Doctor_CM doct : list ) {
    		return doct.getId();
    	}
    	return null;
    }

	
	private void showUpdateAlert(String title, String text) {
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(text);

	    alert.showAndWait();
	}
	
	
    @FXML
    void clearDataBTN(ActionEvent event) {
    	ap_id_TF.setText("");
    	name_TF.setText("");
    	diag_TF.setText("");
    	treatment_TF.setText("");
    	gender_TF.setValue("");
    	phn_num_TF.setText("");
    	App_Date_TF.setValue(null);
    }

    
    @FXML
    void deleteDataBTN(ActionEvent event) throws ClassNotFoundException, IOException {

    	loadTableData();
    }

    
    @FXML
    void mobile_Num_TF_Action(ActionEvent event) {
    	Patient_DB_controll db = new Patient_DB_controll();
    	
    	try {
			ArrayList<Patient> list = db.searchDataByPhn(phn_num_TF.getText());
			
			for(Patient patient : list) {
		    	name_TF.setText(patient.getName());
		    	gender_TF.setValue(patient.getGender());
		    	phn_num_TF.setText(patient.getPhoneNumber());
			}
			
			showUpdateAlert("Found","Successfully Found record!");
		} catch (ClassNotFoundException | IOException e) {
			showUpdateAlert("Failed!","No record found!");
			e.printStackTrace();
		}
    	
    	
    }

    
    @FXML
    void updateDataBTN(ActionEvent event) throws ClassNotFoundException, IOException {

    	loadTableData();
    }
    
 
	private void loadTableData() throws ClassNotFoundException, IOException {
    	
    	Appoinment_DB_controll db = new Appoinment_DB_controll();
    	ArrayList<Appoinment> list = db.getData(login_controll.getGmail());
    	
    	// Set up the table columns
    	ap_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
    	treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    	mobile_number.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
    	appointment_date.setCellValueFactory(new PropertyValueFactory<>("appoinment_date"));
    	
    	// Convert the doctorList to an observable list
	    ObservableList<Appoinment> Data = FXCollections.observableArrayList(list);
	
	    // Set the table data
	    appoinment_view_table.setItems(Data);
	    
	}
	
    
    @FXML
    void initialize() throws ClassNotFoundException, IOException {

    	List<String> gander = new ArrayList<>();
    	gander.add("Male");
    	gander.add("Female");
    	gander.add("Custom");
    	gender_TF.getItems().addAll(gander);

    	//load table data
    	loadTableData();
    	
    	//auto generate appointment id
    	ap_id_TF.setText(generateRandomId());
    	
    }
   
    
    protected String generateRandomId() {
        Random random = new Random();
        int idLength = random.nextInt(3) + 8; // Random length between 8 and 10
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idLength; i++) {
            int digit = random.nextInt(10); // Random digit between 0 and 9
            sb.append(digit);
        }
        return sb.toString();
    }
    
    
    public int getTotalAppoinment() throws ClassNotFoundException, IOException{
    	Appoinment_DB_controll db = new Appoinment_DB_controll();
    	db.loadDataFromFile();
    	ArrayList<Appoinment> list = db.getData(login_controll.getGmail());
    	return list.size();
    }


}
