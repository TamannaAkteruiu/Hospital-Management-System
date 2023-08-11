module Hospital_Management_System {
	exports application;
	exports controller;
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens Model to javafx.base; // Add this line to open the Model package to javafx.base
	opens controller; // Add this line to open the controller package to the javafx.fxml module
	
	opens application to javafx.graphics, javafx.fxml;
}
