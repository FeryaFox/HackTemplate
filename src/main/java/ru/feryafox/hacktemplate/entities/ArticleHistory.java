package ru.feryafox.hacktemplate.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "articles_history")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleHistory {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "changed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedAt = new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "changed_user_id")
    private User changedUser;

    enum EventType {
        CREATE,
        UPDATE,
        DELETE,
        RESTORE
    }
}
