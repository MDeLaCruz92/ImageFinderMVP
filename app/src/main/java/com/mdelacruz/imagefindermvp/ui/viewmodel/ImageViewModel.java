package com.mdelacruz.imagefindermvp.ui.viewmodel;

import android.view.View;

import com.mdelacruz.imagefindermvp.R;
import com.mdelacruz.imagefindermvp.api.model.ImageResult;
import com.mdelacruz.imagefindermvp.ui.viewholder.ImageViewHolder;
import com.mdelacruz.imagefindermvp.util.LongPressGestureDetector;

import static com.mdelacruz.imagefindermvp.ui.viewmodel.ViewModelType.IMAGE;

public class ImageViewModel extends BaseViewModel<ImageViewHolder> implements LongPressGestureDetector.Listener {
  private final ImageResult imageResult;
  private final Listener listener;
  private final int id;

  public ImageViewModel(int id, ImageResult imageResult, Listener listener) {
    super(R.layout.find_image_layout);
    this.id = id;
    this.imageResult = imageResult;
    this.listener = listener;
  }

  @Override
  public ImageViewHolder createItemViewHolder(View view) {
    return new ImageViewHolder(view);
  }

  @Override
  public void bindViewHolder(ImageViewHolder holder) {
    holder.bind(imageResult, this);
  }

  @Override
  public ViewModelType getViewType() {
    return IMAGE;
  }

  @Override
  public void onLongPress() {
    if (listener != null) {
      listener.onImageLongPress(imageResult.getId(), imageResult.getThumbUri());
    }
  }

  public interface Listener {
    void onImageLongPress(String id, String uri);
  }
}
