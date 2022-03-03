package com.nithin.sampleapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nithin.sampleapi.data.OperationCallback
import com.nithin.sampleapi.data.UserDetailsResponse
import com.nithin.sampleapi.model.UserDetailsRepository


class UserDetailsViewModel(private val repository: UserDetailsRepository) : ViewModel() {

    val userDetails: LiveData<UserDetailsResponse>
        get() = _userDetails
    private val _userDetails =
        MutableLiveData<UserDetailsResponse>()

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    /*
    If you require that the data be loaded only once, you can consider calling the method
    "loadMuseums()" on constructor. Also, if you rotate the screen, the service will not be called.

    init {
        //loadMuseums()
    }
     */

    fun getUserForId(userId: Int = 1) {
        _isViewLoading.value = true
        repository.fetchUserData(object : OperationCallback<UserDetailsResponse> {
            override fun onError(error: String?) {
                _isViewLoading.value = false
                _onMessageError.value = error ?: "Something went wrong"
            }

            override fun onSuccess(data: UserDetailsResponse?) {
                _isViewLoading.value = false
                data?.let {
                    _userDetails.value = it
                } ?: {
                    _isEmptyList.value = true
                }
            }
        }, userId)
    }

}