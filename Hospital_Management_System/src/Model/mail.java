package Model;

import java.io.Serializable;

public class mail implements Serializable{
	
	/**
	 * 
	 */

	
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String sender_gmail;
	private String sender_phn_number;
	private String subject;
	private String msg;
	private String read_status;
	private Doctor_CM dm =  new Doctor_CM();
	
	
	public mail(String id, String name, String sender_gmail, String sender_phn_number, String subject, String msg,
			String read_status) {
		super();
		this.id = dm.generateRandomId();
		this.name = name;
		this.sender_gmail = sender_gmail;
		this.sender_phn_number = sender_phn_number;
		this.subject = subject;
		this.msg = msg;
		this.read_status = read_status;
	}


	@Override
	public String toString() {
		return "mail [id=" + id + ", name=" + name + ", sender_gmail=" + sender_gmail + ", sender_phn_number="
				+ sender_phn_number + ", subject=" + subject + ", msg=" + msg + ", read_status=" + read_status + "]";
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


	public String getSender_gmail() {
		return sender_gmail;
	}


	public void setSender_gmail(String sender_gmail) {
		this.sender_gmail = sender_gmail;
	}


	public String getSender_phn_number() {
		return sender_phn_number;
	}


	public void setSender_phn_number(String sender_phn_number) {
		this.sender_phn_number = sender_phn_number;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getRead_status() {
		return read_status;
	}


	public void setRead_status(String read_status) {
		this.read_status = read_status;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
