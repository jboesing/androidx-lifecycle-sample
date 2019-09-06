package com.sample.jetpack.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.sample.jetpack.repository.database.AppDatabase
import com.sample.jetpack.repository.database.entity.Post
import com.sample.jetpack.repository.service.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PostsRepository(context: Context) {

    private val mPostDao = AppDatabase.getInstance(context).postDao()
    private val mService = Service.getInstance()

    fun getBySearch(search: String): LiveData<List<Post>> {
        return mPostDao.getBySearch(search)
    }

    fun startSyncing() {
        GlobalScope.launch(Dispatchers.IO) {
            val posts = mService.getPosts()
            mPostDao.insertAll(posts)
        }
    }

}