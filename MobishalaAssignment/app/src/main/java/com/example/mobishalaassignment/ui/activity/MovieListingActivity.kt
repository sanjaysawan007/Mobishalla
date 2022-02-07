package com.example.mobishalaassignment.ui.activity

import com.example.mobishalaassignment.R
import com.example.mobishalaassignment.architecture.application.ArchitectureApp
import com.example.mobishalaassignment.architecture.constants.ConstantsApi
import com.example.mobishalaassignment.architecture.customAppComponents.activity.BaseAppCompatActivity
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.MovieResponse
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.MovieResult
import com.example.mobishalaassignment.architecture.util.delegates.ActivityBindingProviderDelegate
import com.example.mobishalaassignment.databinding.ActivityMainBinding
import com.example.mobishalaassignment.presenter.presenter.Presenter
import com.example.mobishalaassignment.presenter.presenter.ViewGeneric
import com.example.mobishalaassignment.ui.adapter.recycler.NowPlayingAdapter
import com.example.mobishalaassignment.ui.adapter.recycler.PopularMoviesAdapter
import com.example.mobishalaassignment.utility.GridItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobishalaassignment.ui.moviedetails.ActivityMovieDetails

/**
 * Created by Sanjay Kumar on 07/02/2022.
 */
class MovieListingActivity : BaseAppCompatActivity(),NowPlayingAdapter.ItemClickListener,PopularMoviesAdapter.ItemClickListener {
    private val moviePresenter = Presenter()
    private val binding: ActivityMainBinding by ActivityBindingProviderDelegate(
        this, R.layout.activity_main)
    override fun init() {
        ArchitectureApp.instance.component.inject(this)
        moviePresenter.callNetwork(ConstantsApi.CALL_NOW_PLAYING_MOVIE,CallPlayingMovie())
        moviePresenter.callNetwork(ConstantsApi.CALL_POPULAR_MOVIE,CallPopularMovie())

    }
    inner class CallPopularMovie : ViewGeneric<String?, MovieResponse>(context = this) {
        override val apiRequest: String?
            get() = null

        override fun getApiSuccess(value: MovieResponse) {
            if (value.results.isNotEmpty()) {
                System.out.println("Response>>>>" + value.results)
                val popularMoviesAdapter = PopularMoviesAdapter(value.results as ArrayList<MovieResult>,applicationContext)
                val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
                binding.rvPopularMovie.layoutManager = mLayoutManager
                binding.rvPopularMovie.addItemDecoration(GridItemDecoration(10,2))
                binding.rvPopularMovie.adapter = popularMoviesAdapter
                popularMoviesAdapter.setOnItemClickListener(this@MovieListingActivity)
            } else {
                //showToast("Not called")
                System.out.println("Response>>>>")
            }
        }

    }
    inner class CallPlayingMovie : ViewGeneric<String?, MovieResponse>(context = this) {
        override val apiRequest: String?
            get() = null

        override fun getApiSuccess(value: MovieResponse) {
            if (value.results.isNotEmpty()) {
                System.out.println("Response>>>>" + value.results)
                val nowPlayingAdapter = NowPlayingAdapter(value.results as ArrayList<MovieResult>,applicationContext)
                binding.rvPlayingMovie.adapter = nowPlayingAdapter
                nowPlayingAdapter.setOnItemClickListener(this@MovieListingActivity)
            } else {
                //showToast("Not called")
                System.out.println("Response>>>>")
            }
        }

    }

    override fun onMovieDetailClicked(position: Int, movieResponse: MovieResult) {
        ActivityMovieDetails.start(this,movieResponse)
    }

    override fun onPopularMovieClicked(position: Int, movieResponse: MovieResult) {
        ActivityMovieDetails.start(this,movieResponse)
    }
}