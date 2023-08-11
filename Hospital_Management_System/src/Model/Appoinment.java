package Model;

import java.io.Serializable;

public class Appoinment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String diagnosis;
	private String treatment;
	private String gender;
	private String mobile_number;
	private String appoinment_date;
	private String doctor_id;
	private String status;
	
	
	public Appoinment(String id, String name, String diagnosis, String treatment, String gender, String mobile_number,
			String appoinment_date, String doctor_id, String status) {
		super();
		this.id = id;
		this.name = name;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.gender = gender;
		this.mobile_number = mobile_number;
		this.appoinment_date = appoinment_date;
		this.doctor_id = doctor_id;
		this.status = status;
	}


	@Override
	public String toString() {
		return "Appoinment [id=" + id + ", name=" + name + ", diagnosis=" + diagnosis + ", treatment=" + treatment
				+ ", gender=" + gender + ", mobile_number=" + mobile_number + ", appoinment_date=" + appoinment_date
				+ ", doctor_id=" + doctor_id + ", status=" + status + "]";
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


	public String getDiagnosis() {
		return diagnosis;
	}


	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}


	public String getTreatment() {
		return treatment;
	}


	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getMobile_number() {
		return mobile_number;
	}


	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}


	public String getAppoinment_date() {
		return appoinment_date;
	}


	public void setAppoinment_date(String appoinment_date) {
		this.appoinment_date = appoinment_date;
	}


	public String getDoctor_id() {
		return doctor_id;
	}


	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
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