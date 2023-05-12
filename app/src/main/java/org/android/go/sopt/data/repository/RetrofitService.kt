package org.android.go.sopt.data.repository

import org.android.go.sopt.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @POST("sign-up")
    fun signUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>

    @POST("sign-in")
    fun login(
        @Body request: RequestLoginDto
    ): Call<ResponseLoginDto>

    @GET("info/{userId}")
    fun getUserData(
        @Path("userId") userId:String
    ) : Call <ResponseUserDto>
}