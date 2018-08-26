package com.mdelacruz.imagefindermvp

import com.mdelacruz.imagefindermvp.api.ImageService
import com.mdelacruz.imagefindermvp.api.model.ImageResponse
import com.mdelacruz.imagefindermvp.ui.presenter.MainFragmentPresenter
import com.mdelacruz.imagefindermvp.ui.view.MainFragmentView
import com.mdelacruz.imagefindermvp.util.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MainFragmentPresenterTest {

  private val view = Mockito.mock(MainFragmentView::class.java)
  private val service = Mockito.mock(ImageService::class.java)
  private lateinit var mainFragmentPresenter: MainFragmentPresenter
  private lateinit var testScheduler: TestScheduler

  @Before
  fun setup() {
    val compositeDisposable = CompositeDisposable()
    testScheduler = TestScheduler()
    val testSchedulerProvider = TestSchedulerProvider(testScheduler)
    mainFragmentPresenter = MainFragmentPresenter(service, compositeDisposable, testSchedulerProvider)
    mainFragmentPresenter.attachView(view)
  }

  @Test
  fun test_search_should_callSuccess() {
    val mockedResponse = Mockito.mock(ImageResponse::class.java)
    val searchView = "test"

    Mockito.doReturn(Observable.just(mockedResponse))
        .`when`(service)
        .searchImages(searchView)

    mainFragmentPresenter.search(searchView)

    testScheduler.triggerActions()

    Mockito.verify(view).showLoadingIndicator()
    Mockito.verify(view).updateImageResult(mockedResponse.images)
    Mockito.verify(view).hideLoadingIndicator()
  }

  @Test
  fun test_search_should_callError() {
    val mockedResponse = Mockito.mock(Throwable::class.java)
    val searchView = "test"

    Mockito.doReturn(Observable.just(mockedResponse))
        .`when`(service)
        .searchImages(searchView)

    mainFragmentPresenter.search(searchView)

    testScheduler.triggerActions()

    Mockito.verify(view).showLoadingIndicator()
    Mockito.verify(view).onError()
    Mockito.verify(view).hideLoadingIndicator()
  }

  @After
  fun tearDown() {
    mainFragmentPresenter.detachView()
  }
}