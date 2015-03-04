package com.kids.crm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kids.crm.dao.PatientDao;
import com.kids.crm.db.Patient;

@Service("patientDao")
@Transactional
public class PatientDaoImpl extends HibernateDaoSupport implements PatientDao{

	public ArrayList searchPatient(Patient patient) {
		String patientId = "";
		String patientName = "";
		String patientVoterId = "";
	
		List<Object> list = new ArrayList();
		if (patient.getId()!= null) {
			patientId = " id=? and ";
			list.add(patient.getId());
		}
		if (patient.getName()!= null) {
			patientName = " name=? and ";
			list.add(patient.getName());
		}
		if (patient.getVoterId()!= null) {
			patientVoterId = " voterId=? and ";
			list.add(patient.getVoterId());
		}
		Object obj[] = list.toArray();
		String sql = "from Patient where";
		sql += patientId + patientName + patientVoterId;
		sql=sql.substring(0,sql.length()-4);
		System.out.println("\n\n\n\n sql: " + sql+ "\n\n\n\n");
		return (ArrayList) getHibernateTemplate().find(sql, obj);
	}
	public ArrayList getPatientPartialFName(String fName) {
		return (ArrayList) getHibernateTemplate().find("select name from Patient patient where upper(name) like ?", (fName+ "%"));
	}
	public ArrayList getPatientPartialVoterId(String voterId) {
		return (ArrayList) getHibernateTemplate().find("select voterId from Patient patient where upper(voterId) like ?", (voterId+ "%"));
	}

}
