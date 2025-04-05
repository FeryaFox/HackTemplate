package ru.feryafox.hacktemplate.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.feryafox.hacktemplate.enums.Priority;
import ru.feryafox.hacktemplate.enums.Status;

import java.util.*;

@Entity
@Table(name = "task")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title = "";

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date createdAt = new Date();

    @ManyToOne()
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private User updatedBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date updatedAt = new Date();

    @ManyToOne()
    @JoinColumn(name = "assigned_to", referencedColumnName = "id")
    private User assignedTo;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date dueDate = new Date();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private boolean isDeleted = false;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date deletedAt = null;

    @ManyToOne()
    @JoinColumn(name = "deleted_by", referencedColumnName = "id")
    private User deletedBy = null;

    // Связи с TaskHistory
    @OneToMany(mappedBy = "task")
    private Set<TaskHistory> taskHistories = new LinkedHashSet<>();



}
