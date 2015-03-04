package com.kids.crm.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kids.crm.dao.DatabaseDao;
import com.kids.crm.db.Medicine;
import com.kids.crm.db.Patient;
import com.kids.crm.db.Prescription;
import com.kids.crm.db.Visit;
import com.kids.crm.reports.pdf.PrescriptionPdf;


public class Main {
	public static void main(String []args){
		new Main().de();
	}
	public void de(){
		ApplicationContext factory = new ClassPathXmlApplicationContext("test-spring-servlet.xml");
		DatabaseDao databaseDao = (DatabaseDao) factory.getBean("databaseDao");
		
		Patient patient = new Patient();
		patient.setName("ALI");
		databaseDao.saveOrUpdate(patient);
		
		Visit visit = new Visit();
		visit.setPatient(patient);
		databaseDao.saveOrUpdate(visit);
		
		Prescription p = new Prescription();
		p.setDosage("352");
		p.setVisit(visit);
		databaseDao.saveOrUpdate(p);
		
		
		
	}
}
