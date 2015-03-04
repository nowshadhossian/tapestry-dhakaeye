package com.kids.crm.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Guess {
	 @Persist
	 private int target;
	void setup(int target)
	  {
	    this.target = target;
	  }
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	
	
	
	
	

}
