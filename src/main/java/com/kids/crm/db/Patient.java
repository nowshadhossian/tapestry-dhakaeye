package com.kids.crm.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.Validate;
@Entity
public class Patient extends Persistent{
	private Long id;
	private String name;
	private String address;
	private String phone;
	private String voterFileName;
	private String voterId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="patient")
	@javax.persistence.SequenceGenerator(
	name="patient",
	sequenceName="patient_seq"
	)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Validate("required")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVoterFileName() {
		return voterFileName;
	}
	public void setVoterFileName(String voterFileName) {
		this.voterFileName = voterFileName;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	
	
}
