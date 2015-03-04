package com.kids.crm.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.Validate;
@Entity
public class Medicine extends Persistent{
	private Long id;
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="medicine")
	@javax.persistence.SequenceGenerator(
	name="medicine",
	sequenceName="medicine_seq"
	)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Validate("required")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
