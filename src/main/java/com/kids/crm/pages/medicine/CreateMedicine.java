package com.kids.crm.pages.medicine;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.kids.crm.dao.DatabaseDao;
import com.kids.crm.db.Medicine;

public class CreateMedicine {
	@Property
	private Medicine medicine;
	
	@InjectPage
	private ListMedicines listMedicines;
	 
	@Inject
	private DatabaseDao databaseDao;

	Object onSuccess() {
		databaseDao.saveOrUpdate(medicine);
		return listMedicines;
	}
}
