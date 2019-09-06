package com.sample.jetpack.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "posts"
)
class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val body: String
)