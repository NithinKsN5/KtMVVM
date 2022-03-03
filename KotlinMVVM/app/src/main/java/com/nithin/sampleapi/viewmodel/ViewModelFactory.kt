package com.nithin.sampleapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nithin.sampleapi.model.UserDetailsRepository


class ViewModelFactory(private val repository: UserDetailsRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserDetailsViewModel(repository) as T
    }
}