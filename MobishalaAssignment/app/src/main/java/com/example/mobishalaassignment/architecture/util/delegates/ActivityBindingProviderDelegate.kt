package com.example.mobishalaassignment.architecture.util.delegates

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import com.example.mobishalaassignment.architecture.customAppComponents.activity.BaseAppCompatActivity
import com.example.mobishalaassignment.databinding.ActivityBaseBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Sanjay Kumar on 06/02/2022.
 */
class ActivityBindingProviderDelegate<out T : ViewDataBinding>(
    private val baseAppCompatActivity: BaseAppCompatActivity, @LayoutRes private val layoutRes: Int) : ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: createBinding(
            baseAppCompatActivity.getParentBinding()).also { binding = it }
    }

    private fun createBinding(bindingParent: ActivityBaseBinding): T {
        val inflater = LayoutInflater.from(bindingParent.appBarWithLayout.llInflatorContainer.context)
        return DataBindingUtil.inflate(inflater, layoutRes, bindingParent.appBarWithLayout.llInflatorContainer, true)
    }
}