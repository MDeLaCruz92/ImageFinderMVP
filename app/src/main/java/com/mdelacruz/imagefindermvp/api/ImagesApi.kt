package com.mdelacruz.imagefindermvp.api

import com.mdelacruz.imagefindermvp.api.model.ImageResponse
import com.mdelacruz.imagefindermvp.api.model.MetadataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImagesApi {
  @GET("search/images")
  fun searchImages(@Query("phrase") phrase: String,
                   @Query("fields") fields: String,
                   @Query("sort_order") sortOrder: String) : Observable<ImageResponse>

  @GET("images/{id}")
  fun getImageMetadata(@Path("id") id: String) : Observable<MetadataResponse>

  @GET("images/{id}/similar")
  fun getSimilarImages(@Path("id") id: String) : Observable<ImageResponse>
}