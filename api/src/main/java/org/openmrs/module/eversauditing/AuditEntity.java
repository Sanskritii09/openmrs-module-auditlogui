package org.openmrs.module.eversauditing;

import org.openmrs.api.db.hibernate.envers.OpenmrsRevisionEntity;

public class AuditEntity<T> {
	
	private T entity;
	
	private OpenmrsRevisionEntity revisionEntity;
	
	public AuditEntity(T entity, OpenmrsRevisionEntity revisionEntity) {
		this.entity = entity;
		this.revisionEntity = revisionEntity;
	}
	
	public T getEntity() {
		return entity;
	}
	
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public OpenmrsRevisionEntity getRevisionEntity() {
		return revisionEntity;
	}
	
	public void setRevisionEntity(OpenmrsRevisionEntity revisionEntity) {
		this.revisionEntity = revisionEntity;
	}
}
