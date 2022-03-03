package com.nithin.sampleapi.data

import com.nithin.sampleapi.model.UserDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRemoteDataSource(apiClient: ApiClient) : UserDataSource {

    private var call: Call<UserDetailsResponse>? = null
    private val service = apiClient.build()

    override fun retrieveUserDetails(callback: OperationCallback<UserDetailsResponse>, userId: Int) {

        call = service.getUserBasedOnId(userId)
        call?.enqueue(object : Callback<UserDetailsResponse> {
            override fun onFailure(call: Call<UserDetailsResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<UserDetailsResponse>,
                response: Response<UserDetailsResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError(response.message())
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.cancel()
    }
}