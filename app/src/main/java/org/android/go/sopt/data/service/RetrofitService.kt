package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.*
import org.android.go.sopt.data.model.request.RequestLoginDto
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseLoginDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import org.android.go.sopt.data.model.response.ResponseUserDto
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