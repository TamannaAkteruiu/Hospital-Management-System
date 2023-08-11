package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.Appoinment;

public class Appoinment_DB_controll {

	private File DB = new File("Appoinment_DB.txt");
	private ArrayList<Appoinment> list = new ArrayList<>();
	private ArrayList<Appoinment> newlist = new ArrayList<>();
	@SuppressWarnings("unused")
	private FileOutputStream fos = null;
	private FileInputStream fis = null;
	@SuppressWarnings("unused")
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	
	
	public Appoinment_DB_controll() {
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
	
	
	public ArrayList<Appoinment> getData(String gmail) throws ClassNotFoundException, IOException{
		
		Doctor_DB_controll doctor_db = new Doctor_DB_controll();
		loadDataFromFile();
		
		String doctor_id  = doctor_db.getDoctorId(gmail);
		
		for(int i=0; i<list.size(); i++) {
			String appoint_doctor_id = list.get(i).getDoctor_id();
			String status = list.get(i).getStatus();
			
			if(doctor_id.compareTo(appoint_doctor_id)==0 && status.compareTo("active")==0) {
				newlist.add(list.get(i));
			}
			
		}
		
		/*
		for(Appoinment data : list) {
			String appoint_doctor_id  = data.getDoctor_id();
			newlist.add(list);
		}*/
		
		showAllData(newlist);
		return newlist;
	}
	
	
	
	public ArrayList<Appoinment> getDataBasePatient(String gmail) throws ClassNotFoundException, IOException{
		ArrayList<Appoinment> newlist = new ArrayList<>();
		
		Patient_DB_controll patient_db = new Patient_DB_controll();
		String patient_num = patient_db.getNumberByGmail(gmail);
		
		loadDataFromFile();
		
		for(int i=0; i<list.size(); i++) {
			String db_pat_num = list.get(i).getMobile_number();
			
			if(patient_num.compareTo(db_pat_num)==0) {
				newlist.add(list.get(i));
			}
		}
		
		
		showAllData(newlist);
		return newlist;
	}
	
	
	
	
	public boolean insertData(String id, String name, String diagnosis, String treatment, String gender, String mobile_number, String appoinment_date, String doctor_id) {
		
		loadDataFromFile();
		list.add(new Appoinment(id, name, diagnosis, treatment, gender, mobile_number, appoinment_date, doctor_id,"active"));
		try {
			saveDataToFile();
			showAllData();
			return true;
		} catch (IOException e) {	
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	@SuppressWarnings({ "unchecked"})
	public void loadDataFromFile() {
		try {
	        fis = new FileInputStream(DB);
	        ois = new ObjectInputStream(fis);
	        list = (ArrayList<Appoinment>) ois.readObject();
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
	   

	public ArrayList<Appoinment> getData(){
		loadDataFromFile();
		return list;
	}
	
	
	public void showAllData() {
	    if (list.isEmpty()) {
	        System.out.println("No data available.");
	    } else {
	        System.out.println("Saved Doctor Data:");

	        for (Appoinment appoinment : list) {
	            System.out.println(appoinment.toString());
	        }
	    }
	}

	
	public void showAllData(ArrayList<Appoinment> list) {
	    if (list.isEmpty()) {
	        System.out.println("No data available.");
	    } else {
	        System.out.println("Saved Doctor Data:");

	        for (Appoinment data : list) {
	            System.out.println(data.toString());
	        }
	    }
	}
	
	
	private void saveDataToFile() throws IOException {
		FileOutputStream fos = new FileOutputStream(DB);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
	}


	public boolean removeAppoinments(String doctorId) {
	    loadDataFromFile();

	    ArrayList<Appoinment> tempList = new ArrayList<>(list); // Create a temporary list to avoid modification during iteration

	    for (Appoinment appointment : tempList) {
	        if (appointment.getDoctor_id().equals(doctorId)) {
	            list.remove(appointment); // Remove appointments with the specified doctorId
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


	public boolean cancelAppointmet(String id) {
		loadDataFromFile();
		
		for (Appoinment appointment : list) {
	        if (appointment.getId().equals(id)) {
	        	appointment.setStatus("cancel");
	            break;
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


	public boolean removeAppoinment(String id) {

		loadDataFromFile();
		
		for (Appoinment appointment : list) {
	        if (appointment.getId().equals(id)) {
	            list.remove(appointment);
	            break;
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
