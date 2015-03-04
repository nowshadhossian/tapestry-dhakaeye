package com.kids.crm.reports.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kids.crm.dao.VisitDao;
import com.kids.crm.db.Patient;
import com.kids.crm.db.Prescription;
import com.kids.crm.db.Visit;
@Service("prescriptionPdf")
public class PrescriptionPdf {
	
	@Autowired VisitDao visitDao;
	public InputStream pdfGeneration(Patient patient, Visit visit){
		Document document = new Document();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			//String fileName = "prescription/Prescription"+patient.getId() + new Date().getDay()+new Date().getSeconds()+".pdf";
			
			PdfWriter pdfWriter = PdfWriter.getInstance(document, byteArrayOutputStream);
			//pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			
			Paragraph emptyLine = new Paragraph();
			addEmptyLine(emptyLine, 8);
			document.add(emptyLine);
			String patientName = patient.getName();
			String patientAddress = patient.getAddress();
			String patientPhone = patient.getPhone();
			if(patient.getName()==null){
				patientName = ""; 
			}
			if(patient.getAddress() == null){
				patientAddress = "";
			}
			if(patient.getPhone() == null){
				patientPhone = "";
			}
			// Left
			Paragraph paragraph = new Paragraph("Date " + visit.getCreated()+ "\nPatient Id: "+ patient.getId()+"\n Name: "+patientName+"\n Address: "+patientAddress+"\n Phone: "+patientPhone);
			paragraph.setAlignment(Element.ALIGN_LEFT);
			document.add(paragraph);
			// Centered
			
			ArrayList<Prescription> prescriptionList = visitDao.getPrescriptionByVisit(visit);
			createTable(document, prescriptionList);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		return byteArrayInputStream;
	}
	
	
	public void createTable(Document document, ArrayList<Prescription> prescriptionList){
		try {
			
			int numberOfTableHeader = 3;
			PdfPTable pdfTable = new PdfPTable(numberOfTableHeader);
			
			//emptyline
			Paragraph emptyLine = new Paragraph();
			addEmptyLine(emptyLine, 1);
			document.add(emptyLine);

			int tableWidth[] ={30,80,70}; 
			pdfTable.setWidths(tableWidth);//table column width length
			
			PdfPCell header = new PdfPCell();
			pdfTable.setHorizontalAlignment(0);
			header.setBorder(0);
			
			Font font = new  Font(Font.FontFamily.TIMES_ROMAN, 10,
					Font.BOLD);
			Paragraph headerParagraph = new Paragraph();
			headerParagraph.setFont(font);
			
			//Phrase
			header.addElement(new Paragraph("Sl.", font));
			pdfTable.addCell(header);
			
			header = new PdfPCell();
			header.addElement(new Paragraph("Medicine Name", font));
			header.setBorder(0);
			pdfTable.addCell(header);
			
			
			header = new PdfPCell();
			header.addElement(new Paragraph("Dosage", font));
			header.setBorder(0);
			pdfTable.addCell(header);
			
			
			int serialCounter = 1;
			for(Prescription prescription:prescriptionList){
				
				pdfTable.addCell(new Phrase(String.valueOf(serialCounter), FontFactory.getFont(FontFactory.COURIER, 8)));
				
				pdfTable.addCell(new Phrase(String.valueOf(prescription.getMedicine().getName()), FontFactory.getFont(FontFactory.COURIER, 8)));
				
				pdfTable.addCell(new Phrase(String.valueOf(prescription.getDosage()), FontFactory.getFont(FontFactory.COURIER, 8)));
				serialCounter++;
			}
			
			
			
			document.add(pdfTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
