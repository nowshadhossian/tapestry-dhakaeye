package com.kids.crm.pages.medicine;

import java.util.ArrayList;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.kids.crm.dao.DatabaseDao;
import com.kids.crm.dao.MedicineDao;
import com.kids.crm.db.Medicine;

public class ListMedicines {
	@Property
	private Medicine medicine;
	@Property
	@Inject
	private MedicineDao medicineDao;

	public ArrayList<Medicine> getMedicines() {
		return (ArrayList) medicineDao.getMedicine();
		
	}
}
