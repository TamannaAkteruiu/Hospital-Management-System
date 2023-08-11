package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Database.Mail_DB_controll;
import Model.mail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class Admin_mail_controll implements Initializable{

    @FXML
    private Label email_viewer;

    @FXML
    private TableColumn<mail, ?> m_gmail;

    @FXML
    private TableColumn<mail, ?> m_id;

    @FXML
    private TableColumn<mail, ?> m_mobile_number;

    @FXML
    private TableColumn<mail, ?> m_msg;

    @FXML
    private TableColumn<mail, ?> m_patient_name;

    @FXML
    private TableColumn<mail, ?> m_subject;

    @FXML
    private TableView<mail> m_table;

    @FXML
    private TextArea msg_viewer;

    @FXML
    private Label name_viewer;

    @FXML
    private Label subject_viewer;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Mail_DB_controll mailDbController = new Mail_DB_controll();

        // Set up the TableColumn bindings
        m_gmail.setCellValueFactory(new PropertyValueFactory<>("sender_gmail"));
        m_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        m_mobile_number.setCellValueFactory(new PropertyValueFactory<>("sender_phn_number"));
        m_msg.setCellValueFactory(new PropertyValueFactory<>("msg"));
        m_patient_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        m_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));

        // Load data into TableView
        try {
            m_table.getItems().addAll(mailDbController.getData());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
    

}
