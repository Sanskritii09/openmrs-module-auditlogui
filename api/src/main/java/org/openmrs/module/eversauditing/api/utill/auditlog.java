package org.openmrs.module.eversauditing.api.utill;

public class auditlog
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
        import java.time.LocalDateTime;
import java.util.List;

// Entity Class
@Entity
class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String action;
    private String modifiedData;
    private LocalDateTime timestamp;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getModifiedData() { return modifiedData; }
    public void setModifiedData(String modifiedData) { this.modifiedData = modifiedData; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}

// Repository Interface
interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}

// Service Class
@Service
class AuditLogService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }
}

// Controller Class
@RestController
@RequestMapping("/api/audit-logs")
class AuditLogController {
    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAuditLogs() {
        List<AuditLog> auditLogs = auditLogService.getAllAuditLogs();
        return ResponseEntity.ok(auditLogs);
    }
}

// Main Application Class
@SpringBootApplication
public class AuditLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuditLogApplication.class, args);
    }
}{
}
