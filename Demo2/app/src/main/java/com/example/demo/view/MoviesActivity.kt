package com.example.demo.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.adapter.MovieAdapter
import com.example.demo.adapter.PaginatedAdapter
import com.example.demo.databinding.ActivityMoviesBinding
import com.example.demo.services.isOnline
import com.example.demo.viewmodel.MovieViewModel
import java.util.*


class MoviesActivity : AppCompatActivity() {
    private lateinit var moviesBinding: ActivityMoviesBinding
    lateinit var context: Context
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel
    private var movie: List<com.example.demo.model.movielist.Result> =
        ArrayList<com.example.demo.model.movielist.Result>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesBinding = ActivityMoviesBinding.inflate(layoutInflater)

        setContentView(moviesBinding.root)
        supportActionBar!!.hide()
        context = this@MoviesActivity
        layoutManager = LinearLayoutManager(this)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)


        if (isOnline(context)) {
            adapter = MovieAdapter(movie)
            adapter.setDefaultRecyclerView(this, R.id.movieRecycler)
            adapter.setOnPaginationListener(object : PaginatedAdapter.OnPaginationListener {
                override fun onCurrentPage(page: Int) {
                    moviesBinding.progressMovies.visibility = View.GONE
                }

                override fun onNextPage(page: Int) {
                    getMovies(page)
                }

                override fun onFinish() {
                    Toast.makeText(this@MoviesActivity, "finish", Toast.LENGTH_SHORT).show()
                }
            })

        } else {

            Toast.makeText(this, "Check your Network Connection", Toast.LENGTH_SHORT).show()
        }
        getMovies(adapter.startPage)
    }

    private fun getMovies(page: Int) {
        moviesBinding.progressMovies.visibility = View.VISIBLE
        Handler().postDelayed({
            movieViewModel.getMovie(page)!!.observe(this, { movieList ->

                if (movieList != null) {
                    movie = movieList.results
                    moviesBinding.movieRecycler.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    moviesBinding.movieRecycler.adapter = adapter
                    adapter.setMovieList(movieList.results)
                    onGetDate(movie)
                }

            })


        }, 3000)

    }

    private fun onGetDate(result: List<com.example.demo.model.movielist.Result?>?) {
        adapter.submitItems(result)
    }

}

