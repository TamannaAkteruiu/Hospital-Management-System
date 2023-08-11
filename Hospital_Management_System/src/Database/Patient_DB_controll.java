package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Patient;

public class Patient_DB_controll {
	private File DB = new File("Patient_DB.txt");
	private ArrayList<Patient> list = new ArrayList<>();
	private ArrayList<Patient> newlist = new ArrayList<>();
	@SuppressWarnings("unused")
	private FileOutputStream fos = null;
	private FileInputStream fis = null;
	@SuppressWarnings("unused")
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	
	public Patient_DB_controll() {
		if(DB.exists()) {
			System.out.println("File already exists");
		}else {
			try {
				DB.createNewFile();
			} catch (IOException e) {
				System.out.println("Failed to Create new file!");
			}
		}
	}
	
	
	public ArrayList<Patient> searchDataByPhn(String phn_num) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();        

        for(int i=0; i<list.size(); i++) {
        	String db_phn = list.get(i).getPhoneNumber();
        	
        	if(phn_num.compareTo(db_phn) == 0) {  	
        		newlist.add(list.get(i));		
        		return newlist;
        	}
        	
        }
		
	    return null;
	}
	
	
	public ArrayList<Patient> searchPatientData(String gmail) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();

        for(int i=0; i<list.size(); i++) {
        	String db_gmail = list.get(i).getEmail();
        	
        	if(gmail.compareTo(db_gmail) == 0) {  	
        		newlist.add(list.get(i));		
        		return newlist;
        	}
        	
        }
		
	    return null;
	}
	
	
	public ArrayList<Patient> getPatientDataByCreator(String gmail) throws IOException, ClassNotFoundException {
		ArrayList<Patient> newlist = new ArrayList<>();
		loadDataFromFile();
        

        for(int i=0; i<list.size(); i++) {
        	String db_gmail = list.get(i).getCreator_email();
        	
        	if(gmail.compareTo(db_gmail) == 0) {  	
        		newlist.add(list.get(i));		
        	}
        	
        }
		
	    return newlist;
	}
	
	
	public ArrayList<Patient> getAactivePatientDataByCreator(String gmail) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();

        for(int i=0; i<list.size(); i++) {
        	String db_gmail = list.get(i).getCreator_email();
        	String status = list.get(i).getStatus();
        	
        	if(gmail.compareTo(db_gmail) == 0 && status.compareTo("active")==0) {  	
        		newlist.add(list.get(i));		
        	}
        	
        }
		
	    return newlist;
	}
	
	
	public ArrayList<Patient> getData(){
		loadDataFromFile();
		showAllData();
		return list;
	}
	

	public String checkPatientAuth(String email,String password) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();
		
        for(int i=0; i<list.size(); i++) {
        	String gmail = list.get(i).getEmail();
        	String pass = list.get(i).getPassword();
        	System.out.println();
        	
        	if(gmail.compareTo(email)==0) {
        		if(pass.compareTo(password)==0) {
            		return "match";	
            	}else {
            		return "pass_not_match";
            	}
        	}
        }
		return "email_not_found";
	}
	

	public void updateProfileData(String gmail, String name, String address, String gander, String phn_number) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();
        

        for(int i=0; i<list.size(); i++) {
        	String db_gmail = list.get(i).getEmail();
        	

        	if(db_gmail.compareTo(gmail)==0) {
        		
        		loadDataFromFile();
        		list.get(i).setName(name);
        		list.get(i).setAddress(address);
        		list.get(i).setGender(gander);
        		list.get(i).setPhoneNumber(phn_number);
        		
        		saveDataToFile();
        		
        		showAllData();
        		break;
        	}
        }
		
	}
	
	
	public String inser_patient_reg_data(String email, String username, String password, String con_password) throws IOException {
		
		String id ="";
		String name="";
		String gender="";
		String phoneNumber="";
		String address="";
		String status="deactive";
		
		if(password.compareTo(con_password)!=0) {
			return "pass_not_match";
		}
		
		loadDataFromFile();
		list.add(new Patient(id, name, email, address, phoneNumber, gender, status, password,"owner"));
		
		saveDataToFile();
		showAllData();
		return "success";
	}
	
	
	public String inser_patient__data_By_Doctor(String email, String name, String gender, String phoneNumber, String address,String cre_gmail) throws IOException {
		
		String id ="";
		String status="deactive";
		String password ="12345678";
		
		loadDataFromFile();
		list.add(new Patient(id, name, email, address, phoneNumber, gender, status, password,cre_gmail));
		
		saveDataToFile();
		showAllData();
		return "success";
	}
	
	
	@SuppressWarnings("unchecked")
	private void loadDataFromFile() {
		try {
	        fis = new FileInputStream(DB);
	        ois = new ObjectInputStream(fis);
	        list = (ArrayList<Patient>) ois.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        System.out.println("Failed to load data from file!");
	    } finally {
	        try {
	            if (fis != null)
	                fis.close();
	            if (ois != null)
	                ois.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
	}
	   

	public void showAllData() {
	    if (list.isEmpty()) {
	        System.out.println("No data available.");
	    } else {
	        System.out.println("Saved Doctor Data:");

	        for (Patient patient : list) {
	            System.out.println(patient.toString());
	        }
	    }
	}

	
	public void showAllData(ArrayList<Patient> list) {
	    if (list.isEmpty()) {
	        System.out.println("No data available.");
	    } else {
	        System.out.println("Saved Doctor Data:");

	        for (Patient patient : list) {
	            System.out.println(patient.toString());
	        }
	    }
	}
	
	
	private void saveDataToFile() throws IOException {
		FileOutputStream fos = new FileOutputStream(DB);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
	}


	public String getNumberByGmail(String gmail) {
		
		loadDataFromFile();        

        for(int i=0; i<list.size(); i++) {
        	String db_gmail = list.get(i).getEmail();
        	
        	if(gmail.compareTo(db_gmail) == 0) {  	
        				
        		return list.get(i).getPhoneNumber();
        	}
        	
        }
		
	    return null;
	}


	public boolean updatePatientStatus(String id) {
		loadDataFromFile();
		
		for(int i=0; i<list.size(); i++) {
        	String db_id = list.get(i).getId();
        	
        	if(id.compareTo(db_id) == 0) {  	
        			
        		list.get(i).setStatus("active");
        		
        		try {
					saveDataToFile();
					showAllData();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
        	}
        	
        }
		return false;
	}


	public boolean removePatient(String id) {
		
		loadDataFromFile();
		
		for(int i=0; i<list.size(); i++) {
        	String db_id = list.get(i).getId();
        	
        	if(id.compareTo(db_id) == 0) {  	
        			
        		list.remove(i);
        		
        		try {
					saveDataToFile();
					showAllData();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
        		
        	}	
		}
		
		return false;
	}


	public String getIdByGmail(String gmail) {
		loadDataFromFile();        

        for(int i=0; i<list.size(); i++) {
        	String db_gmail = list.get(i).getEmail();
        	
        	if(gmail.compareTo(db_gmail) == 0) {  	
        				
        		return list.get(i).getId();
        	}
        	
        }
		
	    return null;	
	}
	
	
	public String getNameByGmail(String gmail) {
		loadDataFromFile();        

        for(int i=0; i<list.size(); i++) {
        	String db_gmail = list.get(i).getEmail();
        	
        	if(gmail.compareTo(db_gmail) == 0) {  	
        				
        		return list.get(i).getName();
        	}
        	
        }
		
	    return null;	
	}



	
	public ArrayList<Patient> getDataByGmail(String gmail) {
		ArrayList<Patient> newlist = new ArrayList<>();
		loadDataFromFile();
		
		for(int i=0; i<list.size(); i++) {
	        String db_gmail = list.get(i).getEmail();
	        	
	        if(gmail.compareTo(db_gmail) == 0) {  	
	        	newlist.add(list.get(i));
	        	return newlist;
	        }
	        	
	    }
		
		return newlist;
	}
	
	
	
	
}
