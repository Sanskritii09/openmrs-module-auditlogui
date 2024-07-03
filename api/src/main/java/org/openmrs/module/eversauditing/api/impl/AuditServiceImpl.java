package org.openmrs.module.eversauditing.api.impl;

import org.openmrs.module.eversauditing.AuditEntity;
import org.openmrs.module.eversauditing.api.AuditService;
import org.openmrs.module.eversauditing.api.dao.AuditDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
	
	private final AuditDao auditingDao;
	
	public AuditServiceImpl(AuditDao auditingDao) {
		this.auditingDao = auditingDao;
	}
	
	public <T> List<AuditEntity<T>> getAllRevisions(Class<T> entityClass) {
		return auditingDao.getAllRevisions(entityClass);
	}
	
}
