package com.kids.crm.dao;

import java.util.ArrayList;

import com.kids.crm.db.Patient;

public interface PatientDao {
	public ArrayList searchPatient(Patient patient);
	public ArrayList getPatientPartialFName(String fName);
	public ArrayList getPatientPartialVoterId(String voterId);
	
}
