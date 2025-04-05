package ru.feryafox.hacktemplate.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.feryafox.hacktemplate.enums.EventType;
import ru.feryafox.hacktemplate.enums.Status;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "task_history")
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
    private Status oldStatus;

    @Column(name = "new_status")
    private Status newStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @ManyToOne()
    @JoinColumn(name = "changed_by", referencedColumnName = "id")
    private User changedBy;

}
