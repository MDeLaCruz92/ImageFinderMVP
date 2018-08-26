package com.mdelacruz.imagefindermvp.ui.viewholder;

import android.annotation.SuppressLint;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mdelacruz.imagefindermvp.R;
import com.mdelacruz.imagefindermvp.api.model.ImageResult;
import com.mdelacruz.imagefindermvp.util.LongPressGestureDetector;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.image_view)
  ImageView imageView;

  public ImageViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  @SuppressLint("ClickableViewAccessibility")
  public void bind(ImageResult imageResult, LongPressGestureDetector.Listener listener) {
    GestureDetectorCompat gestureDetector = new GestureDetectorCompat(itemView.getContext(), new LongPressGestureDetector(listener));

    itemView.setOnTouchListener((v, event) -> {
      gestureDetector.onTouchEvent(event);
      return true;
    });

    Picasso.get()
        .load(imageResult.getThumbUri())
        .into(imageView);
  }
}
