package com.kids.crm.dao.impl;

import java.util.ArrayList;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kids.crm.dao.MedicineDao;
import com.kids.crm.db.Medicine;

@Service("medicineDao")
@Transactional
public class MedicineDaoImpl extends HibernateDaoSupport implements MedicineDao{

	public ArrayList getMedicine() {
		return (ArrayList) getHibernateTemplate().find("from Medicine");
	}

	public ArrayList getMedicinePartialFName(String fName) {
		return (ArrayList) getHibernateTemplate().find("select name from Medicine medicine where name like ?", (fName+ "%"));
	}

	public Medicine searchMedicine(Medicine medicine) {
		return (Medicine) getHibernateTemplate().find("from Medicine where name like ?", (medicine.getName())).get(0);
	}

}
