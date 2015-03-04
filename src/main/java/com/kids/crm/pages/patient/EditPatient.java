package com.kids.crm.pages.patient;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.kids.crm.dao.DatabaseDao;
import com.kids.crm.db.Medicine;
import com.kids.crm.db.Patient;
import com.kids.crm.pages.medicine.ListMedicines;

public class EditPatient {
	@Persist
	@Property
	private Patient patient;
	
	@InjectPage
	//private ListMedicines listMedicine;
	
	@Inject
	private DatabaseDao databaseDao;
	
	void onActivate(long id) {
		patient = (Patient) databaseDao.load(id, Patient.class);
	}
	
	void onSuccess() {
		databaseDao.saveOrUpdate(patient);
		//return listMedicine;
	}
}
