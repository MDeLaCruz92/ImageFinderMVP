package com.mdelacruz.imagefindermvp.injection;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.mdelacruz.imagefindermvp.R;
import com.mdelacruz.imagefindermvp.api.ImageInterceptor;
import com.mdelacruz.imagefindermvp.api.ImagesApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
  @Provides
  HttpLoggingInterceptor provideLoggingInterceptor() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return loggingInterceptor;
  }

  @Provides
  @Singleton
  OkHttpClient provideHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                                 ImageInterceptor imageInterceptor) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(imageInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS);
    return builder.build();
  }


  @Provides
  @Singleton
  GsonConverterFactory provideGsonConverterFactory() {
    return GsonConverterFactory.create(new GsonBuilder().create());
  }

  @Provides
  @Singleton
  ImagesApi provideGettyImagesApi(@ForApplication Context context,
                                  OkHttpClient httpClient,
                                  GsonConverterFactory gsonConverterFactory) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(context.getString(R.string.base_url))
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient)
        .build();
    return retrofit.create(ImagesApi.class);
  }
}
