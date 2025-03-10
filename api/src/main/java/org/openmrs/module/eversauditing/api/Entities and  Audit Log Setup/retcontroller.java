@RestController
@RequestMapping("/api/audit-logs")
package org.openmrs.module.eversauditing.api;
import ...
public class AuditLogController {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAuditLogs(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) String entity,
            @RequestParam(required = false) String field) {

        List<AuditLog> logs;

        // Apply filters based on parameters
        if (user != null) {
            logs = auditLogRepository.findByUser(user);
        } else if (startDate != null && endDate != null) {
            logs = auditLogRepository.findByTimestampBetween(startDate, endDate);
        } else if (entity != null && field != null) {
            logs = auditLogRepository.findByEntityAndField(entity, field);
        } else {
            logs = auditLogRepository.findAll();
        }

        return ResponseEntity.ok(logs);
    }
}