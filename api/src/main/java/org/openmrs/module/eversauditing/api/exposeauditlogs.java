package org.openmrs.module.eversauditing.api;

import java.time.LocalDateTime;

public class exposeauditlogs {
    import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/audit")
    public class AuditLogController {

        private final AuditLogService auditLogService;

        public AuditLogController(AuditLogService auditLogService) {
            this.auditLogService = auditLogService;
        }

        @GetMapping
        public List<AuditLog> getAuditLogs(
                @RequestParam(required = false) String user,
                @RequestParam(required = false) LocalDateTime startDate,
                @RequestParam(required = false) LocalDateTime endDate) {
            return auditLogService.filterLogs(user, startDate, endDate);
        }
    }
}
