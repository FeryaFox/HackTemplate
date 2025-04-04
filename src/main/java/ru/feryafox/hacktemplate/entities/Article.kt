package ru.feryafox.hacktemplate.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "articles")
data class Article (
    @Id
    val id: UUID,

    @Column(name = "title")
    var title: String = "",

    @Column(name = "content")
    var content: String?,

    @Column(name = "image")
    var image: String,

//    @Column(name = "created_at")
//    var createAt:


)
