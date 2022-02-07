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
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.MovieDetailResponse
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.ProductionComapany
import com.example.mobishalaassignment.architecture.util.exSetRoundRectImage
import com.example.mobishalaassignment.databinding.ItemPopularMoviesBinding
import com.example.mobishalaassignment.databinding.ItemProductionCompanyBinding

/**
 * Created by Sanjay Kumar on 07/02/2022.
 */
class ProductionCompaniesAdapter(private val result: ArrayList<ProductionComapany>, private val context: Context) : RecyclerView.Adapter<ProductionCompaniesAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(val binding: ItemProductionCompanyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(position: Int, productionComapany: ProductionComapany) {
            binding.productionName.text = productionComapany.name
            binding.productionCountry.text = productionComapany.originCountry

            if(productionComapany.logoPath.isNullOrBlank())
            {
                Glide.with(context).load(context.getDrawable(R.drawable.ic_launcher_background)).apply(RequestOptions().circleCrop()).into(binding.productionCompanyImage)
            }else {
                binding.productionCompanyImage.exSetRoundRectImage(
                    context,
                    "",
                    Constants.IMAGE_BASE_URL + productionComapany.logoPath
                )
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionCompaniesAdapter.MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_production_company, parent, false))
    }


    override fun onBindViewHolder(holder: ProductionCompaniesAdapter.MovieViewHolder, position: Int) {
        holder.bindItems(position, result[position])
    }
    override fun getItemCount(): Int {
        return result.size
    }


}