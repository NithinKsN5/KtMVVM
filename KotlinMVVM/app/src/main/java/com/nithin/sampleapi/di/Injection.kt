package com.nithin.sampleapi.di

import com.nithin.sampleapi.data.ApiClient
import com.nithin.sampleapi.data.UserRemoteDataSource
import com.nithin.sampleapi.model.UserDataSource
import com.nithin.sampleapi.model.UserDetailsRepository
import com.nithin.sampleapi.viewmodel.ViewModelFactory


object Injection {

    private var userDataSource: UserDataSource? = null
    private var userDetailsRepository: UserDetailsRepository? = null
    private var userViewModelFactory: ViewModelFactory? = null

    private fun createUserDataSource(): UserDataSource {
        val dataSource = UserRemoteDataSource(ApiClient)
        userDataSource = dataSource
        return dataSource
    }

    private fun createUserRepository(): UserDetailsRepository {
        val repository = UserDetailsRepository(provideDataSource())
        userDetailsRepository = repository
        return repository
    }

    private fun createFactory(): ViewModelFactory {
        val factory = ViewModelFactory(providerRepository())
        userViewModelFactory = factory
        return factory
    }

    private fun provideDataSource() = userDataSource ?: createUserDataSource()
    private fun providerRepository() = userDetailsRepository ?: createUserRepository()

    fun provideViewModelFactory() = userViewModelFactory ?: createFactory()

    fun destroy() {
        userDataSource = null
        userDetailsRepository = null
        userViewModelFactory = null
    }
}