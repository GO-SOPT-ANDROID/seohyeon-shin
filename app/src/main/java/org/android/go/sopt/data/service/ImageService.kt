package org.android.go.sopt.data.service

import retrofit2.Call
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.android.go.sopt.data.model.response.ResponseMusicDto
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageService {
    @Multipart
    @POST("music")
    fun uploadImage(
        @Header("id") id: String?,
        @Part image: MultipartBody.Part,
        @Part("title") title: RequestBody,
        @Part("singer") singer: RequestBody
    ): Call<ResponseMusicDto>
}