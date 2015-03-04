package com.kids.crm.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.services.Response;

import com.kids.crm.reports.pdf.PDFGenerator;
import com.kids.crm.reports.pdf.PDFStreamResponse;



public class Contact
{
	public StreamResponse onSubmit() {
		/*System.out.println("\n\n\n\n\nContact\n\n\n\n");
        // Create PDF
        InputStream is = PDFGenerator.generatePDF("This is the content of a Dynamically Generated PDF");
        // Return response
        return new PDFStreamResponse(is,"MyDynamicSample");*/
		/*
		try {
			System.out.println("\n\n\n\n\nContact\n\n\n\n");
		    File tmpFile = File.createTempFile("hava", null);
		    BufferedWriter br = new BufferedWriter(new FileWriter(tmpFile));
		    br.append("something to test\nAnother line to test");
		    br.flush();
		    br.close();

		    return new PDFStreamResponse(new FileInputStream(tmpFile.getAbsolutePath()), "results_file");
		 } catch (IOException e) {
		     e.printStackTrace();
		 }

		   return null; 
		*/
		System.out.println("\n\n\n\n\nContact\n\n\n\n");
		
		return new StreamResponse() {
			InputStream inputStream;

			public void prepareResponse(Response response) {
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				inputStream = classLoader.getResourceAsStream("ind.pdf");
				try {
				

				// Set content length to prevent chunking - see
				// http://tapestry-users.832.n2.nabble.com/Disable-Transfer-Encoding-chunked-from-StreamResponse-td5269662.html#a5269662

				
					response.setHeader("Content-Length", "" + inputStream.available());
				}
				catch (IOException e) {
					// Ignore the exception in this simple example.
				}
			}

			public String getContentType() {
				return "application/pdf";
			}

			public InputStream getStream() throws IOException {
				return inputStream;
			}
		};

		
		
    }
}
