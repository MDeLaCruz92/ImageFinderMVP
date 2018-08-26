package com.mdelacruz.imagefindermvp.ui.presenter

import com.mdelacruz.imagefindermvp.api.ImageService
import com.mdelacruz.imagefindermvp.base.BasePresenter
import com.mdelacruz.imagefindermvp.ui.view.MainFragmentView
import com.mdelacruz.imagefindermvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainFragmentPresenter @Inject constructor(var service: ImageService,
                                                disposable: CompositeDisposable,
                                                scheduler: SchedulerProvider)
  : BasePresenter<MainFragmentView>(disposable, scheduler){

  fun search(searchView: String) {
    view?.showLoadingIndicator()
    disposable.add(service.searchImages(searchView)
        .subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
        .subscribe(
            { imageResponse ->
              view?.hideLoadingIndicator()
              view?.updateImageResult(imageResponse.images)

              if (imageResponse.images.isEmpty()) {
                view?.showSearchError()
              }
            },
            {
              view?.hideLoadingIndicator()
              view?.onError()
            }
        )
    )
  }

}