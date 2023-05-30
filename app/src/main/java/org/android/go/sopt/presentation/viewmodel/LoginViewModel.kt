package org.android.go.sopt.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.model.request.RequestLoginDto
import org.android.go.sopt.data.model.ServicePool.retrofitService
import org.android.go.sopt.data.model.response.ResponseLoginDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val logInResult: MutableLiveData<ResponseLoginDto> = MutableLiveData()

    fun logIn(id: String, password: String) {
        retrofitService.login(
            RequestLoginDto(
                id,
                password
            )
        ).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                TODO("Not yet implemented")
            }
        }
        )
    }
}