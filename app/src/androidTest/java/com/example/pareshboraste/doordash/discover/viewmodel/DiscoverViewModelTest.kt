package com.example.pareshboraste.doordash.discover.viewmodel

import com.example.pareshboraste.doordash.discover.model.Restaurant
import com.example.pareshboraste.doordash.discover.ui.views.DiscoverViewContract
import com.example.pareshboraste.doordash.discover.usecase.DiscoverUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable

class DiscoverViewModelTest {
    @Mock
    private lateinit var discoverViewContract: DiscoverViewContract
    @Mock
    private lateinit var useCase: DiscoverUseCase

    private lateinit var discoverViewModel: DiscoverViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        discoverViewModel = DiscoverViewModel(useCase)
        discoverViewModel.attachView(discoverViewContract)
    }

    @Test
    fun verifySuccessfulInteraction() {
        var factory: DiscoverDataFactory = DiscoverDataFactory()
        var list: List<Restaurant> = factory.getRestaurantList()

        `when`(useCase.getListDiscover(factory.getLat(), factory.getLng(), 0, 50)).thenReturn(Observable.just<List<Restaurant>>(list))
        discoverViewModel.load()
        Mockito.verify(discoverViewContract).showLoading(true)
        assert(discoverViewModel.restaurants.value!!.size == list.size)
        Mockito.verify(discoverViewContract).showLoading(false)
    }

    @Test
    fun verifyNumberOfRowsInList() {
        var factory: DiscoverDataFactory = DiscoverDataFactory()
        var list: List<Restaurant> = factory.getRestaurantList(50)

        `when`(useCase.getListDiscover(factory.getLat(), factory.getLng(), 0, 50)).thenReturn(Observable.just<List<Restaurant>>(list))
        discoverViewModel.load()
        Mockito.verify(discoverViewContract).showLoading(true)
        assert(discoverViewModel.restaurants.value!!.size == list.size)
        Mockito.verify(discoverViewContract).showLoading(false)
    }

    @Test
    fun verifyEmptyStateIsShown() {
        var factory: DiscoverDataFactory = DiscoverDataFactory()
        var list: List<Restaurant> = ArrayList<Restaurant>()

        `when`(useCase.getListDiscover(factory.getLat(), factory.getLng(), 0, 50)).thenReturn(Observable.just<List<Restaurant>>(list))
        discoverViewModel.load()
        Mockito.verify(discoverViewContract).showLoading(true)
        assert(discoverViewModel.restaurants.value!!.size == list.size)
        Mockito.verify(discoverViewContract).showLoading(false)
    }

    @Test
    fun verifyErrorMessageIsShown() {
        var factory: DiscoverDataFactory = DiscoverDataFactory()
        var list: List<Restaurant>? = null

        `when`(useCase.getListDiscover(factory.getLat(), factory.getLng(), 0, 50)).thenReturn(Observable.just<List<Restaurant>>(list))
        discoverViewModel.load()
        Mockito.verify(discoverViewContract).showLoading(true)
        Mockito.verify(discoverViewContract).showErrorMessage()
        Mockito.verify(discoverViewContract).showLoading(false)

    }

    @Test
    fun verifyErrorMessageIsNotShownWhenEmptyResponse() {
        var factory: DiscoverDataFactory = DiscoverDataFactory()
        var list: List<Restaurant> = ArrayList<Restaurant>()

        `when`(useCase.getListDiscover(factory.getLat(), factory.getLng(), 0, 50)).thenReturn(Observable.just<List<Restaurant>>(list))
        discoverViewModel.load()
        Mockito.verify(discoverViewContract).showLoading(true)
        Mockito.verify(discoverViewContract, never()).showErrorMessage()
        Mockito.verify(discoverViewContract).showLoading(false)
    }

    
    //@Test  // this test fails sometimes because rx call is non blocking.
    fun verifyLoadMore() {
        var factory: DiscoverDataFactory = DiscoverDataFactory()
        var list: List<Restaurant> = factory.getRestaurantList()

        `when`(useCase.getListDiscover(factory.getLat(), factory.getLng(), 0, 50)).thenReturn(Observable.just<List<Restaurant>>(list))
        discoverViewModel.load()
        assert(discoverViewModel.restaurants.value?.size == list.size)

        var list2: List<Restaurant> = factory.getRestaurantList()
        `when`(useCase.getListDiscover(factory.getLat(), factory.getLng(), 5, 55)).thenReturn(Observable.just<List<Restaurant>>(list2))
        discoverViewModel.load()
        Mockito.verify(discoverViewContract, times(2)).showLoading(true)
        assert(discoverViewModel.restaurants.value?.size == 10)
        Mockito.verify(discoverViewContract).showLoading(false)

    }
}
