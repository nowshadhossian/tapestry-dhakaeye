package com.kids.crm.reports.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
	public static InputStream generatePDF(String teststring) {
		// step 1: creation of a document-object
		Document document = new Document();

		System.out.println("\n\n\n\n\ntold ya 1\n\n\n\n");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			// step 3: we open the document
			document.open();
			// step 4: we add a paragraph to the document
			document.add(new Paragraph(teststring));

			document.close();
			baos.flush();
			ByteArrayInputStream bais = new ByteArrayInputStream(
					baos.toByteArray());
			return bais;
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		}
		// step 5: we close the document
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
