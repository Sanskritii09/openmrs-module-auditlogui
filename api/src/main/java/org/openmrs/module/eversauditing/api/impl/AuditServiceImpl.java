package org.openmrs.module.eversauditing.api.impl;

import org.openmrs.module.eversauditing.AuditEntity;
import org.openmrs.module.eversauditing.api.AuditService;
import org.openmrs.module.eversauditing.api.dao.AuditDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService {
	
	private final AuditDao auditingDao;
	
	private final Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
	
	public AuditServiceImpl(AuditDao auditingDao) {
		this.auditingDao = auditingDao;
	}
	
	public <T> List<AuditEntity<T>> getAllRevisions(Class<T> entityClass) {
		return auditingDao.getAllRevisions(entityClass);
	}
	
	@Override
	public <T> List<AuditEntity<T>> getAllRevisions(String entityClass) {
		try {
			Class clazz = Class.forName(entityClass);
			return getAllRevisions(clazz);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<>();
		}
	}
}
