package ru.feryafox.hacktemplate.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskHistory {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "changed_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date changedAt = new Date();

    @Column(name = "old_status")
    private statusType oldStatus;

    @Column(name = "new_status")
    private statusType newStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne()
    @JoinColumn(name = "changed_by", referencedColumnName = "id")
    private User changedBy;

    private enum EventType {
        CREATE, UPDATE, DELETE, STATUS_CHANGE
    }

    private enum statusType {
        ACTIVE, POSTPONED, COMPLETED
    }
}
