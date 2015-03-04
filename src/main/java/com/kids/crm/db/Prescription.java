package com.kids.crm.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prescription  {
	private Long id;
	private Visit visit;
	private Medicine medicine;
	private String dosage;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="prescription")
	@javax.persistence.SequenceGenerator(
	name="prescription",
	sequenceName="prescription_seq"
	)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "visit_id")
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
}
