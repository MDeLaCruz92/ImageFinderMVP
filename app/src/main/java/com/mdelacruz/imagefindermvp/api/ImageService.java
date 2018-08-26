package com.mdelacruz.imagefindermvp.api;

import com.mdelacruz.imagefindermvp.api.model.ImageResponse;
import com.mdelacruz.imagefindermvp.api.model.MetadataResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ImageService {
  private static final String FIELDS = "id,title,thumb";
  private static final String SORT_ORDER = "best";

  @Inject
  ImagesApi api;

  @Inject
  public ImageService() {
  }

  public Observable<ImageResponse> searchImages(String phrase) {
    return api.searchImages(phrase, FIELDS, SORT_ORDER);
  }

  public Observable<MetadataResponse> getImageMetadata(String id) {
    return api.getImageMetadata(id);
  }

  public Observable<ImageResponse> getSimilarImages(String id) {
    return api.getSimilarImages(id);
  }
}
