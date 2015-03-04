package com.kids.crm.dao;

import java.util.ArrayList;

import com.kids.crm.db.Medicine;
import com.kids.crm.db.Patient;

public interface MedicineDao {
	public ArrayList getMedicine();
	public ArrayList getMedicinePartialFName(String fName);
	public Medicine searchMedicine(Medicine medicine);
}
