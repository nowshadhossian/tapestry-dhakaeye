package com.kids.crm.dao.impl;

import java.util.ArrayList;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kids.crm.dao.VisitDao;
import com.kids.crm.db.Patient;
import com.kids.crm.db.Visit;


@Service("visitDao")
@Transactional
public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao{

	public ArrayList getVisitByPatient(Patient patient) {
		return (ArrayList) getHibernateTemplate().find("from Visit visit where visit.patient.id=? order by created desc", patient.getId());
	}

	public ArrayList getPrescriptionByVisit(Visit visit) {
		return (ArrayList) getHibernateTemplate().find("from Prescription prescription where prescription.visit.id=?", visit.getId());
	}
	

}
