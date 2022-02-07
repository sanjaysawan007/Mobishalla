package com.example.mobishalaassignment.architecture.util

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mobishalaassignment.R


/**
 * Created by Sanjay Kumar on 06/02/2022.
 */

fun String.exShowToast(context: Context?) = Toast.makeText(context, this, Toast.LENGTH_LONG).show()
fun TextView.setDrawableColor(@ColorRes color: Int) {
    compoundDrawables.filterNotNull().forEach {
        it.colorFilter =
            PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)
    }
}

fun String?.exIsNotEmptyOrNullOrBlank() = !this.isNullOrBlank() && !this.isNullOrEmpty()

fun ImageView.exSetImageRoundRect(
    context: Context,
    imageText: String? = "",
    urlToLoad: String? = ""
) {
    when (urlToLoad.exIsNotEmptyOrNullOrBlank() && urlToLoad!!.startsWith("http")) {
        true -> Glide.with(context).load(urlToLoad).into(this)

    }
}
fun ImageView.exSetRoundRectImage(
    context: Context,
    imageText: String? = "",
    urlToLoad: String? = ""
) {
    when (urlToLoad.exIsNotEmptyOrNullOrBlank() && urlToLoad!!.startsWith("http")) {
        true -> Glide.with(context).load(urlToLoad).apply(RequestOptions().circleCrop()).into(this)
        false -> Glide.with(context).load(context.getDrawable(R.drawable.ic_launcher_background)).apply(RequestOptions().circleCrop()).into(this)
    }
}