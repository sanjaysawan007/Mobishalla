package com.example.mobishalaassignment.architecture.customAppComponents.activity

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.mobishalaassignment.R
import com.example.mobishalaassignment.architecture.application.ArchitectureApp
import com.example.mobishalaassignment.databinding.ActivityBaseBinding
import com.example.mobishalaassignment.presenter.connector.ReusableView


abstract class BaseAppCompatActivity : BaseAppActivityImpl(), ReusableView {

  abstract fun init()
  private lateinit var toggle: ActionBarDrawerToggle
  private var isBackPressDialogToShow = false
  private var view: View? = null
  private lateinit var bindingParent: ActivityBaseBinding
  var connectionDialgue:BottomSheetDialog?=null

    companion object {
    internal var progressDialog: ProgressDialog? = null
  }

  private fun setContentBindingTemp() {
    bindingParent = DataBindingUtil.setContentView(getActivity(), R.layout.activity_base)

  }

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ArchitectureApp.instance.component.inject(this)
    setContentBindingTemp()
      //setToolbar()
      //  showToolbar()
      //setUpHeaderView()
      initializeViewBindingTemp()


  }

  private fun initializeViewBindingTemp() {
    view = bindingParent.root
      // initializeOtherViews()
    init()

  }
  override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //menuInflater.inflate(R.menu.menu_lead_action, menu)
        return true
  }
  override fun showToast(msg: String) {
    //msg.exShowToast(getContext())
  }

  override fun showProgressDialog() {
    /*when (progressDialog == null) {
      true -> progressDialog = DialogFactory.getInstance(context = getContext())
    }
    progressDialog?.show()*/
  }

  override fun hideProgressDialog() {
    progressDialog?.hide()
  }



  /*@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  fun networkBottomSheet(action:()->Unit){
      val viewGroup = findViewById<ViewGroup>(android.R.id.content)
      connectionDialgue = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
      val view = LayoutInflater.from(this).inflate(R.layout.layout_connection_lost, viewGroup,false)
      val btnRetry=view?.findViewById<Button>(R.id.btnRetry)
      btnRetry?.setOnClickListener {
          if (ConnectivityUtil.isConnected(this))
          {   action()
              connectionDialgue?.dismiss()
          }
      }


      connectionDialgue?.setContentView(view)
      connectionDialgue?.setCancelable(false)
      connectionDialgue?.show()
  }*/





    fun setToolBarHeading(heading: String) {
        bindingParent.appBarWithLayout.actionbarTextview.text = heading
    }

  fun getParentBinding(): ActivityBaseBinding {
    return bindingParent
  }

  override fun getApiFailure(msg: String) {
    showToast(msg)
  }


   /* @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun connectivityListerner()
    {
        val networkCallback: NetworkCallback =
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                // network available
                connectionDialgue?.hide()
            }

            override fun onLost(network: Network) {
                // network unavailable
                connectionDialgue?.setCancelable(false)
                connectionDialgue?.show()
            }
        }

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager.registerNetworkCallback(request, networkCallback)
        }
        }*/
    }






