package com.example.demo.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.example.demo.R
import com.example.demo.adapter.MovieAdapter
import com.example.demo.databinding.ActivityMoviesBinding
import com.example.demo.di.Demo
import com.example.demo.model.movielist.Result
import com.example.demo.services.isOnline
import com.example.demo.viewmodel.MovieViewModel
import com.paginate.Paginate
import com.paginate.recycler.LoadingListItemCreator
import org.koin.android.ext.android.inject
import java.lang.String
import java.util.*


class MoviesActivity : AppCompatActivity(), Paginate.Callbacks {
    private lateinit var moviesBinding: ActivityMoviesBinding
    lateinit var context: Context
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MovieAdapter
    private val movieViewModel: MovieViewModel by inject()
    private var movie: List<Result> = ArrayList<Result>()
    private var loading = false
    private var page = 1
    private var handler: Handler? = null
    private var paginate: Paginate? = null
    private var totalPages = 10
    private var networkDelay: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesBinding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(moviesBinding.root)
        supportActionBar!!.hide()
        context = this@MoviesActivity

        layoutManager = LinearLayoutManager(this)
        handler = Handler()
        adapter = MovieAdapter(movie)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isOnline(context)) {
                loadMovie(page)
            }
        }
    }

    private val fakeCallback = Runnable {
        page++
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            loadMovie(page)
        }
        loading = false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadMovie(page: Int) {
        if (paginate != null) {
            paginate!!.unbind()
        }
        handler!!.removeCallbacks(fakeCallback)

        movieViewModel.getMovie(page)!!.observe(this, { movieList ->

            if (movieList != null) {
                movie = movieList.results

                moviesBinding.movieRecycler.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                moviesBinding.movieRecycler.adapter = adapter
                adapter.setMovieList(movieList.results)

                paginate = Paginate.with(moviesBinding.movieRecycler, this)
                    .setLoadingTriggerThreshold(1)
                    .addLoadingListItem(true)
                    .setLoadingListItemCreator(CustomLoadingListItemCreator())
                    .build()

            }

        })


    }

    override fun onLoadMore() {
        loading = true
        handler!!.postDelayed(fakeCallback, networkDelay)
    }

    override fun isLoading(): Boolean {
        return loading
    }

    override fun hasLoadedAllItems(): Boolean {

        return page == totalPages
    }

    private class CustomLoadingListItemCreator : LoadingListItemCreator {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view: View =
                inflater.inflate(R.layout.item_custom_loading_list, parent, false)
            return VH(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val vh = holder as VH
            vh.tvLoading.text =
                String.format("Loading more...")
        }
    }

    internal class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvLoading: TextView = itemView.findViewById<View>(R.id.tv_loading_text) as TextView

    }

}