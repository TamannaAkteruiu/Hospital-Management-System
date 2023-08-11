package Model;

import java.io.Serializable;
import java.util.Random;

public class Doctor_CM implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String gander;
	private String mob_number;
	private String email;
	private String address;
	private String Specialization;
	private String password;
	private String status;
	
	
	
	
	public Doctor_CM(String id, String name, String gander, String mob_number, String email, String address,String specialization, String status, String password) {
		super();
		this.id = generateRandomId();
		this.name = name;
		this.gander = gander;
		this.mob_number = mob_number;
		this.email = email;
		this.address = address;
		Specialization = specialization;
		this.status = status;
		this.password = password;
	}
	
	public Doctor_CM() {
		super();
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
	
	

	@Override
	public String toString() {
		return "Doctor_CM [id=" + id + ", name=" + name + ", gander=" + gander + ", mob_number=" + mob_number
				+ ", email=" + email + ", address=" + address + ", Specialization=" + Specialization + ", password="
				+ password + ", status=" + status + "]";
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gander;
	}

	public void setGander(String gander) {
		this.gander = gander;
	}

	public String getContactNumber() {
		return mob_number;
	}

	public void setMob_number(String mob_number) {
		this.mob_number = mob_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}