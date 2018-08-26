package com.mdelacruz.imagefindermvp.ui.factory;

import com.mdelacruz.imagefindermvp.api.model.ImageResult;
import com.mdelacruz.imagefindermvp.ui.viewmodel.ImageViewModel;

import javax.inject.Inject;

public class ImageFactory {
  @Inject
  public ImageFactory() {
  }

  public ImageViewModel createImageViewModel(int id, ImageResult imageResult, ImageViewModel.Listener listener) {
    return new ImageViewModel(id, imageResult, listener);
  }
}