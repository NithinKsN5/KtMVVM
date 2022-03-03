package com.nithin.sampleapi.model

import com.nithin.sampleapi.data.OperationCallback
import com.nithin.sampleapi.data.UserDetailsResponse


interface UserDataSource {

    fun retrieveUserDetails(callback: OperationCallback<UserDetailsResponse>, userId: Int)
    fun cancel()
}