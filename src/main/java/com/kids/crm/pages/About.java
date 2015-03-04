package com.kids.crm.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.kids.crm.dao.DatabaseDao;


public class About {

	@Property
	private String fNameTf;
	
	@Property
	private ArrayList<String> matches;

	
	List<String> onProvideCompletionsFromfNameTf(String partial) {
		partial = partial.toUpperCase();
		
		return matches;
	}

}
