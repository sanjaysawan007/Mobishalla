package com.example.mobishalaassignment.ui.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobishalaassignment.R
import com.example.mobishalaassignment.architecture.constants.Constants
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.MovieResult
import com.example.mobishalaassignment.architecture.util.exSetImageRoundRect
import com.example.mobishalaassignment.databinding.ActivityMovieDetailsBinding
import com.example.mobishalaassignment.databinding.ItemPopularMoviesBinding
import com.example.mobishalaassignment.ui.moviedetails.ActivityMovieDetails

/**
 * Created by Sanjay Kumar on 07/02/2022.
 */
class PopularMoviesAdapter(private val result: ArrayList<MovieResult>, private val context: Context) : RecyclerView.Adapter<PopularMoviesAdapter.MovieViewHolder>() {
    private var mOnItemClickListener: ItemClickListener? = null
    interface ItemClickListener {

        fun onPopularMovieClicked(position: Int , movieResponse: MovieResult)

    }
    inner class MovieViewHolder(val binding: ItemPopularMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(position: Int, movieResponse: MovieResult) {
            binding.popularMovieName.text = movieResponse.title
            binding.popularMovieRating.text = ""+movieResponse.vote_average+" *"
            binding.popularMovieReleaseYear.text = movieResponse.release_date
            binding.popularMovieLanguage.text = movieResponse.original_language
            binding.popularMoviePosterImage.exSetImageRoundRect(context,"", Constants.IMAGE_BASE_URL+movieResponse.poster_path)
            addClickListener(position , movieResponse)
        /* binding.cardView.setOnClickListener {
                //ActivityMovieDetails.start(context)
            }*/
        }
        private fun addClickListener(position: Int , movieResponse: MovieResult) {
            binding.popularMoviePosterImage.setOnClickListener {
                mOnItemClickListener?.onPopularMovieClicked(position,movieResponse)
            }

        }

    }
    fun setOnItemClickListener(listener: PopularMoviesAdapter.ItemClickListener) {
        mOnItemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesAdapter.MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_popular_movies, parent, false))
    }


    override fun onBindViewHolder(holder: PopularMoviesAdapter.MovieViewHolder, position: Int) {
        holder.bindItems(position, result[position])
    }
    override fun getItemCount(): Int {
        return result.size
    }


}