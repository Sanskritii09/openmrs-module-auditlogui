package org.openmrs.module.eversauditing.api;

import org.openmrs.module.eversauditing.AuditEntity;

import java.util.List;

public interface AuditService {
	
	<T> List<AuditEntity<T>> getAllRevisions(Class<T> entityClass);
	
	<T> List<AuditEntity<T>> getAllRevisions(String entityClass);
	
}
