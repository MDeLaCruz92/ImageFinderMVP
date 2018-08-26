package com.mdelacruz.imagefindermvp.base

import com.mdelacruz.imagefindermvp.util.TestSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class BasePresenterTest {

  private lateinit var basePresenter: BasePresenter<BaseView>
  private val view = Mockito.mock(BaseView::class.java)

  @Before
  fun setup() {
    val compositeDisposable = CompositeDisposable()
    val testSchedulerProvider = TestSchedulerProvider(TestScheduler())
    basePresenter = BasePresenter(compositeDisposable, testSchedulerProvider)
  }

  @Test
  fun attachView() {
    basePresenter.attachView(view)
    Mockito.verify(view).setPresenter(basePresenter)
  }

  @After
  fun tearDown() {
    basePresenter.detachView()
  }
}