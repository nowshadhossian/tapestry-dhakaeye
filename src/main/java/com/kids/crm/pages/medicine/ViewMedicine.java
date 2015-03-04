package com.kids.crm.pages.medicine;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.kids.crm.dao.DatabaseDao;
import com.kids.crm.db.Medicine;


public class ViewMedicine {
	@Property
	private Medicine medicine;

	
	@Inject
	private DatabaseDao databaseDao;

	
	void onActivate(long id) {
		medicine = (Medicine) databaseDao.load(id, Medicine.class);
	}
}
