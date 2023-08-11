package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Model.Doctor_CM;

public class Doctor_DB_controll {
	private File doctorDB = new File("Doctor_DB.txt");
	private ArrayList<Doctor_CM> doctorlist = new ArrayList<>();
	private ArrayList<Doctor_CM> newlist = new ArrayList<>();
	@SuppressWarnings("unused")
	private FileOutputStream fos = null;
	private FileInputStream fis = null;
	@SuppressWarnings("unused")
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	
	public Doctor_DB_controll() {
		if(doctorDB.exists()) {
			System.out.println("File already exists");
		}else {
			try {
				doctorDB.createNewFile();
			} catch (IOException e) {
				System.out.println("Failed to Create new file!");
			}
		}
	}
	
	
	public String getDoctorName(String id) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();
        
        for(int i=0; i<doctorlist.size(); i++) {
        	String ID = doctorlist.get(i).getId();
        	
        	if(ID.compareTo(id) == 0) {  	
        		return doctorlist.get(i).getName();
        	}
        	
        }
		
		return "null";
	}
	
		
	public String getDoctorId(String gmail) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();
        
        for(int i=0; i<doctorlist.size(); i++) {
        	String db_gmail = doctorlist.get(i).getEmail();
        	
        	if(db_gmail.compareTo(gmail) == 0) {  	
        		return doctorlist.get(i).getId();
        	}
        	
        }
		
		return "null";
	}

	
	public String inser_doctor_reg_data(String email, String username, String password, String con_password) throws IOException {
		
		String id="";
		String name="";
		String gander="";
		String mob_number="";
		String address="";
		String Specialization="";
		String status="deactive";
		
		if(password.compareTo(con_password)!=0) {
			return "pass_not_match";
		}
		
		loadDataFromFile();
		doctorlist.add(new Doctor_CM(id,name,gander,mob_number,email,address,Specialization,status,password));
		
		saveDataToFile();
		showDoctorAllData();
		return "success";
	}
	
	
	public List<Doctor_CM> getDoctorData() throws IOException, ClassNotFoundException {
	    loadDataFromFile();
	    return doctorlist;
	}

	
	public ArrayList<Doctor_CM> searchDoctorData(String gmail) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();
        

        for(int i=0; i<doctorlist.size(); i++) {
        	String db_gmail = doctorlist.get(i).getEmail();
        	
        	if(gmail.compareTo(db_gmail) == 0) {  	
        		newlist.add(doctorlist.get(i));		
        		return newlist;
        	}
        }
		
	    return null;
	}

	
	@SuppressWarnings("unchecked")
	private void loadDataFromFile() {
	    try {
	        fis = new FileInputStream(doctorDB);
	        ois = new ObjectInputStream(fis);
	        doctorlist = (ArrayList<Doctor_CM>) ois.readObject();
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

	
	public void showDoctorAllData() {
	    if (doctorlist.isEmpty()) {
	        System.out.println("No data available.");
	    } else {
	        System.out.println("Saved Doctor Data:");

	        for (Doctor_CM doctor : doctorlist) {
	            System.out.println(doctor.toString());
	        }
	    }
	}

	
	public void showDoctorAllData(ArrayList<Doctor_CM> list) {
	    if (list.isEmpty()) {
	        System.out.println("No data available.");
	    } else {
	        System.out.println("Saved Doctor Data:");

	        for (Doctor_CM doctor : list) {
	            System.out.println(doctor.toString());
	        }
	    }
	}
	

	public String checkDoctorAuth(String email,String password) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();
		
        for(int i=0; i<doctorlist.size(); i++) {
        	String gmail = doctorlist.get(i).getEmail();
        	String pass = doctorlist.get(i).getPassword();
        	System.out.println();
        	
        	if(gmail.compareTo(email)==0 && doctorlist.get(i).getStatus().compareTo("active")==0) {
        		if(pass.compareTo(password)==0) {
            		return "match";	
            	}else {
            		return "pass_not_match";
            	}
        	}
        }
		return "email_not_found";
	}
	

	public void updateDoctorProfileData(String gmail, String name, String address, String gander, String phn_number, String specialize) throws IOException, ClassNotFoundException {
		
		loadDataFromFile();    

        for(int i=0; i<doctorlist.size(); i++) {
        	String db_gmail = doctorlist.get(i).getEmail();
        	
        	if(db_gmail.compareTo(gmail)==0) {
        		
        		loadDataFromFile();
        		doctorlist.get(i).setName(name);
        		doctorlist.get(i).setAddress(address);
        		doctorlist.get(i).setGander(gander);
        		doctorlist.get(i).setMob_number(phn_number);
        		doctorlist.get(i).setSpecialization(specialize);
        		
        		saveDataToFile();
        		
        		showDoctorAllData();
        		break;
        	}
        }
		
	}
	
	
	public void updateStatus(String doctorId) throws IOException {
		
		loadDataFromFile();
		
		 for(int i=0; i<doctorlist.size(); i++) {
	        	String db_id = doctorlist.get(i).getId();
	        	

	        	if(doctorId.compareTo(db_id)==0) {
	        		
	        		loadDataFromFile();
	        		doctorlist.get(i).setStatus("active");

	        		saveDataToFile();
	        		showDoctorAllData();
	        		break;
	        	}
	        }
	}
	
	
	private void saveDataToFile() throws IOException {
		FileOutputStream fos = new FileOutputStream(doctorDB);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(doctorlist);
        oos.close();
	}


	public boolean removeDoctor(String doctorId) {
	    loadDataFromFile();

	    Iterator<Doctor_CM> iterator = doctorlist.iterator();
	    while (iterator.hasNext()) {
	        Doctor_CM doctor = iterator.next();
	        if (doctor.getId().equals(doctorId)) {
	            iterator.remove(); // Safely remove the current element
	        }
	    }

	    try {
	        saveDataToFile();
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
