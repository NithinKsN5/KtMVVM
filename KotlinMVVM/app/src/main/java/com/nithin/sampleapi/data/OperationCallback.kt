package com.nithin.sampleapi.data


interface OperationCallback<T> {
    fun onSuccess(data: T?)
    fun onError(error: String?)
}