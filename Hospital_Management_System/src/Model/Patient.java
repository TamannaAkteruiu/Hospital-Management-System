package Model;

import java.io.Serializable;

public class Patient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String gender;
    private String status;
    private String password;
    private String creator_email;
    private Doctor_CM dm =  new Doctor_CM();
    
    
	public Patient(String id, String name, String email, String address, String phoneNumber, String gender,
			String status, String password, String creator_email) {
		super();
		this.id = dm.generateRandomId();
		this.name = name;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.status = status;
		this.password = password;
		this.creator_email = creator_email;
	}
	
	
	


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + ", status=" + status + ", password=" + password
				+ ", creator_email=" + creator_email + "]";
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


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCreator_email() {
		return creator_email;
	}


	public void setCreator_email(String creator_email) {
		this.creator_email = creator_email;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}