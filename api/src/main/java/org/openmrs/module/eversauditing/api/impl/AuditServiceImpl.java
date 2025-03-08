package org.openmrs.module.eversauditing.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.eversauditing.AuditEntity;
import org.openmrs.module.eversauditing.api.AuditService;
import org.openmrs.module.eversauditing.api.dao.AuditDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditServiceImpl extends BaseOpenmrsService implements AuditService {

	private final AuditDao auditingDao;
	private final Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);

	public AuditServiceImpl(AuditDao auditingDao) {
		this.auditingDao = auditingDao;
	}

	public <T> List<AuditEntity<T>> getAllRevisions(Class<T> entityClass) {
		logger.info("Fetching all revisions for entity class: {}", entityClass.getName());
		List<AuditEntity<T>> revisions = auditingDao.getAllRevisions(entityClass);
		logger.info("Retrieved {} revisions for entity class: {}", revisions.size(), entityClass.getName());
		return revisions;
	}

	@Override
	public <T> List<AuditEntity<T>> getAllRevisions(String entityClass) {
		try {
			Class clazz = Class.forName(entityClass);
			logger.info("Fetching all revisions for entity class: {}", entityClass);
			return getAllRevisions(clazz);
		} catch (ClassNotFoundException e) {
			logger.error("Class not found: {}", entityClass, e);
			return new ArrayList<>();
		}
	}

	public <T> T getRevisionById(Class<T> entityClass, int entityId, int revisionId) {
		logger.info("Fetching revision {} for entity class: {} with ID: {}", revisionId, entityClass.getName(), entityId);
		T entity = auditingDao.getRevisionById(entityClass, entityId, revisionId);
		if (entity != null) {
			logger.info("Successfully retrieved revision {} for entity class: {} with ID: {}", revisionId, entityClass.getName(), entityId);
		} else {
			logger.warn("No entity found for class: {} with ID: {} at revision: {}", entityClass.getName(), entityId, revisionId);
		}
		return entity;
	}
}
