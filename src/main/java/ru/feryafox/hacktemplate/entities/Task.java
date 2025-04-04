package ru.feryafox.hacktemplate.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "task")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title = "";

    @Column(name = "description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "text")
    private String text;

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
    private priorityType priority;

    private enum priorityType {
        LOW, MEDIUM, HIGH
    }

    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date dueDate = new Date();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private statusType status;

    private enum statusType {
        ACTIVE, POSTPONED, COMPLETED
    }

    @Column(name = "is_deleted")
    @Builder.Default
    private boolean isDeleted = false;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date deletedAt = null;

    @ManyToOne()
    @JoinColumn(name = "deleted_by", referencedColumnName = "id")
    private User deletedBy = null;
}
