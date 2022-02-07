package com.example.mobishalaassignment.ui.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mobishalaassignment.R
import com.example.mobishalaassignment.architecture.constants.Constants
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.CastResult
import com.example.mobishalaassignment.architecture.util.exSetRoundRectImage
import com.example.mobishalaassignment.databinding.ItemCastBinding


/**
 * Created by Sanjay Kumar on 07/02/2022.
 */
class CastAdapter(private val result: ArrayList<CastResult>, private val context: Context) : RecyclerView.Adapter<CastAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(position: Int, castResult: CastResult) {
            binding.castName.text = castResult.castName


            if(castResult.profileImage.isNullOrBlank())
            {
                Glide.with(context).load(context.getDrawable(R.drawable.ic_launcher_background)).apply(
                    RequestOptions().circleCrop()).into(binding.castImage)
            }else {
                binding.castImage.exSetRoundRectImage(
                    context,
                    "",
                    Constants.IMAGE_BASE_URL + castResult.profileImage
                )
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_cast, parent, false))
    }


    override fun onBindViewHolder(holder: CastAdapter.MovieViewHolder, position: Int) {
        holder.bindItems(position, result[position])
    }
    override fun getItemCount(): Int {
        return result.size
    }


}