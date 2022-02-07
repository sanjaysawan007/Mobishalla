package com.example.mobishalaassignment.ui.moviedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.mobishalaassignment.R
import com.example.mobishalaassignment.architecture.application.ArchitectureApp
import com.example.mobishalaassignment.architecture.constants.Constants
import com.example.mobishalaassignment.architecture.constants.ConstantsApi
import com.example.mobishalaassignment.architecture.customAppComponents.activity.BaseAppCompatActivity
import com.example.mobishalaassignment.architecture.retrofit.api.requests.Requests
import com.example.mobishalaassignment.architecture.retrofit.api.response.model.movie.*
import com.example.mobishalaassignment.architecture.util.delegates.ActivityBindingProviderDelegate
import com.example.mobishalaassignment.architecture.util.exSetImageRoundRect
import com.example.mobishalaassignment.databinding.ActivityMovieDetailsBinding
import com.example.mobishalaassignment.presenter.presenter.Presenter
import com.example.mobishalaassignment.presenter.presenter.ViewGeneric
import com.example.mobishalaassignment.ui.adapter.recycler.CastAdapter
import com.example.mobishalaassignment.ui.adapter.recycler.ProductionCompaniesAdapter

class ActivityMovieDetails : BaseAppCompatActivity() {
    private var bundle: Bundle? = null
    private val presenter = Presenter()
    private var movieResponseData: MovieResult? = null
    private val binding: ActivityMovieDetailsBinding by ActivityBindingProviderDelegate(
        this, R.layout.activity_movie_details)
    companion object {
        private const val MOVIE_DATA = "movieData"
        fun start(context: Context,movieResult: MovieResult) {
            val intent = Intent(context, ActivityMovieDetails::class.java)
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_DATA,movieResult)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
    override fun init() {
        ArchitectureApp.instance.component.inject(this)
        getMovieDataFromBundle()
        presenter.callNetwork(ConstantsApi.CALL_MOVIEDETAIL_API,CallMovieDetailsApi())
        presenter.callNetwork(ConstantsApi.CALL_CAST_API,CallCastDetailsApi())
    }
    private fun getMovieDataFromBundle(){
        bundle = intent.extras
        bundle?.let {
            movieResponseData = it.getParcelable(MOVIE_DATA)

            //setLeadDetailsToViews(it.getSerializable(UpdateCallActivity.KEY_LEAD) as AllLeadMaster)
        }
    }
    private fun setMovieDataToView(movieDetailResponse: MovieDetailResponse){
        System.out.println("Title>>"+movieDetailResponse.id)
        binding.imagePoster.exSetImageRoundRect(this,"",Constants.IMAGE_BASE_URL+movieDetailResponse.posterPath)
        binding.textTitle.text = movieDetailResponse.title
        binding.textOverview.text = movieDetailResponse.overview
        binding.textReleaseDate.text = movieDetailResponse.releaseDate
        binding.textLanguage.text = movieResponseData?.original_language
        binding.textVoteCount.text = movieDetailResponse.status
    }
    inner class CallMovieDetailsApi : ViewGeneric<Requests.RequestMovieID, MovieDetailResponse>(context = this) {
        override val apiRequest: Requests.RequestMovieID?
            get() = mRequestRequestMovieID
        private  val mRequestRequestMovieID:Requests.RequestMovieID
            get(){
                val movieID=movieResponseData?.movieId
                return Requests.RequestMovieID(movieId = movieID!!)
            }

        override fun getApiSuccess(value: MovieDetailResponse) {
            setMovieDataToView(value!!)
            val productionCompaniesAdapter = ProductionCompaniesAdapter(value.productionCompany as ArrayList<ProductionComapany>,applicationContext)
            binding.rvProductionCompanies.adapter = productionCompaniesAdapter
        }

    }
    inner class CallCastDetailsApi : ViewGeneric<Requests.RequestMovieID, CastResponse>(context = this) {
        override val apiRequest: Requests.RequestMovieID?
            get() = mRequestRequestMovieID
        private  val mRequestRequestMovieID:Requests.RequestMovieID
            get(){
                val movieID=movieResponseData?.movieId
                return Requests.RequestMovieID(movieId = movieID!!)
            }

        override fun getApiSuccess(value: CastResponse) {

            val castAdapter = CastAdapter(value.cast as ArrayList<CastResult>,applicationContext)
            binding.rvCast.adapter = castAdapter
        }

    }
}