package com.nithin.sampleapi.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.nithin.sampleapi.R
import com.nithin.sampleapi.data.UserDetailsResponse
import com.nithin.sampleapi.di.Injection
import com.nithin.sampleapi.viewmodel.UserDetailsViewModel

class UserDetailsActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "CONSOLE"
        private const val NOT_AVAILABLE = "Not available"
        private const val ERROR = "Error"
    }

    private val viewModel by viewModels<UserDetailsViewModel> {
        Injection.provideViewModelFactory()
    }
    private lateinit var layoutError: View
    private lateinit var textViewError: TextView
    private lateinit var layoutEmpty: View
    private lateinit var txtEmail: TextView
    private lateinit var txtBtn1: TextView
    private lateinit var txtBtn3: TextView
    private lateinit var txtBtn10: TextView
    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        setupViewModel()
        setupUI()
    }

    //ui
    private fun setupUI() {
        layoutError = findViewById(R.id.layoutError)
        layoutEmpty = findViewById(R.id.layoutEmpty)
        progressBar = findViewById(R.id.progressBar)
        txtEmail = findViewById(R.id.txtEmailAddress)
        txtBtn1 = findViewById(R.id.txtBtn1)
        txtBtn3 = findViewById(R.id.txtBtn3)
        txtBtn10 = findViewById(R.id.txtBtn10)
        txtEmail = findViewById(R.id.txtEmailAddress)
        textViewError = findViewById(R.id.textViewError)
        txtEmail.setOnClickListener {
            viewModel.getUserForId()
        }
        txtBtn1.setOnClickListener {
            txtBtn10.resetBgColor()
            txtBtn3.resetBgColor()
            txtBtn1.changeBgColor()
            viewModel.getUserForId(1)
        }
        txtBtn3.setOnClickListener {
            txtBtn1.resetBgColor()
            txtBtn3.changeBgColor()
            txtBtn10.resetBgColor()
            viewModel.getUserForId(3)
        }
        txtBtn10.setOnClickListener {
            txtBtn10.changeBgColor()
            txtBtn3.resetBgColor()
            txtBtn1.resetBgColor()
            viewModel.getUserForId(10)
        }
    }

    //view model
    private fun setupViewModel() {
        viewModel.userDetails.observe(this, renderUserData)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    //observers
    private val renderUserData = Observer<UserDetailsResponse> {
        txtEmail.text = it.data?.email ?: NOT_AVAILABLE
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        val msgError = "$ERROR $it"
        textViewError.text = msgError
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }

    //If you require updated data, you can call the method "loadMuseum" here
    override fun onResume() {
        super.onResume()
        viewModel.getUserForId()
    }

    override fun onDestroy() {
        super.onDestroy()
        Injection.destroy()
    }

    private fun TextView.resetBgColor() {
        setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.colorTransparent,
                null
            )
        )
    }

    private fun TextView.changeBgColor() {
        setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.colorAccent,
                null
            )
        )
    }

}
