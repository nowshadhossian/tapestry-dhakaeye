package com.kids.crm.dao;

import java.util.ArrayList;

import com.kids.crm.db.Patient;
import com.kids.crm.db.Visit;

public interface VisitDao {
	public ArrayList getVisitByPatient(Patient patient);
	public ArrayList getPrescriptionByVisit(Visit visit);
}
