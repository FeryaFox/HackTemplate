package ru.feryafox.hacktemplate.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title = "";

    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt = new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_user_id")
    private User createdUser;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "updated_user_id")
    private User updatedUser;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

}
