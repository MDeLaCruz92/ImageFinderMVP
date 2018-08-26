package com.mdelacruz.imagefindermvp.ui.view

import com.mdelacruz.imagefindermvp.base.BaseView

interface PopUpDialogFragmentView : BaseView {
  fun showMetadataError()
  fun showSimilarImagesError()
  fun loadMetadata(title: String, artist: String)
  fun loadImagesUri(uris: List<String>)
}