package com.sample.jetpack.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.jetpack.R
import com.sample.jetpack.adapter.PostsAdapter
import com.sample.jetpack.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.fragment_master.*

class MasterFragment : Fragment() {

    private lateinit var mViewModel: PostsViewModel
    private val mAdapter = PostsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_master, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mViewModel = ViewModelProviders.of(activity!!).get(PostsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        mViewModel.getPostsLive().observe(viewLifecycleOwner, Observer { posts ->
            mAdapter.setPosts(posts)
        })
    }

    private fun setupList() {
        mAdapter.onPostSelected = { post ->
            mViewModel.setSelectedPost(post)
        }
        postsListView?.layoutManager = LinearLayoutManager(context)
        postsListView?.adapter = mAdapter
    }
}