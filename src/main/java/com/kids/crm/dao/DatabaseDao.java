package com.kids.crm.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

public interface DatabaseDao {
	public void saveOrUpdate(Object object);
	public Object load(Long id, Class clz);
	
}
