package org.android.go.sopt.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("sign-up")
    fun signUp(
        @Body request:RequestSignUpDto): Call<ResponseSignUpDto>
    @POST("sign-in")
    fun login(
        @Body request:RequestLoginDto) : Call<ResponseLoginDto>
}