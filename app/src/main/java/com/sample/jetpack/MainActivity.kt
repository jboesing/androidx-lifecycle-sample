package com.sample.jetpack

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.sample.jetpack.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var mSearchMenuItem: MenuItem? = null
    private var mSearchView: SearchView? = null
    private lateinit var mViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchMenuItem = menu.findItem(R.id.search)
        mSearchView = mSearchMenuItem?.actionView as? SearchView

        mSearchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        mSearchView?.isSubmitButtonEnabled = true
        mSearchView?.maxWidth = Int.MAX_VALUE
        mSearchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        mViewModel.setSearch(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        mViewModel.setSearch(newText)
        return true
    }
}
