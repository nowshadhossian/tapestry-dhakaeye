package com.kids.crm.pages.prescription;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.upload.services.UploadedFile;

import com.kids.crm.dao.DatabaseDao;
import com.kids.crm.dao.MedicineDao;
import com.kids.crm.db.Medicine;
import com.kids.crm.db.Patient;
import com.kids.crm.db.Prescription;
import com.kids.crm.db.Visit;
import com.kids.crm.pages.medicine.ListMedicines;
import com.kids.crm.pages.patient.ExistingPatient;
import com.kids.crm.reports.pdf.PDFGenerator;
import com.kids.crm.reports.pdf.PDFStreamResponse;
import com.kids.crm.reports.pdf.PrescriptionPdf;

public class CreatePrescription {

	@Inject
	private DatabaseDao databaseDao;

	@Persist
	@Property
	private Patient patient;
	
	@Property
	private Prescription prescription;
	
	@InjectPage
	private ExistingPatient existingPatient;

	@Property
	private UploadedFile file;
	
	@Persist
	@Property
	private long idFromUrl;

	Object onSuccess() {
		Visit visit = new Visit();
		visit.setPatient(patient);
		databaseDao.saveOrUpdate(visit);
		String fileName = file.getFileName();
		File copied = new File("target/images/patient prescription/");
		if (copied.canWrite() == false) {
			copied.mkdirs();
			copied = new File("target/images/patient prescription/" + patient.getId()+"-" +visit.getId()+"-"+ fileName);
		} else {
			copied = new File("target/images/patient prescription/" + patient.getId()+"-" +visit.getId()+"-"+ fileName);
		}
		file.write(copied);
		visit.setFileName(patient.getId()+"-" +visit.getId()+"-"+ fileName);
		databaseDao.saveOrUpdate(visit);
		return existingPatient;
	}

	void onActivate(long id) {
		patient = (Patient) databaseDao.load(id, Patient.class);
	}
	public void set(long idFromUrl) {
		//used to send patientid from create patient page through url
		this.idFromUrl = idFromUrl;
	}
	long onPassivate() {
		//used to send patientid from create patient page through url
		// onPassivate() is called by Tapestry to get the activation context to put in the URL.
		return idFromUrl;
	}

}
