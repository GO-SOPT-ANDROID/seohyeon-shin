package org.android.go.sopt.data.repository

import org.android.go.sopt.data.model.RequestLoginDto
import org.android.go.sopt.data.model.RequestSignUpDto
import org.android.go.sopt.data.model.ResponseLoginDto
import org.android.go.sopt.data.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {
    @POST("sign-up")
    fun signUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto>

    @POST("sign-in")
    fun login(
        @Body request: RequestLoginDto
    ): Call<ResponseLoginDto>
}