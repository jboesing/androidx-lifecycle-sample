package com.sample.jetpack.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.jetpack.repository.database.entity.Post

@Dao
abstract class PostDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM posts where title like '%' || :search || '%'")
    abstract fun getBySearch(search: String): LiveData<List<Post>>

}