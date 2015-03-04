package com.kids.crm.dao.impl;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kids.crm.dao.DatabaseDao;


@Service("databaseDao")
@Transactional
public class DatabaseDaoImpl extends HibernateDaoSupport implements DatabaseDao{
	public void saveOrUpdate(Object object){
		getSession(false).saveOrUpdate(object);
	}
	
	public Object load(Long id, Class clz) {
		return getSession(false).get(clz, id);
	}
	
}
