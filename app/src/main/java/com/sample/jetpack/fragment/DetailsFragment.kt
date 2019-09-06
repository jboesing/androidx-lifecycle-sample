package com.sample.jetpack.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sample.jetpack.R
import com.sample.jetpack.repository.database.entity.Post
import com.sample.jetpack.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private lateinit var mViewModel: PostsViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mViewModel = ViewModelProviders.of(activity!!).get(PostsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getSelectedPostLive().observe(viewLifecycleOwner, Observer { post ->
            displayPost(post)
        })
    }
    
    private fun displayPost(post: Post?) {
        if (post == null) {
            emptyView.visibility = View.VISIBLE
            contentView.visibility = View.GONE
        } else {
            emptyView.visibility = View.GONE
            contentView.visibility = View.VISIBLE
            contentView.text = post.body
        }
    }

}