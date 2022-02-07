package com.example.mobishalaassignment.ui.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mobishalaassignment.databinding.ItemPlayingMovieBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mobishalaassignment.R
import com.example.mobishalaassignment.architecture.constants.Constants
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.MovieResult
import com.example.mobishalaassignment.architecture.util.exSetImageRoundRect
import com.example.mobishalaassignment.ui.moviedetails.ActivityMovieDetails

/**
 * Created by Sanjay Kumar on 07/02/2022.
 */
class NowPlayingAdapter(private val result: ArrayList<MovieResult>, private val context: Context) : RecyclerView.Adapter<NowPlayingAdapter.MovieViewHolder>() {
    private var mOnItemClickListener: ItemClickListener? = null
    interface ItemClickListener {

        fun onMovieDetailClicked(position: Int , movieResponse: MovieResult)

    }
    inner class MovieViewHolder(val binding: ItemPlayingMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(position: Int, movieResponse: MovieResult) {
            //binding.textTitle.text = movieResponse.title
            binding.rating.text = ""+movieResponse.vote_average+" *"
            binding.releaseYear.text = movieResponse.release_date
            binding.language.text = movieResponse.original_language
            binding.posterImage.exSetImageRoundRect(context,"",Constants.IMAGE_BASE_URL+movieResponse.poster_path)
            /*binding.posterImage.setOnClickListener {
                System.out.println("Sanjay")
                ActivityMovieDetails.start(context)
            }*/
            addClickListener(position , movieResponse)
            }
        private fun addClickListener(position: Int , movieResponse: MovieResult) {
            binding.posterImage.setOnClickListener {
                mOnItemClickListener?.onMovieDetailClicked(position,movieResponse)
            }

        }


        }
    fun setOnItemClickListener(listener: ItemClickListener) {
        mOnItemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingAdapter.MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_playing_movie, parent, false))
    }


    override fun onBindViewHolder(holder: NowPlayingAdapter.MovieViewHolder, position: Int) {
        holder.bindItems(position, result[position])
    }
    override fun getItemCount(): Int {
       return result.size
    }


}








