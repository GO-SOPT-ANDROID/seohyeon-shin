package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.response.ResponseUserListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserListService {
    @GET("api/users")
    fun getUserList(@Query("page") page:Int) : Call<ResponseUserListDto>
}