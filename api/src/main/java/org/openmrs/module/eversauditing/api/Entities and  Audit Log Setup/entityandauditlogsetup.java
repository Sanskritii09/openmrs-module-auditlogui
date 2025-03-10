package org.openmrs.module.eversauditing.api;
import ...
@Entity
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;
    private LocalDateTime timestamp;
    private String entity;
    private String field;
    private String oldValue;
    private String newValue;

    // Getters and Setters...
}