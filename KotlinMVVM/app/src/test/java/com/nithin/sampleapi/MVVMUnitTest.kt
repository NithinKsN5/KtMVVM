package com.nithin.sampleapi

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nithin.sampleapi.data.OperationCallback
import com.nithin.sampleapi.data.UserDetailsResponse
import com.nithin.sampleapi.model.UserDataSource
import com.nithin.sampleapi.model.UserDetailsRepository
import com.nithin.sampleapi.viewmodel.UserDetailsViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.*
import org.mockito.Mockito.*

/**
 * @author Eduardo Medina
 */
class MVVMUnitTest {

    @Mock
    private lateinit var museumDataSource: UserDataSource

    @Mock
    private lateinit var context: Application

    @Captor
    private lateinit var operationCallbackCaptor: ArgumentCaptor<OperationCallback<UserDetailsResponse>>

    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var repository: UserDetailsRepository

    private lateinit var isViewLoadingObserver: Observer<Boolean>
    private lateinit var onMessageErrorObserver: Observer<Any>
    private lateinit var emptyListObserver: Observer<Boolean>
    private lateinit var onRenderUserDetailsObserver: Observer<UserDetailsResponse>

    private lateinit var museumEmptyList: List<UserDetailsResponse>
    private lateinit var museumList: List<UserDetailsResponse>

    /**
    //https://jeroenmols.com/blog/2019/01/17/livedatajunit5/
    //Method getMainLooper in android.os.Looper not mocked

    java.lang.IllegalStateException: operationCallbackCaptor.capture() must not be null
     */
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(context.applicationContext).thenReturn(context)

        repository = UserDetailsRepository(museumDataSource)
        viewModel = UserDetailsViewModel(repository)

        /*mockData()
        setupObservers()*/
    }

    /*@Test
    fun `retrieve museums with ViewModel and Repository returns empty data`() {
        with(viewModel) {
            loadUserDetailss()
            isViewLoading.observeForever(isViewLoadingObserver)
            isEmptyList.observeForever(emptyListObserver)
            museums.observeForever(onRenderUserDetailsObserver)
        }

        verify(museumDataSource, times(1)).retrieveUserDetailss(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onSuccess(museumEmptyList)

        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertTrue(viewModel.isEmptyList.value == true)
        Assert.assertTrue(viewModel.museums.value?.size == 0)
    }

    @Test
    fun `retrieve museums with ViewModel and Repository returns full data`() {
        with(viewModel) {
            loadUserDetailss()
            isViewLoading.observeForever(isViewLoadingObserver)
            museums.observeForever(onRenderUserDetailsObserver)
        }

        verify(museumDataSource, times(1)).retrieveUserDetailss(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onSuccess(museumList)

        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertTrue(viewModel.museums.value?.size == 3)
    }

    @Test
    fun `retrieve museums with ViewModel and Repository returns an error`() {
        with(viewModel) {
            loadUserDetailss()
            isViewLoading.observeForever(isViewLoadingObserver)
            onMessageError.observeForever(onMessageErrorObserver)
        }
        verify(museumDataSource, times(1)).retrieveUserDetailss(capture(operationCallbackCaptor))
        operationCallbackCaptor.value.onError("An error occurred")
        Assert.assertNotNull(viewModel.isViewLoading.value)
        Assert.assertNotNull(viewModel.onMessageError.value)
    }

    private fun setupObservers() {
        isViewLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        onMessageErrorObserver = mock(Observer::class.java) as Observer<Any>
        emptyListObserver = mock(Observer::class.java) as Observer<Boolean>
        onRenderUserDetailsObserver = mock(Observer::class.java) as Observer<List<UserDetails>>
    }

    private fun mockData() {
        museumEmptyList = emptyList()
        val mockList: MutableList<UserDetails> = mutableListOf()
        mockList.add(
            UserDetails(
                0,
                "Museo Nacional de Arqueología, Antropología e Historia del Perú",
                ""
            )
        )
        mockList.add(UserDetails(1, "Museo de Sitio Pachacamac", ""))
        mockList.add(UserDetails(2, "Casa Museo José Carlos Mariátegui", ""))

        museumList = mockList.toList()
    }*/
}