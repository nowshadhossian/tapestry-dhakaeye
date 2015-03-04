package com.kids.crm.pages.patient;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.kids.crm.dao.PatientDao;
import com.kids.crm.db.Patient;

public class ExistingPatient {
	@Property
	private Patient patient;
	@Inject
	private PatientDao patientDao;
	
	@Persist
	@Property
	private ArrayList patientsResult;
	
	@Persist
	@Property
	private String patientNameTf;
	
	@Persist
	@Property
	private String patientVoterIdTf;
	
	@Property
	private ArrayList<String> matchesPatientName;
	
	@Property
	private ArrayList<String> matchesPatientVoterId;
	
	@Inject
	private ComponentResources componentResources;


	void onActivate() {
		componentResources.discardPersistentFieldChanges();
	}
	List<String> onProvideCompletionsFrompatientNameTf(String partial) {
		partial = partial.toUpperCase();
		matchesPatientName = patientDao.getPatientPartialFName(partial);
		return matchesPatientName;
	}
	
	List<String> onProvideCompletionsFrompatientVoterIdTf(String partial) {
		partial = partial.toUpperCase();
		matchesPatientVoterId = patientDao.getPatientPartialVoterId(partial);
		return matchesPatientVoterId;
	}
	
	void onSuccess() {
		if(!((patientNameTf == null) || (patientNameTf.equals("")))){
			patient.setName(patientNameTf);
		}else if(!((patientVoterIdTf == null)||(patientVoterIdTf.equals("")))){
			patient.setVoterId(patientVoterIdTf);
		}
		patientsResult = patientDao.searchPatient(patient);
		
	}
	public ArrayList<Patient> getPatientSearch() {
		return patientsResult;
	}

}
