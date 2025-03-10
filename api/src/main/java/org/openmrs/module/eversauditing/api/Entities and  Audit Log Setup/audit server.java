package org.openmrs.module.eversauditing.api
        import...
@Service
public class AuditService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logChange(String user, String entity, String field, String oldValue, String newValue) {
        AuditLog log = new AuditLog();
        log.setUser(user);
        log.setTimestamp(LocalDateTime.now());
        log.setEntity(entity);
        log.setField(field);
        log.setOldValue(oldValue);
        log.setNewValue(newValue);
        auditLogRepository.save(log);
    }
}