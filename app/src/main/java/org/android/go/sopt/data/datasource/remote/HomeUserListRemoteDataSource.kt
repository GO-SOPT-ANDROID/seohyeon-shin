package org.android.go.sopt.data.datasource.remote

import android.util.Log
import org.android.go.sopt.data.model.ApiFactory
import org.android.go.sopt.data.model.response.ResponseUserListDto
import org.android.go.sopt.data.repository.HomeUserListRepository
import org.android.go.sopt.data.service.UserListService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object HomeUserListRemoteDataSource {

    fun getUserList(callback:HomeUserListRepository.GetDataCallback<List<ResponseUserListDto.Data>>){
        Log.e("hyeon","remote datasource 함수 진입")
        val retrofitService = ApiFactory.retrofitUserList.create(UserListService::class.java)
        retrofitService.getUserList(1).enqueue(object : Callback<ResponseUserListDto> {
            override fun onResponse(
                call: Call<ResponseUserListDto>,
                response: Response<ResponseUserListDto>
            ) {
                if(response.isSuccessful){
                    Log.e("hyeon","retrofit 성공")
                    Log.e("hyeon",response.body()?.data.toString())
                    callback.onSuccess(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<ResponseUserListDto>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }
}