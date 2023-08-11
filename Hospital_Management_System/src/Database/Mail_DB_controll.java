package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Model.mail;

public class Mail_DB_controll {

	private File DB = new File("mail_DB.txt");
	private ArrayList<mail> list = new ArrayList<>();
	@SuppressWarnings("unused")
	private ArrayList<mail> newlist = new ArrayList<>();
	@SuppressWarnings("unused")
	private FileOutputStream fos = null;
	@SuppressWarnings("unused")
	private FileInputStream fis = null;
	@SuppressWarnings("unused")
	private ObjectOutputStream oos = null;
	@SuppressWarnings("unused")
	private ObjectInputStream ois = null;
	
	
	public Mail_DB_controll() {
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
	
	
	public void sendMailToServer(mail mailObject, String serverAddress, int serverPort) {
		
		try (Socket clientSocket = new Socket(serverAddress, serverPort);
		    ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
			oos.writeObject(mailObject);
			oos.flush();
			
			addMailToList(mailObject);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	public void addMailToList(mail mailObject) throws IOException {
		loadData();
	    list.add(mailObject); // Add mail object to your list
	    saveDataToFile();
	    
	    showAllData();
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadData() throws IOException {
		try {
	        fis = new FileInputStream(DB);
	        ois = new ObjectInputStream(fis);
	        list = (ArrayList<mail>) ois.readObject();
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

	
	public void saveDataToFile() {
        try (FileOutputStream fos = new FileOutputStream(DB);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public void showAllData() {
	    if (list.isEmpty()) {
	        System.out.println("No data available.");
	    } else {
	        System.out.println("Saved Doctor Data:");

	        for (mail mail : list) {
	            System.out.println(mail.toString());
	        }
	    }
	}
	
	
	public ArrayList<mail> getData() throws IOException{
		loadData();
		return list;
	}
	
	
	
}
