package com.mdelacruz.imagefindermvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.mdelacruz.imagefindermvp.R
import com.mdelacruz.imagefindermvp.base.BaseDialog
import com.mdelacruz.imagefindermvp.manager.ImageManager
import com.mdelacruz.imagefindermvp.ui.presenter.PopUpDialogFragmentPresenter
import com.mdelacruz.imagefindermvp.ui.view.PopUpDialogFragmentView
import com.mdelacruz.imagefindermvp.util.toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dialog_popup.*
import javax.inject.Inject

class PopUpDialogFragment : BaseDialog(), PopUpDialogFragmentView {

  //==============================================================================================
  // Properties
  //==============================================================================================

  @Inject
  lateinit var popUpDialogFragmentPresenter: PopUpDialogFragmentPresenter

  private var listener: PopUpDialogListener? = null

  //==============================================================================================
  // Lifecycle Methods
  //==============================================================================================

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.fragment_dialog_popup, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    listener?.getPressedImage()
    popUpDialogFragmentPresenter.fetchMetadata(ImageManager.savedImageId)
    popUpDialogFragmentPresenter.fetchSimilarImages(ImageManager.savedImageId)
    loadPressedImage()
  }

  override fun onFragmentInject() {
    getAppComponent().inject(this)
    popUpDialogFragmentPresenter.attachView(this)
  }

  //==============================================================================================
  // Instance Methods
  //==============================================================================================

  fun setPopupDialogListener(listener: PopUpDialogListener) {
    this.listener = listener
  }

  private fun loadPressedImage() {
    Picasso.get()
        .load(ImageManager.savedImageUri)
        .into(image_view)
  }

  //==============================================================================================
  //  PopUpDialogFragmentView Implementation
  //==============================================================================================

  override fun showMetadataError() {
    activity?.toast(R.string.fetch_metadata_error)

  }

  override fun showSimilarImagesError() {
    activity?.toast(R.string.fetch_similar_images_error)
  }

  override fun loadMetadata(title: String, artist: String) {
    title_view.text = title
    artist_view.text = artist
  }

  override fun loadImagesUri(uris: List<String>) {
    val images = listOf<ImageView>(similar_image_view1, similar_image_view2, similar_image_view3)
    for ((uriCount, image) in images.withIndex()) {
      Picasso.get()
          .load(uris[uriCount])
          .into(image)
    }
  }
}

interface PopUpDialogListener {
  fun getPressedImage()
}