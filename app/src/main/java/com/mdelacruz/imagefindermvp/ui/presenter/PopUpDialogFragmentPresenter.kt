package com.mdelacruz.imagefindermvp.ui.presenter

import com.mdelacruz.imagefindermvp.api.ImageService
import com.mdelacruz.imagefindermvp.base.BasePresenter
import com.mdelacruz.imagefindermvp.manager.ImageManager
import com.mdelacruz.imagefindermvp.ui.view.PopUpDialogFragmentView
import com.mdelacruz.imagefindermvp.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopUpDialogFragmentPresenter @Inject constructor(var service: ImageService,
                                                       disposable: CompositeDisposable,
                                                       scheduler: SchedulerProvider)
  : BasePresenter<PopUpDialogFragmentView>(disposable, scheduler) {

  fun fetchMetadata(id: String) {
    disposable.add(service.getImageMetadata(id)
        .subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
        .subscribe(
            { metadataResponse ->
              for (metadata in metadataResponse.metadata) {
                if (id == metadata.id) {
                  ImageManager.savedTitle = metadata.title
                  ImageManager.savedArtist = metadata.artist
                }
              }
              view?.loadMetadata(ImageManager.savedTitle, ImageManager.savedArtist)
              ImageManager.savedTitle = ""
              ImageManager.savedArtist = ""
            },
            {
              view?.showMetadataError()
            }
        )
    )
  }

  fun fetchSimilarImages(id: String) {
    disposable.add(service.getSimilarImages(id)
        .subscribeOn(scheduler.io())
        .observeOn(scheduler.ui())
        .subscribe(
            { imageResponse ->
              for (image in imageResponse.images) {
                ImageManager.savedImageList.add(image.thumbUri)
              }
              view?.loadImagesUri(ImageManager.savedImageList)
              ImageManager.savedImageList = mutableListOf()
            },
            {
              view?.showSimilarImagesError()
            }
        )
    )
  }

}