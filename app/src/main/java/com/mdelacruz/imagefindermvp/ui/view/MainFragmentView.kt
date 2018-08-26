package com.mdelacruz.imagefindermvp.ui.view

import com.mdelacruz.imagefindermvp.api.model.ImageResult
import com.mdelacruz.imagefindermvp.base.BaseView

interface MainFragmentView : BaseView {
  fun hideLoadingIndicator()
  fun showLoadingIndicator()
  fun showSearchError()
  fun updateImageResult(images: List<ImageResult>)
}