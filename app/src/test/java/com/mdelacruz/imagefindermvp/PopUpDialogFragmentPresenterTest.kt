package com.mdelacruz.imagefindermvp

import com.mdelacruz.imagefindermvp.api.ImageService
import com.mdelacruz.imagefindermvp.api.model.ImageResponse
import com.mdelacruz.imagefindermvp.api.model.MetadataResponse
import com.mdelacruz.imagefindermvp.manager.ImageManager
import com.mdelacruz.imagefindermvp.ui.presenter.PopUpDialogFragmentPresenter
import com.mdelacruz.imagefindermvp.ui.view.PopUpDialogFragmentView
import com.mdelacruz.imagefindermvp.util.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class PopUpDialogFragmentPresenterTest {

  private val view = Mockito.mock(PopUpDialogFragmentView::class.java)
  private val service = Mockito.mock(ImageService::class.java)
  private lateinit var presenter: PopUpDialogFragmentPresenter
  private lateinit var testScheduler: TestScheduler

  @Before
  fun setup() {
    val compositeDisposable = CompositeDisposable()
    testScheduler = TestScheduler()
    val testSchedulerProvider = TestSchedulerProvider(testScheduler)
    presenter = PopUpDialogFragmentPresenter(service, compositeDisposable, testSchedulerProvider)
    presenter.attachView(view)
  }

  @Test
  fun test_fetchMetadata_should_callSuccess() {
    val mockedResponse = Mockito.mock(MetadataResponse::class.java)
    val id = ImageManager.savedImageId

    Mockito.doReturn(Observable.just(mockedResponse))
        .`when`(service)
        .getImageMetadata(id)

    presenter.fetchMetadata(id)

    testScheduler.triggerActions()

    Mockito.verify(view).loadMetadata(ImageManager.savedTitle, ImageManager.savedArtist)
  }

  @Test
  fun test_fetchMetadata_should_callError() {
    val mockedResponse = Mockito.mock(Throwable::class.java)
    val id = ImageManager.savedImageId

    Mockito.doReturn(Observable.just(mockedResponse))
        .`when`(service)
        .getImageMetadata(id)

    presenter.fetchMetadata(id)

    testScheduler.triggerActions()

    Mockito.verify(view).showMetadataError()
  }

  @Test
  fun test_fetchSimilarImages_should_callSuccess() {
    val mockedResponse = Mockito.mock(ImageResponse::class.java)
    val id = ImageManager.savedImageId

    Mockito.doReturn(Observable.just(mockedResponse))
        .`when`(service)
        .getSimilarImages(id)

    presenter.fetchSimilarImages(id)

    testScheduler.triggerActions()

    Mockito.verify(view).loadImagesUri(ImageManager.savedImageList)
  }

  @Test
  fun test_fetchSimilarImages_should_callError() {
    val mockedResponse = Mockito.mock(Throwable::class.java)
    val id = ImageManager.savedImageId

    Mockito.doReturn(Observable.just(mockedResponse))
        .`when`(service)
        .getSimilarImages(id)

    presenter.fetchSimilarImages(id)

    testScheduler.triggerActions()

    Mockito.verify(view).showSimilarImagesError()
  }

  @After
  fun tearDown() {
    presenter.detachView()
  }
}