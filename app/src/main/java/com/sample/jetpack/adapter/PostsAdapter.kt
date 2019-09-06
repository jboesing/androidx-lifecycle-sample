package com.sample.jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.jetpack.R
import com.sample.jetpack.repository.database.entity.Post

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val mPosts = mutableListOf<Post>()

    var onPostSelected: ((Post?) -> (Unit))? = null

    fun setPosts(posts: List<Post>) {
        mPosts.clear()
        mPosts.addAll(posts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_posts_row, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mPosts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = mPosts.getOrNull(position)
        holder.title?.text = post?.title
        holder.itemView.setOnClickListener {
            onPostSelected?.invoke(post)
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView? = itemView.findViewById<TextView?>(R.id.titleView)
    }
}