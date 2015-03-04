package com.kids.crm.pages.patient;

import java.io.File;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.upload.services.UploadedFile;

public class UploadVoterCard {
	  @Property
      private UploadedFile file;
	  
	  @Persist
	  private long patientId;
	  
	  public void setPatiendId(long patiendId){
		  this.patientId = patientId;
	  }

      public void onSuccess()
      {
    	  String fileName = file.getFileName();
          File copied = new File("target/images/votercard/");
          if(copied.canWrite()==false){
        	  copied.mkdirs();
        	  copied = new File("target/images/votercard/" + patientId + fileName);
          }else{
        	  copied = new File("target/images/votercard/" + patientId + fileName);
          }
          file.write(copied);
      }

}
