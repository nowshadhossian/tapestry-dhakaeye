package com.kids.crm.pages.patient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;

import com.kids.crm.dao.DatabaseDao;
import com.kids.crm.dao.VisitDao;
import com.kids.crm.db.Medicine;
import com.kids.crm.db.Patient;
import com.kids.crm.db.Visit;
import com.kids.crm.reports.pdf.PDFStreamResponse;
import com.kids.crm.reports.pdf.PrescriptionPdf;

public class ViewPatient {
	@Persist
	@Property
	private Patient patient;

	@Inject
	private DatabaseDao databaseDao;
	
	@Inject
	private VisitDao visitDao;
	
	@Inject 
	private PrescriptionPdf prescriptionPdf;
	
	@Property
	private Visit visit;

	
	void onActivate(long id) {
		patient = (Patient) databaseDao.load(id, Patient.class);
	}
	
	public ArrayList<Medicine> getVisits() {
		return (ArrayList) visitDao.getVisitByPatient(patient);
	}
	
	StreamResponse onActionFromPrescriptionViewLink(long id) {
		visit =  (Visit) databaseDao.load(id, Visit.class);
		return new StreamResponse() {
			InputStream inputStream;

			public void prepareResponse(Response response) {
				File file = new File("target/images/patient prescription/" + visit.getFileName());
				try {
					inputStream = new FileInputStream(file);
				
					response.setHeader("Content-Length", "" + inputStream.available());
				}
				catch (IOException e) {
				}
			}

			public String getContentType() {
				return "image/bmp";
			}

			public InputStream getStream() throws IOException {
				return inputStream;
			}
		};
	}
	
	StreamResponse onActionFromViewVoterIdLink() { 
		return new StreamResponse() {
			InputStream inputStream;

			public void prepareResponse(Response response) {
				File file = new File("target/images/votercard/" + patient.getVoterFileName());
				try {
					inputStream = new FileInputStream(file);
				
					response.setHeader("Content-Length", "" + inputStream.available());
				}
				catch (IOException e) {
				}
			}

			public String getContentType() {
				return "image/bmp";
			}

			public InputStream getStream() throws IOException {
				return inputStream;
			}
		};
	}
}
