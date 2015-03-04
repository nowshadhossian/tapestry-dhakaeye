package com.kids.crm.pages.patient;

import java.io.File;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.upload.services.UploadedFile;

import com.kids.crm.dao.DatabaseDao;
import com.kids.crm.db.Medicine;
import com.kids.crm.db.Patient;
import com.kids.crm.pages.medicine.ListMedicines;
import com.kids.crm.pages.prescription.CreatePrescription;

public class CreatePatient {
	@Property
	private Patient patient;

	@InjectPage
	private CreatePrescription createPrescription;
	 
	@Inject
	private DatabaseDao databaseDao;
	
	@Property
    private UploadedFile file;
	
	
	
	Object onSuccess() {
		databaseDao.saveOrUpdate(patient);
		
		if(file !=null){
			
			String fileName = file.getFileName();
			File copied = new File("target/images/votercard/");
			if(copied.canWrite()==false){
				copied.mkdirs();
				copied = new File("target/images/votercard/" + patient.getId() + fileName);
			}else{
				copied = new File("target/images/votercard/" + patient.getId() + fileName);
			}
			file.write(copied);
			patient.setVoterFileName(patient.getId()+fileName);
			databaseDao.saveOrUpdate(patient);
		}
		createPrescription.set(patient.getId());
		return createPrescription;
	}
}
