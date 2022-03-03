package com.nithin.sampleapi.model

import com.nithin.sampleapi.data.OperationCallback
import com.nithin.sampleapi.data.UserDetailsResponse

class UserDetailsRepository(private val userDataSource: UserDataSource) {

    fun fetchUserData(callback: OperationCallback<UserDetailsResponse>, userId : Int) {
        userDataSource.retrieveUserDetails(callback,userId)
    }

    /*fun cancel() {
        userDataSource.cancel()
    }*/
}