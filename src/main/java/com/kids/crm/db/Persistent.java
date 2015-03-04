package com.kids.crm.db;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;

@MappedSuperclass
public class Persistent implements PreInsertEventListener, PreUpdateEventListener {

	private Date created;
	private Date updated;
		

    public boolean onPreInsert(PreInsertEvent event) {
    	Object obj = event.getEntity();
    	if(obj instanceof Persistent){
	    	// Update the state so DB gets it
	    	Object[] values = event.getState();
			values[0] = new Date(); //values[1] = created
			Persistent persistent = (Persistent) obj;
			persistent.setCreated((Date)values[0]);
    	}
 	   return false;
    }

    public boolean onPreUpdate(PreUpdateEvent event) {
    	Object obj = event.getEntity();   
    	if(obj instanceof Persistent){
	    	// Update the state so DB gets it
	    	Object[] values = event.getState();
	        values[1] = new Date(); //values[3] = updated
	    	Persistent persistent = (Persistent) obj;
	    	persistent.setUpdated((Date)values[1]);
    	}
 	   return false;
    }
        
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", updatable = false)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}