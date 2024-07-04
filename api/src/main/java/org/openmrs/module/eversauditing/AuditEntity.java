package org.openmrs.module.eversauditing;

import org.hibernate.envers.RevisionType;
import org.openmrs.api.db.hibernate.envers.OpenmrsRevisionEntity;

public class AuditEntity<T> {
	
	private T entity;
	
	private OpenmrsRevisionEntity revisionEntity;
	
	private RevisionType revisionType;
	
	public AuditEntity(T entity, OpenmrsRevisionEntity revisionEntity, RevisionType revisionType) {
		this.entity = entity;
		this.revisionEntity = revisionEntity;
		this.revisionType = revisionType;
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
	
	public RevisionType getRevisionType() {
		return revisionType;
	}
	
	public void setRevisionType(RevisionType revisionType) {
		this.revisionType = revisionType;
	}
}
