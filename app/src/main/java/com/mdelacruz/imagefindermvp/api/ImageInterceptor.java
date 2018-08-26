package com.mdelacruz.imagefindermvp.api;

import android.content.Context;

import com.mdelacruz.imagefindermvp.R;
import com.mdelacruz.imagefindermvp.injection.ForApplication;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ImageInterceptor implements Interceptor {

  @Inject
  @ForApplication
  Context context;

  @Inject
  ImageInterceptor() {
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    String apiKey = context.getString(R.string.api_key);

    Request original = chain.request();
    Request request = original
        .newBuilder()
        .header("Api-Key", apiKey)
        .build();

    return chain.proceed(request);
  }
}
