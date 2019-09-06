package com.sample.jetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sample.jetpack.repository.PostsRepository
import com.sample.jetpack.repository.database.entity.Post

class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val mSelectedPostLive = MutableLiveData<Post?>()
    private val mSearchLive = MutableLiveData<String?>().apply { value = "" }

    private val mRepository = PostsRepository(application)

    init {
        mRepository.startSyncing()
    }

    fun setSearch(search: String?) {
        mSearchLive.value = search
        mSelectedPostLive.value = null
    }

    fun setSelectedPost(post: Post?) {
        mSelectedPostLive.value = post
    }

    fun getSelectedPostLive(): LiveData<Post?> {
        return mSelectedPostLive
    }

    fun getPostsLive(): LiveData<List<Post>> {
        return Transformations.switchMap(mSearchLive) {
            mRepository.getBySearch(it ?: "")
        }
    }
}